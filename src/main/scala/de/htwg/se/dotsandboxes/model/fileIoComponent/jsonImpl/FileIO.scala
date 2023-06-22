package de.htwg.se.dotsandboxes.model
package fileIoComponent.jsonImpl

import play.api.libs.json._
import scala.io.Source

import fieldComponent.FieldInterface
import fileIoComponent.FileIOInterface
import fieldComponent.fieldImpl.Field
import matrixComponent.matrixImpl.Status


class FileIO extends FileIOInterface:
    override def save(field: FieldInterface): Unit =
        import java.io._
        val printWriter = new PrintWriter(new File("field.json"))
        printWriter.write(Json.prettyPrint(fieldToJson(field)))
        printWriter.close

    def fieldToJson(field: FieldInterface): JsObject =
        Json.obj(
            "field" -> Json.obj(
                "rowSize" -> field.colSize(1, 0),
                "colSize" -> field.rowSize(2),
                "status" -> Json.toJson(
                    for
                        row <- 0 until field.rowSize(0)
                        col <- 0 until field.colSize(0, 0)
                    yield
                        Json.obj(
                            "row" -> row,
                            "col" -> col,
                            "value" -> Json.toJson(field.getCell(0, row, col).toString))
                ),
                "rows" -> Json.toJson(
                    for
                        row <- 0 until field.rowSize(1)
                        col <- 0 until field.colSize(1, 0)
                    yield
                        Json.obj(
                            "row" -> row,
                            "col" -> col,
                            "value" -> Json.toJson(field.getCell(1, row, col).toString.toBoolean))
                ),
                "cols" -> Json.toJson(
                    for
                        row <- 0 until field.rowSize(2)
                        col <- 0 until field.colSize(2, 0)
                    yield
                        Json.obj(
                            "row" -> row,
                            "col" -> col,
                            "value" -> Json.toJson(field.getCell(2, row, col).toString.toBoolean))
                ),
                "playerList" -> Json.toJson(
                    for
                        playerIndex <- 0 until field.playerList.size
                    yield
                        Json.obj(
                            "index" -> playerIndex,
                            "points" -> Json.toJson(field.getPoints(playerIndex)))
                ),
                "playerSize" -> Json.toJson(field.playerList.size),
                "currentPlayer" -> Json.toJson(field.currentPlayerIndex)))


    override def load: FieldInterface =
        val source: String = Source.fromFile("field.json").getLines.mkString
        val json: JsValue = Json.parse(source)
        val rowSize = (json \ "field" \ "rowSize").as[Int]
        val colSize = (json \ "field" \ "colSize").as[Int]
        val playerSize = (json \ "field" \ "playerSize").as[Int]
        var field: FieldInterface = new Field(rowSize, colSize, Status.Empty, playerSize)

        val status: JsLookupResult = (json \ "field" \ "status")
        for (index <- 0 until rowSize * colSize)
            val row = (status \\ "row")(index).as[Int]
            val col = (status \\ "col")(index).as[Int]
            val value = (status \\ "value")(index).as[String]
            val player = value match
                case "B" => Status.Blue
                case "R" => Status.Red
                case "G" => Status.Green
                case "Y" => Status.Yellow
                case _ => Status.Empty
            field = field.putCell(0, row, col, player)

        val rows: JsLookupResult = (json \ "field" \ "rows")
        for (index <- 0 until rowSize * (colSize + 1))
            val row = (rows \\ "row")(index).as[Int]
            val col = (rows \\ "col")(index).as[Int]
            val value = (rows \\ "value")(index).as[Boolean]
            field = field.putCell(1, row, col, value)

        val cols: JsLookupResult = (json \ "field" \ "cols")
        for (index <- 0 until (rowSize + 1) * colSize)
            val row = (cols \\ "row")(index).as[Int]
            val col = (cols \\ "col")(index).as[Int]
            val value = (cols \\ "value")(index).as[Boolean]
            field = field.putCell(2, row, col, value)

        val points: JsLookupResult = (json \ "field" \ "playerList")
        for (player <- 0 until playerSize)
                val index = (points \\ "index")(player).as[Int]
                val score = (points \\ "points")(player).as[Int]
                field = field.addPoints(index, score)

        val curPlayerIndex: Int = (json \ "field" \ "currentPlayer").as[Int]
        field = field.updatePlayer(curPlayerIndex)

        field
