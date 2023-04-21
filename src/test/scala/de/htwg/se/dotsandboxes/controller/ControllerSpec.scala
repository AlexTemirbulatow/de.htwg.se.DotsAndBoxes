package de.htwg.se.dotsandboxes
package controller

import model.Field
import model.Status
import util.Observer
import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec {
    "The Controller" should {
        val controller = Controller(new Field(2, 2, Status.Empty))
        "put a connected line on the field when a move is made" in {
            val fieldWithMove = controller.put(1, 0, 0, true)
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
            controller.put(1, 0, 0, true)
            testObserver.bing should be(true)
        }
    }
}
