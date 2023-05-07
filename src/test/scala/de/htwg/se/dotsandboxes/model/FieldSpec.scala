package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {

    "A Dots and Boxes Field" when {
        "initialized empty" should {
            val field1 = new Field(1, 1, Status.Empty, 2)
            val field2 = new Field(3, 2, Status.Empty, 2)
            val field3 = new Field(3, 3, Status.Empty, 2)
            val field4 = new Field(5, 5, Status.Empty, 2)
            "have a bar as String of form 'O-------O-------O-------O'" in {
                field4.bar(rowIndex = 0) should be("O-------O-------O-------O-------O-------O\n")
                field2.bar(cellNum = 3, rowIndex = 0) should be("O-------O-------O-------O\n")
            }
            "have a scalable bar" in {
                field1.bar(1, 1, 0) should be("O-O\n")
                field2.bar(2, 1, 0) should be("O--O\n")
                field3.bar(2, 2, 0) should be("O--O--O\n")
            }
            "have cells as String of form " +
                "¦   -   ¦   -   ¦   -   ¦" +
                "¦   -   ¦   -   ¦   -   ¦" in {
                field3.cells(0) should be(
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n")
                field3.cells(1, 7, 2) should be(
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n")
                field3.cells(2, 7, 2) should be(
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n")
            }
            "have scalable cells" in {
                field1.cells(0, 1, 1) should be("¦-¦\n")
                field1.cells(0, 5, 1) should be("¦  -  ¦\n")
                field2.cells(0, 3, 1) should be("¦ - ¦ - ¦ - ¦\n")
                field3.cells(0, 7, 2) should be(
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n")
            }
            "have a mesh in form " +
                "O---O---O---O" +
                "¦ - ¦ - ¦ - ¦" +
                "¦ - ¦ - ¦ - ¦" +
                "O---O---O---O" +
                "¦ - ¦ - ¦ - ¦" +
                "¦ - ¦ - ¦ - ¦" +
                "O---O---O---O" in {
                field2.mesh(3) should be(
                    "O---O---O---O\n" +
                    "¦ - ¦ - ¦ - ¦\n" +
                    "¦ - ¦ - ¦ - ¦\n" +
                    "O---O---O---O\n" +
                    "¦ - ¦ - ¦ - ¦\n" +
                    "¦ - ¦ - ¦ - ¦\n" +
                    "O---O---O---O\n")
                field1.mesh() should be("O-------O\n" + "¦   -   ¦\n" + "¦   -   ¦\n" + "O-------O\n")
                field1.mesh(1) should be("O-O\n" + "¦-¦\n" + "¦-¦\n" + "O-O\n")
                field3.mesh() should be(
                    "O-------O-------O-------O\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "O-------O-------O-------O\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "O-------O-------O-------O\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "O-------O-------O-------O\n")
            }
        }
        "initialized blue" should {
            val field4 = new Field(2, 2, Status.Blue)
            "have a mesh with Blue fillings" in {
                field4.mesh() should be(
                    "O-------O-------O\n" +
                    "¦   B   ¦   B   ¦\n" +
                    "¦   B   ¦   B   ¦\n" +
                    "O-------O-------O\n" +
                    "¦   B   ¦   B   ¦\n" +
                    "¦   B   ¦   B   ¦\n" +
                    "O-------O-------O\n")
            }
        }
        "initialized empty" should {
            val field = new Field(2, 2, Status.Empty)
            "be empty initially" in {
                field.toString should be(
                    "O-------O-------O\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "O-------O-------O\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "O-------O-------O\n")
            }
            "have Blue and Red after putCell()" in {
                field.putCell(0, 0, 0, Status.Blue).putCell(0, 1, 1, Status.Red).toString should be(
                    "O-------O-------O\n" +
                    "¦   B   ¦   -   ¦\n" +
                    "¦   B   ¦   -   ¦\n" +
                    "O-------O-------O\n" +
                    "¦   -   ¦   R   ¦\n" +
                    "¦   -   ¦   R   ¦\n" +
                    "O-------O-------O\n")
            }
            "have changeable lines in form of '=' and '‖' after putCell()" in {
                field.putCell(1, 0, 0, true).putCell(2, 0, 0, true).toString should be(
                    "O=======O-------O\n" +
                    "‖   -   ¦   -   ¦\n" +
                    "‖   -   ¦   -   ¦\n" +
                    "O-------O-------O\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "O-------O-------O\n")
            }
            "acces Matrix to check line and filling status" in {
                val bar = field.putCell(1, 0, 0, true)
                bar.rows(1, 0, 7) should be("-------")
                bar.rows(0, 0, 7) should be("=======")

                val cells = field.putCell(2, 0, 0, true)
                cells.columns(1, 0, 7) should be("¦   -   ")
                cells.columns(0, 0, 7) should be("‖   -   ")
                cells.columns(1, 2, 7) should be("¦")

                val square = field.putCell(0, 0, 0, Status.Blue).putCell(0, 1, 1, Status.Red)
                square.status(1, 0, 7) should be("   -   ")
                square.status(0, 0, 7) should be("   B   ")
                square.status(1, 1, 7) should be("   R   ")
            }
            "return correct size" in {
                field.rowSize() should be(2)
                field.colSize() should be(2)

                field.rowSize(0) should be(2)
                field.rowSize(1) should be(3)
                field.rowSize(2) should be(2)

                field.colSize(0, 0) should be(2)
                field.colSize(0, 1) should be(2)

                field.colSize(1, 0) should be(2)
                field.colSize(1, 1) should be(2)
                field.colSize(1, 2) should be(2)

                field.colSize(2, 0) should be(3)
                field.colSize(2, 1) should be(3)
            }
            "return correct space" in {
                field.space(3) should be(" ")
                field.space(5) should be("  ")
                field.space(7) should be("   ")
            }
            "give access to its cells" in {
                field.getCell(0, 0, 0) should be(Status.Empty)
                field.getCell(1, 0, 0) should === (false)
                field.getCell(2, 0, 0) should === (false)

                field.putCell(1, 0, 0, true).getCell(1, 0, 0) should === (true)
                field.putCell(2, 0, 0, true).getCell(2, 0, 0) should === (true)
            }
            "check if a move is a edge case" in {
                val move1 = new Move(1, 0, 0, true)
                val move2 = new Move(1, 1, 1, true)
                val move3 = new Move(2, 0, 0, true)
                val move4 = new Move(2, 1, 1, true)

                field.isEdge(move1) should === (true)
                field.isEdge(move2) should === (false)

                field.isEdge(move3) should === (true)
                field.isEdge(move4) should === (false)
            }
            "do a move in edge case" in {
                field.doEdge(1, 0, 0) should be(field)

                field.putCell(1, 0, 0, true).putCell(1, 1, 0, true).putCell(2, 0, 0, true).putCell(2, 0, 1, true).doEdge(1, 0, 0).toString should be(
                    "O=======O-------O\n" +
                    "‖   B   ‖   -   ¦\n" +
                    "‖   B   ‖   -   ¦\n" +
                    "O=======O-------O\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦\n" +
                    "O-------O-------O\n")
            }
            "do a move in mud case" in {
                val field = new Field(3, 3, Status.Empty)

                field.doMid("downcase", 1, 1) should be(field)
                field.putCell(1, 1, 1, true).putCell(1, 2, 1, true).putCell(2, 1, 1, true).putCell(2, 1, 2, true).doMid("downcase", 1, 1).toString should be(
                    "O-------O-------O-------O\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "O-------O=======O-------O\n" +
                    "¦   -   ‖   B   ‖   -   ¦\n" +
                    "¦   -   ‖   B   ‖   -   ¦\n" +
                    "O-------O=======O-------O\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "¦   -   ¦   -   ¦   -   ¦\n" +
                    "O-------O-------O-------O\n")
            }
            "return information about current game state" in {
                var field = new Field(1, 1, Status.Empty)

                val matrixVector1 =
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Blue", 0, Status.Blue))

                val matrixVector2 =
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Red", 0, Status.Red))

                val matrixVector3 =
                    Matrix(Vector(Vector(Status.Blue)),
                    Vector(Vector(true), Vector(true)),
                    Vector(Vector(true, true)),
                    Vector(Player("Blue", 1, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Blue", 1, Status.Blue))

                field.currentPlayer should be("Blue")
                field.currentStatus should be(Vector(Vector(Status.Empty)))
                field.currentPoints should be(0)

                field.isFinished should === (false)

                field.nextPlayer.getMatrix should be(matrixVector2)
                field.nextPlayer.nextPlayer.getMatrix should be(matrixVector1)

                field = field.putCell(0, 0, 0, Status.Blue).putCell(1, 0, 0, true).putCell(2, 0, 0, true).putCell(1, 1, 0, true).putCell(2, 0, 1, true)
                field.currentPlayer should be("Blue")
                field.currentStatus should be(Vector(Vector(Status.Blue)))

                field = field.addPoint.updatePlayer
                field.getMatrix should be(matrixVector3)
                field.currentPoints should be(1)

                field.isFinished should === (true)

                field.winner should be("Player Blue wins!")

                field.stats should be(
                    "Player Blue [points: 1]\n" +
                    "Player Red [points: 0]")

                val field2 = new Field(
                    Matrix(Vector(Vector(Status.Blue, Status.Blue), Vector(Status.Red, Status.Red)),
                    Vector(Vector(true, true), Vector(true, true), Vector(true, true)),
                    Vector(Vector(true, true, true), Vector(true, true, true)),
                    Vector(Player("Blue", 2, Status.Blue), Player("Red", 2, Status.Red)),
                    Player("Blue", 2, Status.Blue)))

                field2.winner should be("It's a draw!")

                field2.stats should be(
                    "Player Blue [points: 2]\n" +
                    "Player Red [points: 2]")

                val field3 = new Field(
                    Matrix(Vector(Vector(Status.Blue, Status.Blue), Vector(Status.Red, Status.Red)), 
                    Vector(Vector(true, true), Vector(true, true), Vector(true, true)),
                    Vector(Vector(true, true, true), Vector(true, true, true)),
                    Vector(Player("Blue", 2, Status.Blue), Player("Red", 2, Status.Red), Player("Green", 1, Status.Green)),
                    Player("Blue", 2, Status.Blue)))
                
                field3.winner should be("It's a draw!")

                field3.stats should be(
                    "Player Blue [points: 2]\n" +
                    "Player Red [points: 2]\n" +
                    "Player Green [points: 1]")
            }
            "return the correct player list" in {
                val field0 = new Field(3, 3, Status.Empty)
                val field1 = new Field(3, 3, Status.Empty, 1)
                val field2 = new Field(3, 3, Status.Empty, 2)
                val field3 = new Field(3, 3, Status.Empty, 3)
                val field4 = new Field(3, 3, Status.Empty, 4)

                field0.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
                field1.playerList should be(Vector(Player("Blue", 0, Status.Blue)))
                field2.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
                field3.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green)))
                field4.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green), Player("Yellow", 0, Status.Yellow)))
            }
        }
    }
}
