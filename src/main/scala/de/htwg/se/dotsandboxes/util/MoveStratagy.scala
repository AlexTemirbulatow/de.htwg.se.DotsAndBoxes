package de.htwg.se.dotsandboxes
package util

import moveState.{EdgeState, MidState}
import model.fieldComponent.fieldImpl.Move
import model.fieldComponent.FieldInterface


/* strategy pattern */
object MoveStratagy:
  def decideMove(move: Move, field: FieldInterface): FieldInterface =
    if(field.isEdge(move)) EdgeState.handle(move, field) else MidState.handle(move, field)
