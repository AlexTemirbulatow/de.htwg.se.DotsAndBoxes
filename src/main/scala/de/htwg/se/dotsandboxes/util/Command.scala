package de.htwg.se.dotsandboxes
package util

import model.Field

trait Command:
  def doStep(field: Field): Field
  def undoStep(field: Field): Field
  def redoStep(field: Field): Field

class UndoManager:
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(field: Field, command: Command): Field =
    undoStack = command :: undoStack
    command.doStep(field)

  def undoStep(field: Field): Field =
    undoStack match
      case Nil => field
      case head :: stack =>
        val result = head.undoStep(field)
        undoStack = stack
        redoStack = head :: redoStack
        result

  def redoStep(field: Field): Field =
    redoStack match
      case Nil => field
      case head :: stack =>
        val result = head.redoStep(field)
        redoStack = stack
        undoStack = head :: undoStack
        result
