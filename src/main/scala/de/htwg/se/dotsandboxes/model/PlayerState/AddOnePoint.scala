package de.htwg.se.dotsandboxes.model
package PlayerState

object AddOnePoint extends PlayerState:
    override def handle(field: Field): Field =
        var state = field.addPoint.updatePlayer
        field
