package de.htwg.se.dotsandboxes
package util.playerState

import model.fieldComponent.FieldInterface

object NextPlayer extends PlayerState:
    override def handle(field: FieldInterface): FieldInterface = field.nextPlayer
