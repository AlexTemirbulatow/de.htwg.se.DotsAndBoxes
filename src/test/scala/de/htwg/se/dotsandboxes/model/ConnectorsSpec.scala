package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ConnectorsSpec extends AnyWordSpec {
    "Connectores" when {
        "accessing" should {
            "have the right string representation" in {
                val dot = Connectors.Dot
                dot.toString should be("O")
                val emptyRow = Connectors.EmptyRow
                emptyRow.toString should be("-")
                val connectedRow = Connectors.ConnectedRow
                connectedRow.toString should be("=")
                val emptyColumn = Connectors.EmptyColumn
                emptyColumn.toString should be("¦")
                val connectedColumn = Connectors.ConnectedColumn
                connectedColumn.toString should be("‖")
                val empty = Connectors.Empty
                empty.toString should be("")
            }
        }
    }
}
