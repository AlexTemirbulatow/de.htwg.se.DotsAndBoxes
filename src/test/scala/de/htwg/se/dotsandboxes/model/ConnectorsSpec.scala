package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ConnectorsSpec extends AnyWordSpec {
    "Connectores" when {
        "accessing" should {
            "have the right string representation" in {
                val dot = Connectors.Dot
                dot.toString should be("O")
                val xLineEmpty = Connectors.EmptyRow
                xLineEmpty.toString should be("-")
                val xLineConnected = Connectors.ConnectedRow
                xLineConnected.toString should be("=")
                val yLineEmpty = Connectors.EmptyColumn
                yLineEmpty.toString should be("¦")
                val yLineConnected = Connectors.ConnectedColumn
                yLineConnected.toString should be("‖")
                val empty = Connectors.Empty
                empty.toString should be("")
            }
        }
    }
}
