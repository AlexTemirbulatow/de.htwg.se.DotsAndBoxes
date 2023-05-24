package de.htwg.se.dotsandboxes.model
package PlayerState

object NextPlayer extends PlayerState:
    override def handle(field: Field): Field =
        val state = field.nextPlayer
        state
