package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ConnectorsSpec extends AnyWordSpec {
    "Connectores" when {
        "accessing" should {
            "have the right string representation" in {
                val dot = Connectors("O")
                dot should be("O")
                val emptyRow = Connectors("-")
                emptyRow should be("-")
                val connectedRow = Connectors("=")
                connectedRow should be("=")
                val emptyColumn = Connectors("¦")
                emptyColumn should be("¦")
                val connectedColumn = Connectors("‖")
                connectedColumn should be("‖")
                val empty = Connectors("")
                empty should be("")
                Connectors.apply("O") should be("O")

                val dot2 = new Dot
                dot2.stringRepresentation should be("O")
                dot2.toString should be("O")
                dot2 should be(dot)
            }
        }
    }
}
