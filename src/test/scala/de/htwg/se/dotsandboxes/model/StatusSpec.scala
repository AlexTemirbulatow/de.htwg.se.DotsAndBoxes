package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class StatusSpec extends AnyWordSpec {
    "Status" when {
        "accessing" should {
            "have the right string representation" in {
                val empty = Status.Empty
                empty.toString should be("E")
                val blue = Status.Blue
                blue.toString should be("B")
                val red = Status.Red
                red.toString should be("R")
            } 
        }
    }
}
