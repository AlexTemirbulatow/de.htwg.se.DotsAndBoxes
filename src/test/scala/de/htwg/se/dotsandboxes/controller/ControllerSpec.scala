package de.htwg.se.dotsandboxes
package controller

import model.Field
import model.Status
import model.Move
import util.Observer
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec {
    val controller = Controller(new Field(2, 2, Status.Empty))
    "The Controller" should {
        "put a connected line on the field when a move is made" in {
            val fieldWithMove = controller.put(Move(1, 0, 0, true))
            fieldWithMove.get(1, 0, 0) should === (true)
            fieldWithMove.get(1, 0, 1) should === (false)
        }
        "notify its observers on change" in {
            class TestObserver(controller: Controller) extends Observer:
                controller.add(this)
                var bing = false
                def update = bing = true
            val testObserver = TestObserver(controller)
            testObserver.bing should be(false)
            controller.publish(controller.put, Move(1, 0, 0, true))
            testObserver.bing should be(true)
            controller.toString should be(
                "O=======O-------O\n" +
                "¦   E   ¦   E   ¦\n" +
                "¦   E   ¦   E   ¦\n" +
                "O-------O-------O\n" +
                "¦   E   ¦   E   ¦\n" +
                "¦   E   ¦   E   ¦\n" +
                "O-------O-------O\n")
            controller.remove(testObserver)
        }
    }
}
