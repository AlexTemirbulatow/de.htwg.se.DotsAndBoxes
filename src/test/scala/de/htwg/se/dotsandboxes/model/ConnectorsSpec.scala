package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ConnectorsSpec extends AnyWordSpec {
    "Connectores" when {
        "accessing" should {
            "have the right string representation" in {
                val dot = Connectors.Dot
                dot.toString should be("O")
                val xLineEmpty = Connectors.EmptyX
                xLineEmpty.toString should be("-")
                val xLineConnected = Connectors.ConnectedX
                xLineConnected.toString should be("=")
                val yLineEmpty = Connectors.EmptyY
                yLineEmpty.toString should be("¦")
                val yLineConnected = Connectors.ConnectedY
                yLineConnected.toString should be("‖")
                val empty = Connectors.Empty
                empty.toString should be("")
            }
        }
    }
}
