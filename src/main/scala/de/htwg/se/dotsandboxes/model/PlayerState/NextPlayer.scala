package de.htwg.se.dotsandboxes.model
package PlayerState

object NextPlayer extends PlayerState:
    override def handle(field: Field): Field =
        var state = field.nextPlayer
        field
