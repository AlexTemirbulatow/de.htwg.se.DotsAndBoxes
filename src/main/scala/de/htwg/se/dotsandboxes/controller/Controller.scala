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

  def put(move: Move): Field = undoManager.doStep(field, PutCommand(move, field))
  def undo: Field = undoManager.undoStep(field)
  def redo: Field = undoManager.redoStep(field)

  def gameEnd: Boolean = field.isFinished
  def playerPoints: Int = field.currentPoints
  def winner: String = field.winner
  def stats: String = field.stats

  def publish(doThis: => Field) =
    field = doThis
    notifyObservers
  def publish(doThis: Move => Field, move: Move) = moveCheck_Line.handle(move) match
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
    def handle(move: Move): Try[Any]

  class CheckLine(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Try[Any] =
      (move.vec > 0 && move.vec < 3) match
        case false => Failure(new MatchError("<Line> index failed the check. Try again:"))
        case true  => 
          next match
            case Some(h: MoveHandler) => h.handle(move)
            case None => Failure(new Exception("could not handle."))

  class CheckX(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Try[Any] =
      (move.x >= 0 && move.x <= field.maxPosX) match
        case false => Failure(new MatchError("<X> coordinate failed the check. Try again:"))
        case true  =>
          next match
            case Some(h: MoveHandler) => h.handle(move)
            case None => Failure(new Exception("could not handle."))

  class CheckY(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Try[Any] =
      (move.y >= 0 && move.y <= field.maxPosY) match
        case false => Failure(new MatchError("<Y> coordinate failed the check. Try again:"))
        case true  =>
          next match
            case Some(h: MoveHandler) => h.handle(move)
            case None => Failure(new Exception("could not handle."))

  class CheckAvailable(val next: Option[MoveHandler]) extends MoveHandler:
    override def handle(move: Move): Try[Any] =
      field.getCell(move.vec, move.x, move.y) match
        case true  => Failure(new MatchError("This line is already taken. Try again:"))
        case false =>
          next match
            case Some(h: MoveHandler) => Success(h.handle(move))
            case None => Success(true)
