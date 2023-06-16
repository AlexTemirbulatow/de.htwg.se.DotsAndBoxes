package de.htwg.se.dotsandboxes.model
package matrixComponent.matrixImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class StatusSpec extends AnyWordSpec {
    "Status" when {
        "accessing" should {
            "have the right string representation" in {
                val empty = Status.Empty
                empty.toString should be("-")
                val blue = Status.Blue
                blue.toString should be("B")
                val red = Status.Red
                red.toString should be("R")
                val green = Status.Green
                green.toString should be("G")
                val yellow = Status.Yellow
                yellow.toString should be("Y")
            } 
        }
    }
}
