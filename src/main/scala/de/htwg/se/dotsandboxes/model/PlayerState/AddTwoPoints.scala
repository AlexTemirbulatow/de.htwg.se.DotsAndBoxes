package de.htwg.se.dotsandboxes.model
package PlayerState

object AddTwoPoints extends PlayerState:
    override def handle(field: Field): Field =
        var state = field.addPoint.addPoint.updatePlayer
        field
