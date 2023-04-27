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
  def doAndPublish(doThis: Move => Field, move: Move): Unit =
    field = doThis(move)
    field = field.check(move.vec, move.x, move.y)
    notifyObservers
  override def toString: String = field.toString
