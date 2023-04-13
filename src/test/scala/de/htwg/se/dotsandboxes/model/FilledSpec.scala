package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.Assertions._

class FilledSpec extends AnyWordSpec {
    "Filled" when {
        "accessing" should {
            "have the right string representation" in {
                val empty = Filled.Empty
                empty.toString should be("E")
                val blue = Filled.Blue
                blue.toString should be("B")
                val red = Filled.Red
                red.toString should be("R")
            } 
        }
    }
}