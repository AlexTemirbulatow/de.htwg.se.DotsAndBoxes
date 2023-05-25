package de.htwg.se.dotsandboxes
package controller

import model._
import model.MoveState._
import model.PlayerState._
import util.{Observable, Command, UndoManager}
import scala.util.{Try, Success, Failure}

case class Controller(var field: Field) extends Observable:

  /* setup chain */
  val moveCheck_Available = new CheckAvailable(None)
  val moveCheck_Y = new CheckY(Some(moveCheck_Available))
  val moveCheck_X = new CheckX(Some(moveCheck_Y))
  val moveCheck_Line = new CheckLine(Some(moveCheck_X))

  /* setup undo manager */
  val undoManager = new UndoManager

  def gameEnd: Boolean = field.isFinished
  def playerPoints: Int = field.currentPoints
  def winner: String = field.winner
  def stats: String = field.stats

  def put(move: Move): Field = undoManager.doStep(field, PutCommand(move, field))
  def undo: Field = undoManager.undoStep(field)
  def redo: Field = undoManager.redoStep(field)

  def publish(doThis: => Field) =
    field = doThis
    notifyObservers
  def publish(doThis: Move => Field, move: Move) = Try(moveCheck_Line.handle(move)) match
    case Failure(exception) => println(exception.getMessage.dropRight(28))
    case Success(value) =>
      field = doThis(move)
      val preStatus = field.currentStatus
      field = StrategyMove.decideMove(move)
      val postStatus = field.currentStatus
      field = StrategyPlayer.updatePlayer(preStatus, postStatus)
      notifyObservers

  override def toString: String = 
    field.toString + "\n" + field.currentPlayer + "s turn\n[points: " + field.currentPoints + "]\n"



/* strategy pattern */
  object StrategyMove:
    def decideMove(move: Move): Field =
      if(field.isEdge(move)) EdgeState.handle(move, field) else MidState.handle(move, field)

  object StrategyPlayer:
    def updatePlayer(preStatus: Vector[Vector[Any]], postStatus: Vector[Vector[Any]]): Field =
      val difference = preStatus.indices.map(x => preStatus(x).zip(postStatus(x)).count(x => x._1 != x._2)).sum
      if(difference.equals(1)) AddOnePoint.handle(field)
      else if(difference.equals(2)) AddTwoPoints.handle(field)
      else NextPlayer.handle(field)


/* chain of responsibility */
  trait MoveHandler:
    val next: Option[MoveHandler]
    def handle(move: Move): Boolean

  class CheckLine(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Boolean =
      (move.vec > 0 && move.vec < 3) match
        case false => throw new MatchError("<Line> index failed the check. Try again:")
        case true  => 
          next match
            case Some(h: MoveHandler) => h.handle(move)
            case None => false

  class CheckX(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Boolean =
      (move.x >= 0 && move.x <= field.maxPosX) match
        case false => throw new MatchError("<X> coordinate failed the check. Try again:")
        case true  =>
          next match
            case Some(h: MoveHandler) => h.handle(move)
            case None => false

  class CheckY(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Boolean =
      (move.y >= 0 && move.y <= field.maxPosY) match
        case false => throw new MatchError("<Y> coordinate failed the check. Try again:")
        case true  =>
          next match
            case Some(h: MoveHandler) => h.handle(move)
            case None => false

  class CheckAvailable(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Boolean =
      field.getCell(move.vec, move.x, move.y) match
        case true  => throw new MatchError("This line is already taken. Try again:")
        case false =>
          next match
            case Some(h: MoveHandler) => h.handle(move)
            case None => true
