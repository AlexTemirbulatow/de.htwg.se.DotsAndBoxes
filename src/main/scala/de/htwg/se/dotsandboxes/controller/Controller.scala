package de.htwg.se.dotsandboxes
package controller

import model.Field
import model.Status
import model.Move
import model.Matrix
import model.Player
import util.Observable

case class Controller(var field: Field) extends Observable:
  override def toString: String = field.toString + "\n" + field.currentPlayer + "s turn\n[points: " + field.currentPoints + "]\n"
  def put(move: Move): Field = field.putCell(move.vec, move.x, move.y, move.status)
  def gameEnd = field.isFinished
  def playerPoints = field.currentPoints
  def winner = field.winner
  def stats = field.stats
  def publish(doThis: Move => Field, move: Move): Unit =
    field = doThis(move)
    val preStatus = field.currentStatus
    field.isEdge(move) match
      case true  => field = field.doEdge(move.vec, move.x, move.y)
      case false => move.vec match
        case 1 => field = field.doMid("downcase", move.x, move.y).doMid("upcase", move.x, move.y)
        case 2 => field = field.doMid("rightcase", move.x, move.y).doMid("leftcase", move.x, move.y)
    val postStatus = field.currentStatus
    val difference = preStatus.indices.map(x => preStatus(x).zip(postStatus(x)).count(x => x._1 != x._2)).sum
    field = difference match
      case 0 => field.nextPlayer
      case 1 => field.addPoint.updatePlayer
      case 2 => field.addPoint.addPoint.updatePlayer
    notifyObservers
