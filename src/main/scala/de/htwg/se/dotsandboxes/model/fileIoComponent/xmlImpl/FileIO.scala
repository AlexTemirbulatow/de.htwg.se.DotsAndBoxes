package de.htwg.se.dotsandboxes.model
package fileIoComponent.xmlImpl

import scala.xml.{Elem, NodeSeq, PrettyPrinter}

import fieldComponent.FieldInterface
import fileIoComponent.FileIOInterface
import fieldComponent.fieldImpl.Field
import matrixComponent.matrixImpl.Status


class FileIO extends FileIOInterface:
    override def save(field: FieldInterface): Unit =
        import java.io._
        val prettyPrinter = new PrettyPrinter(120, 4)
        val xml = prettyPrinter.format(fieldToXml(field))
        val printWriter = new PrintWriter(new File("field.xml"))
        printWriter.write(xml)
        printWriter.close

    def fieldToXml(field: FieldInterface): Elem =
        <field rowSize={field.colSize(1, 0).toString} colSize={field.rowSize(2).toString}>
            <status> {
                for
                    row <- 0 until field.rowSize(0)
                    col <- 0 until field.colSize(0, 0)
                yield cellToXml(field, 0, row, col)}
            </status>
            <rows> {
                for
                    row <- 0 until field.rowSize(1)
                    col <- 0 until field.colSize(1, 0)
                yield cellToXml(field, 1, row, col)}
            </rows>
            <cols> {
                for
                    row <- 0 until field.rowSize(2)
                    col <- 0 until field.colSize(2, 0)
                yield cellToXml(field, 2, row, col)}
            </cols>
            <playerList playerSize={field.playerList.size.toString} currentPlayer={field.currentPlayerIndex.toString}>
            {field.playerList.indices.map(playerToXml(field, _))}
            </playerList>
        </field>

    def cellToXml(field: FieldInterface, vec: Int, row: Int, col: Int): Elem = 
        <data row={row.toString} col={col.toString}>
            {field.getCell(vec, row, col)}
        </data>

    def playerToXml(field: FieldInterface, index: Int): Elem =
        <data index={index.toString}>
            {field.getPoints(index)}
        </data>


    override def load: FieldInterface =
        val file: Elem = scala.xml.XML.loadFile("field.xml")
        val rowSize: Int = (file \\ "field" \ "@rowSize").text.toInt
        val colSize: Int = (file \\ "field" \ "@colSize").text.toInt
        val playerSize: Int = (file \\ "field" \ "playerList" \ "@playerSize").text.toInt
        var field: FieldInterface = new Field(rowSize, colSize, Status.Empty, playerSize)

        val statusSeq: NodeSeq = (file \\ "field" \ "status" \ "data")
        for (rowNode <- statusSeq)
            val row = (rowNode \ "@row").text.toInt
            val col = (rowNode \ "@col").text.toInt
            val value = rowNode.text.trim
            val status = value match
                case "B" => Status.Blue
                case "R" => Status.Red
                case "G" => Status.Green
                case "Y" => Status.Yellow
                case _ => Status.Empty
            field = field.putCell(0, row, col, status)

        val rowSeq: NodeSeq = (file \\ "field" \ "rows" \ "data")
        for (rowNode <- rowSeq)
            val row = (rowNode \ "@row").text.toInt
            val col = (rowNode \ "@col").text.toInt
            val value = rowNode.text.trim.toBoolean
            field = field.putCell(1, row, col, value)

        val colSeq: NodeSeq = (file \\ "field" \ "cols" \ "data")
        for (rowNode <- colSeq)
            val row = (rowNode \ "@row").text.toInt
            val col = (rowNode \ "@col").text.toInt
            val value = rowNode.text.trim.toBoolean
            field = field.putCell(2, row, col, value)

        val scoreSeq: NodeSeq = (file \\ "field" \ "playerList" \ "data")
        for (player <- scoreSeq)
                val index = (player \ "@index").text.toInt
                val score = player.text.trim.toInt
                field = field.addPoints(index, score)

        val curPlayerIndex: Int = (file \\ "field" \ "playerList" \ "@currentPlayer").text.toInt
        field = field.updatePlayer(curPlayerIndex)

        field
