package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ConnectorsSpec extends AnyWordSpec {
    "Connectores" when {
        "accessing" should {
            "have the right string representation" in {
                Connectors("O") should be("O")
                Connectors("-") should be("-")
                Connectors("=") should be("=")
                Connectors("¦") should be("¦")
                Connectors("‖") should be("‖")
                Connectors("")  should be("")

                Connectors.apply("O") should be(new Dot().toString)
                Connectors.apply("-") should be(new EmptyRow().toString)
                Connectors.apply("=") should be(new ConnectedRow().toString)
                Connectors.apply("¦") should be(new EmptyColumn().toString)
                Connectors.apply("‖") should be(new ConnectedColumn().toString)
                Connectors.apply("")  should be(new Empty().toString)
            }
        }
    }
}
