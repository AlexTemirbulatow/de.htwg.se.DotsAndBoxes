package de.htwg.se.dotsandboxes
package aview

import controller.Controller
import model.Field
import model.Status
import model.Move
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import java.io.ByteArrayInputStream

class TuiSpec extends AnyWordSpec {

    "The TUI" should {
        val tui = TUI(Controller(new Field(3, 3, Status.Empty, 2)))
        "recognize the input as move of line connector" in {
            tui.analyseInput("100") should be(Some(Move(1, 0, 0, true)))
            tui.analyseInput("210") should be(Some(Move(2, 1, 0, true)))
            tui.analyseInput("q") should be(None)
        }
        "print the correct form of String" in {
            tui.finalStats should be(
                "Player Blue [points: 0]\n" +
                "Player Red [points: 0]\n\n" +
                "_________________________\n\n" +
                "It's a draw!\n")
        }
        "idle testing" in {
            tui.update.toString should be("()")
            val input = new ByteArrayInputStream(("").getBytes)
            Console.withIn(input) {
                assertThrows[NullPointerException] {
                    tui.run
                }
            }
            Console.withIn(input) {
                    assertThrows[NullPointerException] {
                    tui.gameLoop
                }
            }
            val input2 = new ByteArrayInputStream(("idle").getBytes)
            Console.withIn(input2) {
                    assertThrows[NumberFormatException] {
                    tui.gameLoop
                }
            }
            val input3 = new ByteArrayInputStream(("q").getBytes)
            Console.withIn(input3) {
                    assertThrows[NullPointerException] {
                    tui.gameLoop
                }
            }
            val input4 = new ByteArrayInputStream(("111").getBytes)
            Console.withIn(input4) {
                    assertThrows[NullPointerException] {
                    tui.gameLoop
                }
            }
            val tui2 = TUI(Controller(new Field(1, 1, Status.Empty, 2).putCell(1, 0, 0, true).putCell(1, 1, 0, true).putCell(2, 0, 0, true).putCell(2, 0, 1, true)))
            Console.withIn(input4) {
                    assertThrows[NullPointerException] {
                    tui2.gameLoop
                }
            }
        }
    }
}
