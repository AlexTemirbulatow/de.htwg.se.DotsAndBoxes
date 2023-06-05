package de.htwg.se.dotsandboxes
package controller

import util._
import model.{Field, Status, Move}
import java.io.StringReader
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import org.scalactic.exceptions.NullArgumentException


class ControllerSpec extends AnyWordSpec {
    val controller = Controller(new Field(3, 3, Status.Empty, 3))
    "The Controller" should {
        "put a connected line on the field when a move is made" in {
            val fieldWithMove = controller.put(Move(1, 0, 0, true))
            fieldWithMove.getCell(1, 0, 0) shouldBe true
            fieldWithMove.getCell(1, 0, 1) shouldBe false
        }
        "notify its observers on change" in {
            class TestObserver(controller: Controller) extends Observer:
                controller.add(this)
                var bing = false
                def update(e: Event) = bing = true
            val testObserver = TestObserver(controller)

            testObserver.bing shouldBe false
            controller.publish(controller.put, Move(1, 0, 0, true))
            controller.gameEnded shouldBe false
            testObserver.bing shouldBe true
            controller.toString should be(
                "O=======O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n\n" +
                "Reds turn\n" +
                "[points: 0]\n")
            controller.publish(controller.put, Move(1, 1, 1, true))
            controller.toString should be(
                "O=======O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O=======O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n\n" +
                "Greens turn\n" +
                "[points: 0]\n")
            controller.publish(controller.put, Move(2, 0, 1, true))
            controller.toString should be(
                "O=======O-------O-------O\n" +
                "¦   -   ‖   -   ¦   -   ¦\n" +
                "¦   -   ‖   -   ¦   -   ¦\n" +
                "O-------O=======O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n\n" +
                "Blues turn\n" +
                "[points: 0]\n")
            controller.publish(controller.put, Move(2, 0, 0, true))
            controller.publish(controller.put, Move(1, 1, 0, true))
            controller.currentPoints should be(1)
            controller.toString should be(
                "O=======O-------O-------O\n" +
                "‖   R   ‖   -   ¦   -   ¦\n" +
                "‖   R   ‖   -   ¦   -   ¦\n" +
                "O=======O=======O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n\n" +
                "Reds turn\n" +
                "[points: 1]\n")
            controller.publish(controller.put, Move(1, 0, 1, true))
            controller.publish(controller.put, Move(1, 0, 2, true))
            controller.publish(controller.put, Move(1, 1, 2, true))
            controller.publish(controller.put, Move(2, 0, 3, true))
            controller.publish(controller.put, Move(2, 0, 2, true))
            controller.toString should be(
                "O=======O=======O=======O\n" +
                "‖   R   ‖   G   ‖   G   ‖\n" +
                "‖   R   ‖   G   ‖   G   ‖\n" +
                "O=======O=======O=======O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n\n" +
                "Greens turn\n" +
                "[points: 2]\n")
            controller.publish(controller.put, Move(2, 1, 0, true))
            controller.publish(controller.put, Move(2, 1, 1, true))
            controller.publish(controller.put, Move(2, 1, 2, true))
            controller.publish(controller.put, Move(1, 2, 0, true))
            controller.currentPoints should be(3)
            controller.publish(controller.put, Move(2, 1, 3, true))
            controller.publish(controller.put, Move(2, 2, 0, true))
            controller.publish(controller.put, Move(2, 2, 1, true))
            controller.publish(controller.put, Move(2, 2, 2, true))
            controller.publish(controller.put, Move(2, 2, 3, true))
            controller.publish(controller.put, Move(1, 2, 1, true))
            controller.publish(controller.put, Move(1, 2, 2, true))
            controller.publish(controller.put, Move(1, 3, 0, true))
            controller.publish(controller.put, Move(1, 3, 1, true))
            controller.publish(controller.put, Move(1, 3, 2, true))
            controller.currentPoints should be(6)
            controller.toString should be(
                "O=======O=======O=======O\n" +
                "‖   R   ‖   G   ‖   G   ‖\n" +
                "‖   R   ‖   G   ‖   G   ‖\n" +
                "O=======O=======O=======O\n" +
                "‖   G   ‖   R   ‖   R   ‖\n" +
                "‖   G   ‖   R   ‖   R   ‖\n" +
                "O=======O=======O=======O\n" +
                "‖   R   ‖   R   ‖   R   ‖\n" +
                "‖   R   ‖   R   ‖   R   ‖\n" +
                "O=======O=======O=======O\n\n" +
                "Reds turn\n" +
                "[points: 6]\n")

            controller.gameEnded shouldBe true
            controller.winner should be("Player Red wins!")
            controller.stats should be(
                "Player Blue [points: 0]\n" +
                "Player Red [points: 6]\n" +
                "Player Green [points: 3]")
            
            controller.remove(testObserver)
        }
        "be able to undo and redo" in {
            val controller = Controller(new Field(3, 3, Status.Empty, 2))
            class TestObserver(controller: Controller) extends Observer:
                controller.add(this)
                var bing = false
                def update(e: Event) = bing = true
            val testObserver = TestObserver(controller)
            controller.publish(controller.put, Move(1, 0, 0, true))
            controller.publish(controller.put, Move(1, 1, 0, true))
            controller.publish(controller.put, Move(2, 0, 0, true))
            controller.publish(controller.put, Move(2, 0, 1, true))

            controller.field.getCell(0, 0, 0) should be(Status.Red)
            controller.field.getCell(2, 0, 1) shouldBe true

            controller.publish(controller.undo)
            controller.field.getCell(0, 0, 0) should be(Status.Empty)
            controller.field.getCell(2, 0, 1) shouldBe false

            controller.publish(controller.redo)
            controller.field.getCell(0, 0, 0) should be(Status.Red)
            controller.field.getCell(2, 0, 1) shouldBe true
        }
        "deny wrong input" in {
            val controller = Controller(new Field(3, 3, Status.Empty, 2))
            class TestObserver(controller: Controller) extends Observer:
                controller.add(this)
                var bing = false
                def update(e: Event) = bing = true
            val testObserver = TestObserver(controller)
            controller.publish(controller.put, Move(1, 0, 0, true))
            controller.publish(controller.put, Move(1, 1, 0, true))
            controller.publish(controller.put, Move(2, 0, 0, true))
            controller.publish(controller.put, Move(2, 0, 1, true))
            /* wrong inputs */
            controller.publish(controller.put, Move(4, 0, 0, true))
            controller.publish(controller.put, Move(1, 9, 0, true))
            controller.publish(controller.put, Move(2, 0, 9, true))
            /* no change */
            controller.toString should be(
                "O=======O-------O-------O\n" +
                "‖   R   ‖   -   ¦   -   ¦\n" +
                "‖   R   ‖   -   ¦   -   ¦\n" +
                "O=======O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "¦   -   ¦   -   ¦   -   ¦\n" +
                "O-------O-------O-------O\n\n" +
                "Reds turn\n" +
                "[points: 1]\n")
        }
        "create different fields based on player size input" in {
            Console.withIn(StringReader("2")) {
                PlayerMode.getInput should be(new Field(5, 4, Status.Empty, 2))
            }
            Console.withIn(StringReader("Default")) {
                Controller(PlayerMode.selectPlayerMode) should be(Controller(new Field(5, 4, Status.Empty, 2)))
            }
            Console.withIn(StringReader("2")) {
                Controller(PlayerMode.selectPlayerMode) should be(Controller(new Field(5, 4, Status.Empty, 2)))
            }/*
            Console.withIn(StringReader("3")) {
                Controller(PlayerMode.selectPlayerMode) should be(Controller(new Field(8, 6, Status.Empty, 3)))
            }
            Console.withIn(StringReader("4")) {
                Controller(PlayerMode.selectPlayerMode) should be(Controller(new Field(11, 9, Status.Empty, 4)))
            }*/
        }
    }
}
