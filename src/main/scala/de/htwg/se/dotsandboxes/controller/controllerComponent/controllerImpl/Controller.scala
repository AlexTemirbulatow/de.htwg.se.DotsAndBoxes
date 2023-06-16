package de.htwg.se.dotsandboxes
package controller.controllerComponent
package controllerImpl

import scala.util.{Success, Failure}

import Default.given

import util._
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Move
import model.matrixComponent.matrixImpl.Player


class Controller(using var field: FieldInterface) extends ControllerInterface:
  /* setup chain */
  val moveCheck_Available = CheckAvailable(None)
  val moveCheck_Y = CheckY(Some(moveCheck_Available))
  val moveCheck_X = CheckX(Some(moveCheck_Y))
  val moveCheck_Line = CheckLine(Some(moveCheck_X))

  /* setup undo manager */
  val undoManager = new UndoManager

  override def put(move: Move): FieldInterface = undoManager.doStep(field, PutCommand(move, field))
  override def get(vec: Int, x: Int, y: Int): Any = field.getCell(vec, x, y)
  override def undo: FieldInterface = undoManager.undoStep(field)
  override def redo: FieldInterface = undoManager.redoStep(field)
  override def abort: Unit = notifyObservers(Event.Abort)

  override def colSize(row: Int = 0, col: Int = 0): Int = field.colSize(row, col)
  override def rowSize(row: Int = 0): Int = field.rowSize(row)
  override def playerList: Vector[Player] = field.playerList
  override def currentPlayer: String = field.currentPlayer
  override def currentPoints: Int = field.currentPoints
  override def gameEnded: Boolean = field.isFinished
  override def winner: String = field.winner
  override def stats: String = field.stats

  override def publish(doThis: => FieldInterface): Unit =
    field = doThis
    notifyObservers(Event.Move)
  override def publish(doThis: Move => FieldInterface, move: Move): Unit = moveCheck_Line.handle(move, field) match
    case Failure(exception) => print(exception.getMessage.dropRight(28))
    case Success(value) =>
      field = doThis(move)
      val preStatus = field.currentStatus
      field = MoveStratagy.decideMove(move, field)
      val postStatus = field.currentStatus
      field = PlayerStratagy.updatePlayer(field, preStatus, postStatus)
      notifyObservers(Event.Move)
      if gameEnded then notifyObservers(Event.End)

  override def toString: String =
    def moveString: String = if !gameEnded then "Your Move <Line><X><Y>: " else ""
    s"\n\n${field.toString}\n${currentPlayer}s turn\n[points: ${currentPoints}]\n\n${moveString}"
