package de.htwg.se.dotsandboxes
package controller

import model._
import model.MoveState._
import model.Player._
import model.MoveState.MidState
import util.Observable
import util.GameState
import de.htwg.se.dotsandboxes.model.PlayerState.AddOnePoint
import de.htwg.se.dotsandboxes.model.PlayerState.AddTwoPoints
import de.htwg.se.dotsandboxes.model.PlayerState.NextPlayer

case class Controller(var field: Field) extends Observable:
  override def toString: String = field.toString + "\n" + field.currentPlayer + "s turn\n[points: " + field.currentPoints + "]\n"
  def put(move: Move): Field = field.putCell(move.vec, move.x, move.y, move.status)
  def gameEnd = field.isFinished
  def playerPoints = field.currentPoints
  def winner = field.winner
  def stats = field.stats
  def publish(doThis: Move => Field, move: Move) =
    field = doThis(move)
    val preStatus = field.currentStatus
    field = StrategyMove.decideMove(move)
    val postStatus = field.currentStatus
    field = StrategyPlayer.updatePlayer(preStatus, postStatus)
    field
  def stateHandler(state: GameState) = state match
    case GameState.Aborted | GameState.Finished => sys.exit
    case GameState.Running => notifyObservers


  object StrategyMove:
    def decideMove(move: Move): Field = if(field.isEdge(move)) EdgeState.handle(move, field) else MidState.handle(move, field)

  object StrategyPlayer:
    def updatePlayer(preStatus: Vector[Vector[Any]], postStatus: Vector[Vector[Any]]) =
      val difference = preStatus.indices.map(x => preStatus(x).zip(postStatus(x)).count(x => x._1 != x._2)).sum
      if(difference.equals(1)) AddOnePoint.handle(field) else if(difference.equals(2)) AddTwoPoints.handle(field) else NextPlayer.handle(field)
