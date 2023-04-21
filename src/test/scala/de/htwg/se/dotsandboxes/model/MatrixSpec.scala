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
                val replCell2 = matrix.replaceCell(2, 0, 0, true)
                val replCell3 = replCell2.replaceCell(3, 0, 0, true)

                matrix.cell(0, 0, 0) should be(Status.Empty)
                replCell0.cell(0, 0, 0) should be(Status.Blue)

                matrix.cell(1, 0, 0) should === (false)
                replCell1.cell(1, 0, 0) should === (true)

                matrix.cell(2, 0, 0) should === (false)
                replCell2.cell(2, 0, 0) should === (true)

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
    }
}
