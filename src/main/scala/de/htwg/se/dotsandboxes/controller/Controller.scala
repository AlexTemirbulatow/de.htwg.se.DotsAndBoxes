package de.htwg.se.dotsandboxes
package controller

import model.Field
import model.Status
import util.Observable

case class Controller(var field: Field) extends Observable:
  def put(vecIndex: Int, x: Int, y: Int, filling: Any): Unit =
    field = field.put(vecIndex, x, y, filling)
    notifyObservers
  override def toString: String = field.toString
  