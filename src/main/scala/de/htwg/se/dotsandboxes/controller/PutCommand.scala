package de.htwg.se.dotsandboxes
package controller

import model.{Field, Move, Status}
import util.{Command, UndoManager}

class PutCommand(move: Move) extends Command:
  override def doStep(field: Field): Field = field.putCell(move.vec, move.x, move.y, move.status)
  override def undoStep(field: Field): Field = field.putCell(move.vec, move.x, move.y, false)
  override def redoStep(field: Field): Field = field.putCell(move.vec, move.x, move.y, move.status)
