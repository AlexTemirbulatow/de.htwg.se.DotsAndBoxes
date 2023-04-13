package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MatrixSpec extends AnyWordSpec {
    "A Matrix" when {
        "initialized" should {
            "have the correct size" in {
                val matrix1 = new Matrix(Vector(Vector(Filled.Empty)))
                matrix1.rowSize should be(1)
                matrix1.colSizeEven should be(1)
                val matrix2 = new Matrix(3, 2, Filled.Empty)
                matrix2.rowSize should be(2)
                matrix2.colSizeEven should be(3)
                val matrix3 = new Matrix[Boolean](2, 2, false)
                matrix3.rowSize should be(2)
                matrix3.colSizeEven should be(2)
                val test = Vector.tabulate(2, 2) { (_, _) => false }
                val matrix4 = new Matrix(test)
                matrix4.rowSize should be(2)
                matrix4.colSizeEven should be(2)
            }
            val test = Vector.tabulate(2, 2) { (_, _) => false }
            val matrix = new Matrix(test)
            "give access to its cells" in {
                matrix.cell(0, 0) should be(false)
                matrix.cell(1, 1) should be(false)
                test(0)(0) should be(false)
            }
            "give access to its rows" in {
                matrix.row(0) should be(Vector(false, false))
                matrix.row(1) should be(Vector(false, false))
                test(0) should be(Vector(false, false))
            }
            "be refillable" in {
                val refillMatrix = matrix.fill(true)
                matrix.cell(1, 1) should be(false)
                refillMatrix.cell(1, 1) should be(true)
                refillMatrix.row(1) should be(Vector(true, true))
            }
            "allow to replace cells" in {
                val replCellMatrix = matrix.replaceCell(0, 0, false)
                matrix.cell(0, 0) should be(false)
                replCellMatrix.cell(0, 0) should be(false) 
                replCellMatrix.row(0) should be(Vector(false, false))
            }
        }
    }
}