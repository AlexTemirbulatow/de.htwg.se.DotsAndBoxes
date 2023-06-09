package de.htwg.se.dotsandboxes
package util

import scala.util.{Try, Success, Failure}

import model.fieldComponent.fieldImpl.Move
import model.fieldComponent.FieldInterface


/* chain of responsibility */
trait MoveHandler:
  val next: Option[MoveHandler]
  def handle(move: Move, field: FieldInterface): Try[Any]

class CheckLine(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: FieldInterface): Try[Any] =
    (move.vec > 0 && move.vec < 3) match
      case false => Failure(new MatchError("\n<Line> index failed the check. Try again: "))
      case true  => next match
        case Some(nextHandler: MoveHandler) => nextHandler.handle(move, field)
        case None => Failure(new Exception("could not handle."))

class CheckX(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: FieldInterface): Try[Any] =
    (move.x >= 0 && move.x <= field.maxPosX) match
      case false => Failure(new MatchError("\n<X> coordinate failed the check. Try again: "))
      case true  => next match
        case Some(nextHandler: MoveHandler) => nextHandler.handle(move, field)
        case None => Failure(new Exception("could not handle."))

class CheckY(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: FieldInterface): Try[Any] =
    (move.y >= 0 && move.y <= field.maxPosY) match
      case false => Failure(new MatchError("\n<Y> coordinate failed the check. Try again: "))
      case true  => next match
        case Some(nextHandler: MoveHandler) => nextHandler.handle(move, field)
        case None => Failure(new Exception("could not handle."))

class CheckAvailable(val next: Option[MoveHandler]) extends MoveHandler:
  override def handle(move: Move, field: FieldInterface): Try[Any] =
    field.getCell(move.vec, move.x, move.y) match
      case true  => Failure(new MatchError("\nThis line is already taken. Try again: "))
      case false => next match
        case Some(nextHandler: MoveHandler) => nextHandler.handle(move, field)
        case None => Success("Move was successful!")
