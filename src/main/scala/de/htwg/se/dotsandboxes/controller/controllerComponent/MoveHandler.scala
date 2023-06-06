package de.htwg.se.dotsandboxes
package controller.controllerComponent

import model.{Field, Move}
import scala.util.{Try, Success, Failure}

/* chain of responsibility */
trait MoveHandler:
  val next: Option[MoveHandler]
  def handle(move: Move, field: Field): Try[Any]

class CheckLine(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: Field): Try[Any] =
    (move.vec > 0 && move.vec < 3) match
      case false => Failure(new MatchError("<Line> index failed the check. Try again:"))
      case true  => next match
        case Some(h: MoveHandler) => h.handle(move, field)
        case None => Failure(new Exception("could not handle."))

class CheckX(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: Field): Try[Any] =
    (move.x >= 0 && move.x <= field.maxPosX) match
      case false => Failure(new MatchError("<X> coordinate failed the check. Try again:"))
      case true  => next match
        case Some(h: MoveHandler) => h.handle(move, field)
        case None => Failure(new Exception("could not handle."))

class CheckY(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: Field): Try[Any] =
    (move.y >= 0 && move.y <= field.maxPosY) match
      case false => Failure(new MatchError("<Y> coordinate failed the check. Try again:"))
      case true  => next match
        case Some(h: MoveHandler) => h.handle(move, field)
        case None => Failure(new Exception("could not handle."))

class CheckAvailable(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: Field): Try[Any] =
    field.getCell(move.vec, move.x, move.y) match
      case true  => Failure(new MatchError("This line is already taken. Try again:"))
      case false => next match
        case Some(h: MoveHandler) => Success(h.handle(move, field))
        case None => Success(true)
