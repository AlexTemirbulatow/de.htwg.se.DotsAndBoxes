package de.htwg.se.dotsandboxes
package controller

import util._
import model._
import model.MoveState._
import model.PlayerState._
import controllerComponent._
import scala.util.{Try, Success, Failure}

case class Controller(var field: Field) extends Observable:

  /* setup chain */
  val moveCheck_Available = CheckAvailable(None)
  val moveCheck_Y = CheckY(Some(moveCheck_Available))
  val moveCheck_X = CheckX(Some(moveCheck_Y))
  val moveCheck_Line = CheckLine(Some(moveCheck_X))

  /* setup undo manager */
  val undoManager = new UndoManager

  def put(move: Move): Field = undoManager.doStep(field, PutCommand(move, field))
  def get(vec: Int, x: Int, y: Int): Any = field.getCell(vec, x, y)
  def undo: Field = undoManager.undoStep(field)
  def redo: Field = undoManager.redoStep(field)
  def abort = notifyObservers(Event.Abort)

  def currentPlayer: String = field.currentPlayer
  def currentPoints: Int = field.currentPoints
  def gameEnded: Boolean = field.isFinished
  def winner: String = field.winner
  def stats: String = field.stats

  def publish(doThis: => Field): Unit =
    field = doThis
    notifyObservers(Event.Move)
  def publish(doThis: Move => Field, move: Move): Unit = moveCheck_Line.handle(move, field) match
    case Failure(exception) => println(exception.getMessage.dropRight(28))
    case Success(value) =>
      field = doThis(move)
      val preStatus = field.currentStatus
      field = StrategyMove.decideMove(move, field)
      val postStatus = field.currentStatus
      field = StrategyPlayer.updatePlayer(field, preStatus, postStatus)
      notifyObservers(Event.Move)
      if gameEnded then notifyObservers(Event.End)

  override def toString = s"${field.toString}\n${currentPlayer}s turn\n[points: ${currentPoints}]\n"
