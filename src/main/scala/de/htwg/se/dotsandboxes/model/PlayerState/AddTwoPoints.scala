package de.htwg.se.dotsandboxes.model
package PlayerState

object AddTwoPoints extends PlayerState:
    override def handle(field: Field): Field =
        val state = field.addPoints(2).updatePlayer
        state
