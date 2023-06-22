package de.htwg.se.dotsandboxes.model
package matrixComponent.matrixImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import fieldComponent.fieldImpl.Move


class MatrixSpec extends AnyWordSpec {
    "A Matrix" when {
        "initialized" should {
            "have the correct size" in {
                val matrix1 = new Matrix(
                Vector(Vector(Status.Empty)),
                Vector(Vector(false), Vector(false, true)),
                Vector(Vector(false, true)),
                Vector(Player("Blue", 0, Status.Blue),
                Player("Red", 0, Status.Red)),
                list.head)

                matrix1.rowSize(0) should be(1)
                matrix1.colSize(0, 0) should be(1)

                matrix1.rowSize(1) should be(2)
                matrix1.colSize(1, 0) should be(1)
                matrix1.colSize(1, 1) should be(2)

                matrix1.rowSize(2) should be(1)
                matrix1.colSize(2, 0) should be(2)

                val matrix2 = new Matrix(3, 2, Status.Empty)
                matrix2.rowSize(0) should be(2)
                matrix2.colSize(0, 0) should be(3)
                matrix2.colSize(0, 1) should be(3)

                matrix2.rowSize(1) should be(3)
                matrix2.colSize(1, 0) should be(3)
                matrix2.colSize(1, 1) should be(3)
                matrix2.colSize(1, 2) should be(3)

                matrix2.rowSize(2) should be(2)
                matrix2.colSize(2, 0) should be(4)
                matrix2.colSize(2, 1) should be(4)
            }
            val matrix = new Matrix(2, 2, Status.Empty, 2)
            "give access to its cells" in {
                matrix.cell(0, 0, 0) should be(Status.Empty)
                matrix.cell(0, 0, 1) should be(Status.Empty)
                matrix.cell(0, 1, 0) should be(Status.Empty)
                matrix.cell(0, 1, 1) should be(Status.Empty)

                matrix.cell(1, 0, 0) shouldBe false
                matrix.cell(1, 2, 1) shouldBe false

                matrix.cell(2, 0, 0) shouldBe false
                matrix.cell(2, 1, 2) shouldBe false
            }
            "give access to its rows" in {
                matrix.row(0, 0) should be(Vector(Status.Empty, Status.Empty))
                matrix.row(0, 1) should be(Vector(Status.Empty, Status.Empty))

                matrix.row(1, 0) should be(Vector(false, false))
                matrix.row(1, 2) should be(Vector(false, false))

                matrix.row(2, 0) should be(Vector(false, false, false))
                matrix.row(2, 1) should be(Vector(false, false, false))
            }
            "allow to replace cells" in {
                val replCell0 = matrix.replaceCell(0, 0, 0, Status.Blue)
                val replCell1 = matrix.replaceCell(1, 0, 0, true)
                val replCell2 = matrix.replaceCell(2, 1, 0, true)

                matrix.cell(0, 0, 0) should be(Status.Empty)
                replCell0.cell(0, 0, 0) should be(Status.Blue)

                matrix.cell(1, 0, 0) shouldBe false
                replCell1.cell(1, 0, 0) shouldBe true

                matrix.cell(2, 1, 0) shouldBe false
                replCell2.cell(2, 1, 0) shouldBe true
            }
            "return correct Matrix" in {
                val vec0 = matrix.vector(0)
                val vec1 = matrix.vector(1)
                val vec2 = matrix.vector(2)
                val vec3 = matrix.vector(3)
                val vec4 = matrix.productElement(4).asInstanceOf[Player]

                vec0 should be(Vector(Vector(Status.Empty, Status.Empty), Vector(Status.Empty, Status.Empty)))
                vec1 should be(Vector(Vector(false, false), Vector(false, false), Vector(false, false)))
                vec2 should be(Vector(Vector(false, false, false), Vector(false, false, false)))
                vec3 should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
                vec4 should be(Player("Blue", 0, Status.Blue))
            }
        }
        "checking for squares" should {
            val matrix = new Matrix(2, 2, Status.Empty)
            val matrix2 = matrix.replaceCell(1, 0, 0, true).replaceCell(1, 1, 0, true).replaceCell(2, 0, 0, true).replaceCell(2, 0, 1, true)

            "return same matrix" in {
                val matrixVector =
                    Matrix(Vector(Vector(Status.Empty, Status.Empty), Vector(Status.Empty, Status.Empty)),
                    Vector(Vector(false, false), Vector(false, false), Vector(false, false)),
                    Vector(Vector(false, false, false), Vector(false, false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    list.head)

                matrix.checkSquare("upcase", 1, 0) should be(matrixVector)
                matrix.checkSquare("rightcase", 0, 0) should be(matrixVector)
                matrix.checkSquare("leftcase", 0, 1) should be(matrixVector)
            }
            "return correct matrix on edge case" in {
                val matrixVector =
                    Matrix(Vector(Vector(Status.Blue, Status.Empty), Vector(Status.Empty, Status.Empty)),
                    Vector(Vector(true, false), Vector(true, false), Vector(false, false)),
                    Vector(Vector(true, true, false), Vector(false, false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    list.head)

                matrix2.checkSquare("downcase", 0, 0) should be(matrixVector)
                matrix2.checkSquare("upcase", 1, 0) should be(matrixVector)

                matrix2.checkSquare("rightcase", 0, 0) should be(matrixVector)
                matrix2.checkSquare("leftcase", 0, 1) should be(matrixVector)
            }
        }
        "checking a move" should {
            "return correct matrix" in {
                val matrix = new Matrix(2, 2, Status.Empty)
                val matrix2 = matrix.replaceCell(1, 0, 0, true).replaceCell(1, 1, 0, true).replaceCell(2, 0, 0, true).replaceCell(2, 0, 1, true)
                val matrixVector =
                    Matrix(Vector(Vector(Status.Empty, Status.Empty), Vector(Status.Empty, Status.Empty)),
                    Vector(Vector(true, false), Vector(true, false), Vector(false, false)),
                    Vector(Vector(true, true, false), Vector(false, false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    list.head)

                val matrixVector2 =
                    Matrix(Vector(Vector(Status.Blue, Status.Empty), Vector(Status.Empty, Status.Empty)),
                    Vector(Vector(true, false), Vector(true, false), Vector(false, false)),
                    Vector(Vector(true, true, false), Vector(false, false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    list.head)

                matrix2.checkSquare("downcase", 0, 0) should be(matrixVector2)
                matrix2.checkSquare("upcase", matrix2.maxPosX, 0) should be(matrixVector)

                matrix2.checkSquare("rightcase", 0, 0) should be(matrixVector2)
                matrix2.checkSquare("leftcase", 0, matrix2.maxPosY) should be(matrixVector)
            }
        }
        "checking for edge case" should {
            "return correct boolean for a move" in {
                val matrix = new Matrix(3, 3, Status.Empty)
                val move1 = new Move(1, 0, 0, true)
                val move2 = new Move(1, 1, 1, true)
                val move3 = new Move(2, 0, 0, true)
                val move4 = new Move(2, 1, 1, true)

                matrix.isEdge(move1) shouldBe true
                matrix.isEdge(move2) shouldBe false

                matrix.isEdge(move3) shouldBe true
                matrix.isEdge(move4) shouldBe false
            }
        }
        "manipulating a player" should {
            "return the correct player" in {
                val matrix = new Matrix(1, 1, Status.Empty)

                matrix.currentPlayerInfo._1 should be("Blue")
                matrix.currentPlayerInfo._2 should be(0)
                matrix.updatePlayer().currentPlayerInfo._2 should be(0)
                matrix.updatePlayer().changePlayer.currentPlayerInfo._2 should be(1)
            }
            "return a matrix with correct player" in {
                val matrix = new Matrix(1, 1, Status.Empty)

                val matrixVector =
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Red", 0, Status.Red))

                val matrixVector2 =
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Blue", 0, Status.Blue))

                matrix.changePlayer should be(matrixVector)
                matrix.updatePlayer() should be(matrixVector2)

                matrix.changePlayer.changePlayer should be(matrixVector2)
                matrix.updatePlayer() should be(matrixVector2)

                matrix.updatePlayer(1) should be(matrixVector)

                matrix.list should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
                matrix.addPoints(points = 1).updatePlayer().list should be(Vector(Player("Blue", 1, Status.Blue), Player("Red", 0, Status.Red)))

                matrix.addPoints(points = 1).updatePlayer() should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 1, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Blue", 1, Status.Blue)))
                
                matrix.currentPlayer should be(Player("Blue", 0, Status.Blue))
                matrix.changePlayer.addPoints(points = 1).updatePlayer().currentPlayer should be(Player("Red", 1, Status.Red))

                matrix.changePlayer.addPoints(points = 1).updatePlayer() should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 1, Status.Red)),
                    Player("Red", 1, Status.Red)))

                matrix.addPoints(0, 3).updatePlayer(0) should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 3, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Blue", 3, Status.Blue)))

                matrix.addPoints(1, 5).updatePlayer(1).getPoints(1) should be(5)

                matrix.list should not be(list)


                val matrix0 = new Matrix(1, 1, Status.Empty)
                val matrix1 = new Matrix(1, 1, Status.Empty, 1)
                val matrix2 = new Matrix(1, 1, Status.Empty, 2)
                val matrix3 = new Matrix(1, 1, Status.Empty, 3)
                val matrix4 = new Matrix(1, 1, Status.Empty, 4)

                matrix0 should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Blue", 0, Status.Blue)))

                matrix1 should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue)),
                    Player("Blue", 0, Status.Blue)))

                matrix2 should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)),
                    Player("Blue", 0, Status.Blue)))

                matrix3 should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green)),
                    Player("Blue", 0, Status.Blue)))

                matrix4 should be(
                    Matrix(Vector(Vector(Status.Empty)),
                    Vector(Vector(false), Vector(false)),
                    Vector(Vector(false, false)),
                    Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green), Player("Yellow", 0, Status.Yellow)),
                    Player("Blue", 0, Status.Blue)))
            }
        }
    }
}
