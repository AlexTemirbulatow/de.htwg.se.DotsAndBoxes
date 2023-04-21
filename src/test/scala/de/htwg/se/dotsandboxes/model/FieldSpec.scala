package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {

    "A Dots and Boxes Field" when {
        "initialized empty" should {
            val field1 = new Field(1, 1, Status.Empty)
            val field2 = new Field(3, 2, Status.Empty)
            val field3 = new Field(3, 3, Status.Empty)
            val field4 = new Field(5, 5, Status.Empty)
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
                "¦   E   ¦   E   ¦   E   ¦" +
                "¦   E   ¦   E   ¦   E   ¦" in {
                field3.cells(0) should be(
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n")
                field3.cells(1, 7, 2) should be(
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n")
                field3.cells(2, 7, 2) should be(
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n")
            }
            "have scalable cells" in {
                field1.cells(0, 1, 1) should be("¦E¦\n")
                field1.cells(0, 5, 1) should be("¦  E  ¦\n")
                field2.cells(0, 3, 1) should be("¦ E ¦ E ¦ E ¦\n")
                field3.cells(0, 7, 2) should be(
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n")
            }
            "have a mesh in form " +
                "O---O---O---O" +
                "¦ E ¦ E ¦ E ¦" +
                "¦ E ¦ E ¦ E ¦" +
                "O---O---O---O" +
                "¦ E ¦ E ¦ E ¦" +
                "¦ E ¦ E ¦ E ¦" +
                "O---O---O---O" in {
                field2.mesh(3) should be(
                    "O---O---O---O\n" +
                    "¦ E ¦ E ¦ E ¦\n" +
                    "¦ E ¦ E ¦ E ¦\n" +
                    "O---O---O---O\n" +
                    "¦ E ¦ E ¦ E ¦\n" +
                    "¦ E ¦ E ¦ E ¦\n" +
                    "O---O---O---O\n")
                field1.mesh() should be("O-------O\n" + "¦   E   ¦\n" + "¦   E   ¦\n" + "O-------O\n")
                field1.mesh(1) should be("O-O\n" + "¦E¦\n" + "¦E¦\n" + "O-O\n")
                field3.mesh() should be(
                    "O-------O-------O-------O\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "O-------O-------O-------O\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "O-------O-------O-------O\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦   E   ¦\n" +
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
                    "¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦\n" +
                    "O-------O-------O\n" +
                    "¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦\n" +
                    "O-------O-------O\n")
            }
            "have Blue and Red after put()" in {
                field.put(0, 0, 0, Status.Blue).put(0, 1, 1, Status.Red).toString should be(
                    "O-------O-------O\n" +
                    "¦   B   ¦   E   ¦\n" +
                    "¦   B   ¦   E   ¦\n" +
                    "O-------O-------O\n" +
                    "¦   E   ¦   R   ¦\n" +
                    "¦   E   ¦   R   ¦\n" +
                    "O-------O-------O\n")
            }
            "have changeable lines in form of '=' and '‖' after put()" in {
                field.put(1, 0, 0, true).put(2, 0, 0, true).toString should be(
                    "O=======O-------O\n" +
                    "‖   E   ¦   E   ¦\n" +
                    "‖   E   ¦   E   ¦\n" +
                    "O-------O-------O\n" +
                    "¦   E   ¦   E   ¦\n" +
                    "¦   E   ¦   E   ¦\n" +
                    "O-------O-------O\n")
            }
            "acces Matrix to check line and filling status" in {
                val bar = field.put(1, 0, 0, true)
                bar.xline(1, 0, 7) should be("-------")
                bar.xline(0, 0, 7) should be("=======")

                val cells = field.put(2, 0, 0, true)
                cells.yline(1, 0, 7) should be("¦   E   ")
                cells.yline(0, 0, 7) should be("‖   E   ")
                cells.yline(1, 2, 7) should be("¦")
                
                val square = field.put(0, 0, 0, Status.Blue).put(0, 1, 1, Status.Red)
                square.filling(1, 0, 7) should be("   E   ")
                square.filling(0, 0, 7) should be("   B   ")
                square.filling(1, 1, 7) should be("   R   ")
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
        }
    }
}
