package de.htwg.se.dotsandboxes
package controller.controllerComponent.controllerImpl

import util.Command
import model.fieldComponent.fieldImpl.Move
import model.fieldComponent.FieldInterface


class PutCommand(move: Move, var field: FieldInterface) extends Command:
  override def doStep(field: FieldInterface): FieldInterface = field.putCell(move.vec, move.x, move.y, move.status)
  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp
  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp
