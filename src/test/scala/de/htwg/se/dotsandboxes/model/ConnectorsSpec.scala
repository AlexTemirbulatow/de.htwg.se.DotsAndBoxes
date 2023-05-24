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

                Connectors.apply("O") should be(new Dot().toString)
                Connectors.apply("-") should be(new EmptyRow().toString)
                Connectors.apply("=") should be(new ConnectedRow().toString)
                Connectors.apply("¦") should be(new EmptyColumn().toString)
                Connectors.apply("‖") should be(new ConnectedColumn().toString)
                Connectors.apply("") should be(new Empty().toString)
            }
        }
    }
}
