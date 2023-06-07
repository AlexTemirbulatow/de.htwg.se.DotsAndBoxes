package de.htwg.se.dotsandboxes
package controller.controllerComponent

import model.{Field, Move, Status}
import util.{Command, UndoManager}

class PutCommand(move: Move, var field: Field) extends Command:
  override def doStep(field: Field): Field = field.putCell(move.vec, move.x, move.y, move.status)
  override def undoStep(field: Field): Field =
    val temp = this.field
    this.field = field
    temp
  override def redoStep(field: Field): Field =
    val temp = this.field
    this.field = field
    temp
