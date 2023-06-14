package de.htwg.se.dotsandboxes
package util

import model.fieldComponent.FieldInterface

trait Command:
  def doStep(field: FieldInterface): FieldInterface
  def undoStep(field: FieldInterface): FieldInterface
  def redoStep(field: FieldInterface): FieldInterface

class UndoManager:
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(field: FieldInterface, command: Command): FieldInterface =
    undoStack = command :: undoStack
    command.doStep(field)

  def undoStep(field: FieldInterface): FieldInterface =
    undoStack match
      case Nil => field
      case head :: stack =>
        val result = head.undoStep(field)
        undoStack = stack
        redoStack = head :: redoStack
        result

  def redoStep(field: FieldInterface): FieldInterface =
    redoStack match
      case Nil => field
      case head :: stack =>
        val result = head.redoStep(field)
        redoStack = stack
        undoStack = head :: undoStack
        result
