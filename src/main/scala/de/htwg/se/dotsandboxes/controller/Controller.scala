package de.htwg.se.dotsandboxes
package controller

import model.Field
import model.Status
import model.Move
import model.Matrix
import util.Observable

case class Controller(var field: Field) extends Observable:
  def put(move: Move): Field =
    field.put(move.vec, move.x, move.y, move.status)
  def publish(doThis: Move => Field, move: Move): Unit =
    field = doThis(move)
    field.checkEdge(move) match
      case true  => field = field.doEdge(move.vec, move.x, move.y)
      case false =>
        move.vec match
          case 1 =>
            field = field.doMid(1, move.x, move.y)
            field = field.doMid(2, move.x, move.y)
          case 2 =>
            field = field.doMid(3, move.x, move.y)
            field = field.doMid(4, move.x, move.y)
    notifyObservers
  override def toString: String = field.toString
