package de.htwg.se.dotsandboxes.model.fieldComponent.fieldImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class MoveSpec extends AnyWordSpec {
    "A Move" should {
        "have a vector index, x and y coordinate and a status" in {
            val move = Move(1, 1, 0, true)
            move.vec should be(1)
            move.x should be(1)
            move.y should be(0)
            move.status shouldBe true
        }
    }
}
