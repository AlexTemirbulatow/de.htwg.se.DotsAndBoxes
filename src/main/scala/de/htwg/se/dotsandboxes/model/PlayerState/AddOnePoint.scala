package de.htwg.se.dotsandboxes.model
package PlayerState

object AddOnePoint extends PlayerState:
    override def handle(field: Field): Field =
        val state = field.addPoints(1).updatePlayer
        state
