package de.htwg.se.dotsandboxes
package util

import moveState.MoveState
import model.fieldComponent.fieldImpl.Move
import model.fieldComponent.FieldInterface


/* strategy pattern */
object MoveStratagy:
  def executeStrategy(position: MoveState, move: Move, field: FieldInterface): FieldInterface =
    position.handle(move, field)

