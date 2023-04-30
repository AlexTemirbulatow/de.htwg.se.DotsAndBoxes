package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpec extends AnyWordSpec {
    "A Matrix" when {
        "initialized" should {
            "have the correct size" in {
                val matrix1 = new Matrix(
                Vector(Vector(Status.Empty)),
                Vector(Vector(false), Vector(false, true)), 
                Vector(Vector(false, true)))

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
            val matrix = new Matrix(2, 2, Status.Empty)
            "give access to its cells" in {
                matrix.cell(0, 0, 0) should be(Status.Empty)
                matrix.cell(0, 0, 1) should be(Status.Empty)
                matrix.cell(0, 1, 0) should be(Status.Empty)
                matrix.cell(0, 1, 1) should be(Status.Empty)

                matrix.cell(1, 0, 0) should === (false)
                matrix.cell(1, 2, 1) should === (false)

                matrix.cell(2, 0, 0) should === (false)
                matrix.cell(2, 1, 2) should === (false)
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
                val replCell3 = replCell2.replaceCell(2, 1, 0, true)

                matrix.cell(0, 0, 0) should be(Status.Empty)
                replCell0.cell(0, 0, 0) should be(Status.Blue)

                matrix.cell(1, 0, 0) should === (false)
                replCell1.cell(1, 0, 0) should === (true)

                matrix.cell(2, 1, 0) should === (false)
                replCell2.cell(2, 1, 0) should === (true)

                replCell3 should equal(replCell2)
            }
            "should return correct Matrix[Vector]" in {
                val vec0 = matrix.vector(0)
                val vec1 = matrix.vector(1)
                val vec2 = matrix.vector(2)

                vec0 should be(Vector(Vector(Status.Empty, Status.Empty), Vector(Status.Empty, Status.Empty)))
                vec1 should be(Vector(Vector(false, false), Vector(false, false), Vector(false, false)))
                vec2 should be(Vector(Vector(false, false, false), Vector(false, false, false)))
            }
        }
        "checking for squares" should {
            val matrix = new Matrix(2, 2, Status.Empty)
            val matrix2 = matrix.replaceCell(1, 0, 0, true).replaceCell(1, 1, 0, true).replaceCell(2, 0, 0, true).replaceCell(2, 0, 1, true)
            
            "return same matrix" in {
                val matrixVector = 
                    Matrix(Vector(Vector(Status.Empty, Status.Empty), Vector(Status.Empty, Status.Empty)), 
                    Vector(Vector(false, false), Vector(false, false), Vector(false, false)), 
                    Vector(Vector(false, false, false), Vector(false, false, false)))

                matrix.checkSquare(2, 1, 0) should be(matrixVector)
                matrix.checkSquare(3, 0, 0) should be(matrixVector)
                matrix.checkSquare(4, 0, 1) should be(matrixVector)
            }
            "return correct matrix on edge case" in {
                val matrixVector = 
                    Matrix(Vector(Vector(Status.Blue, Status.Empty), Vector(Status.Empty, Status.Empty)), 
                    Vector(Vector(true, false), Vector(true, false), Vector(false, false)), 
                    Vector(Vector(true, true, false), Vector(false, false, false)))                
                
                matrix2.checkSquare(1, 0, 0) should be(matrixVector)
                matrix2.checkSquare(2, 1, 0) should be(matrixVector)

                matrix2.checkSquare(3, 0, 0) should be(matrixVector)
                matrix2.checkSquare(4, 0, 1) should be(matrixVector)
            }
        }
        "checking a move" should {
            "return correct matrix" in {
                val matrix = new Matrix(2, 2, Status.Empty)
                val matrix2 = matrix.replaceCell(1, 0, 0, true).replaceCell(1, 1, 0, true).replaceCell(2, 0, 0, true).replaceCell(2, 0, 1, true)
                val matrixVector =
                    Matrix(Vector(Vector(Status.Empty, Status.Empty), Vector(Status.Empty, Status.Empty)), 
                    Vector(Vector(true, false), Vector(true, false), Vector(false, false)), 
                    Vector(Vector(true, true, false), Vector(false, false, false)))
                val matrixVector2 =
                    Matrix(Vector(Vector(Status.Blue, Status.Empty), Vector(Status.Empty, Status.Empty)), 
                    Vector(Vector(true, false), Vector(true, false), Vector(false, false)), 
                    Vector(Vector(true, true, false), Vector(false, false, false)))

                matrix2.checkMove(1, 0, 0) should be(matrixVector2)
                matrix2.checkMove(1, matrix2.maxPosX, 0) should be(matrixVector)

                matrix2.checkMove(2, 0, 0) should be(matrixVector2)
                matrix2.checkMove(2, 0, matrix2.maxPosY) should be(matrixVector)
            }
        }
        "checking for edge case" should {
            "return correct boolean for a move" in {
                val matrix = new Matrix(3, 3, Status.Empty)
                val move1 = new Move(1, 0, 0, true)
                val move2 = new Move(1, 1, 1, true)
                val move3 = new Move(2, 0, 0, true)
                val move4 = new Move(2, 1, 1, true)

                matrix.isEdge(move1) should === (true)
                matrix.isEdge(move2) should === (false)
                
                matrix.isEdge(move3) should === (true)
                matrix.isEdge(move4) should === (false)
            }
        }
    }
}
