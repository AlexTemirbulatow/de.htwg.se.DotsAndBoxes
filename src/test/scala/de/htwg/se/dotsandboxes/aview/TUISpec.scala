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
            tui.welcome should be(
                "\n" +
                "---------------------------------" + "\n" +
                "¦ Welcome to Dots and Boxes TUI ¦" + "\n" +
                "---------------------------------" + "\n" +
                "\n")
            tui.finished should be(
                "\n" +
                "-----------------" + "\n" +
                "| Game finished |" + "\n" +
                "-----------------" + "\n" +
                "\n")
            tui.finalStats should be(
                "Player Blue [points: 0]\n" +
                "Player Red [points: 0]\n\n" +
                "_________________________\n\n" +
                "It's a draw!\n")
            tui.update.toString should be("()")
            val input = new ByteArrayInputStream(("").getBytes)
            Console.withIn(input) {
                assertThrows[NullPointerException] {
                    tui.run
                }
            }
        }
    }
}
