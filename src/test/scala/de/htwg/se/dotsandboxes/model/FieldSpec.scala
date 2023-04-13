package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {

    "A Dots and Boxes Field" when {
        "initialized Empty" should {
            val field1 = new Field(1, 1, Filled.Empty)
            val field2 = new Field(3, 2, Filled.Empty)
            val field3 = new Field(3, 3, Filled.Empty)
            "have a bar as String of form 'O-------O-------O-------O-------O-------O'" in {
                field2.bar() should be("O-------O-------O-------O-------O-------O\n")
            }
            "have a scalable bar" in {
                field1.bar(1, 1) should be("O-O\n")
                field2.bar(2, 1) should be("O--O\n")
                field3.bar(2, 2) should be("O--O--O\n")
            }
            "have cells as String of form " +
                "|   E   |   E   |   E   |" +
                "|   E   |   E   |   E   |" in {
                field3.cells(0, 7, 2) should be(
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n")
                field3.cells(1) should be(
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n")
                field3.cells(2) should be(
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n")
            }
            "have scalable cells" in {
                field1.cells(0, 1, 1) should be("|E|\n")
                field1.cells(0, 5, 1) should be("|  E  |\n")
                field2.cells(1, 3, 1) should be("| E | E | E |\n")
                field3.cells(0, 7, 2) should be(
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n")
            }
            "have a mesh in form " +
                "O---O---O---O" +
                "| E | E | E |" +
                "| E | E | E |" +
                "O---O---O---O" +
                "| E | E | E |" +
                "| E | E | E |" +
                "O---O---O---O" in {
                field2.mesh(3) should be(
                    "O---O---O---O\n" +
                    "| E | E | E |\n" +
                    "| E | E | E |\n" +
                    "O---O---O---O\n" +
                    "| E | E | E |\n" +
                    "| E | E | E |\n" +
                    "O---O---O---O\n")
                field1.mesh(1) should be("O-O\n" + "|E|\n" + "|E|\n" + "O-O\n")
                field1.mesh() should be("O-------O\n" + "|   E   |\n" + "|   E   |\n" + "O-------O\n")
                field1.rowSize should be(1)
                field3.mesh() should be(
                    "O-------O-------O-------O\n" +
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n")
            }
        }
        "initialized Blue" should {
            val field4 = new Field(1, 1, Filled.Blue)
            val field5 = new Field(3, 2, Filled.Blue)
            val field6 = new Field(3, 3, Filled.Blue)
            "have a bar as String of form 'O-------O-------O-------O-------O-------O'" in {
                field5.bar() should be("O-------O-------O-------O-------O-------O\n")
            }
            "have a scalable bar" in {
                field4.bar(1, 1) should be("O-O\n")
                field5.bar(2, 1) should be("O--O\n")
                field6.bar(2, 2) should be("O--O--O\n")
            }
            "have cells as String of form " +
                "|   B   |   B   |   B   |" +
                "|   B   |   B   |   B   |" in {
                field6.cells(0) should be(
                    "|   B   |   B   |   B   |\n" +
                    "|   B   |   B   |   B   |\n")
                field6.cells(1) should be(
                    "|   B   |   B   |   B   |\n" +
                    "|   B   |   B   |   B   |\n")
                field6.cells(2) should be(
                    "|   B   |   B   |   B   |\n" +
                    "|   B   |   B   |   B   |\n")
            }
            "have scalable cells" in {
                field4.cells(0, 1, 1) should be("|B|\n")
                field5.cells(0, 3, 1) should be("| B | B | B |\n")
                field6.cells(0, 7, 2) should be(
                    "|   B   |   B   |   B   |\n" +
                    "|   B   |   B   |   B   |\n")
            }
            "have a mesh in form " +
                "O---O---O---O" +
                "| B | B | B |" +
                "| B | B | B |" +
                "O---O---O---O" +
                "| B | B | B |" +
                "| B | B | B |" +
                "O---O---O---O" in {
                field5.mesh(3) should be(
                    "O---O---O---O\n" +
                    "| B | B | B |\n" +
                    "| B | B | B |\n" +
                    "O---O---O---O\n" +
                    "| B | B | B |\n" +
                    "| B | B | B |\n" +
                    "O---O---O---O\n")
                field4.mesh(3) should be("O---O\n" + "| B |\n" + "| B |\n" + "O---O\n")
                field4.rowSize should be(1)
                field6.mesh() should be(
                    "O-------O-------O-------O\n" +
                    "|   B   |   B   |   B   |\n" +
                    "|   B   |   B   |   B   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   B   |   B   |   B   |\n" +
                    "|   B   |   B   |   B   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   B   |   B   |   B   |\n" +
                    "|   B   |   B   |   B   |\n" +
                    "O-------O-------O-------O\n")
            }
        }
        "initialized Empty" should {
            val field = new Field(3, 3, Filled.Empty)
            "be empty initially" in {
                field.toString should be(
                    "O-------O-------O-------O\n" +
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n")
            }
            "have Blue and Red after put()" in {
                field.put(Filled.Blue, 0, 0).put(Filled.Red, 1, 1).toString should be(
                    "O-------O-------O-------O\n" +
                    "|   B   |   E   |   E   |\n" +
                    "|   B   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   E   |   R   |   E   |\n" +
                    "|   E   |   R   |   E   |\n" +
                    "O-------O-------O-------O\n" +
                    "|   E   |   E   |   E   |\n" +
                    "|   E   |   E   |   E   |\n" +
                    "O-------O-------O-------O\n")
            }
        }
    }
}