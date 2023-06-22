package de.htwg.se.dotsandboxes
package util.playerState

import model.fieldComponent.FieldInterface


object AddTwoPoints extends PlayerState:
    override def handle(field: FieldInterface): FieldInterface = field.addPoints(points = 2).updatePlayer()
