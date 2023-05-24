package de.htwg.se.dotsandboxes
package controller

import model._
import model.MoveState._
import model.PlayerState._
import util.{Observable, GameState}

case class Controller(var field: Field) extends Observable:
  override def toString: String = field.toString + "\n" + field.currentPlayer + "s turn\n[points: " + field.currentPoints + "]\n"
  def put(move: Move): Field = field.putCell(move.vec, move.x, move.y, move.status)
  def gameEnd: Boolean = field.isFinished
  def playerPoints: Int = field.currentPoints
  def winner: String = field.winner
  def stats: String = field.stats
  def publish(doThis: Move => Field, move: Move): Field =
    field = doThis(move)
    val preStatus = field.currentStatus
    field = StrategyMove.decideMove(move)
    val postStatus = field.currentStatus
    field = StrategyPlayer.updatePlayer(preStatus, postStatus)
    field
  def stateHandler(state: GameState): Unit = state match
    case GameState.Aborted | GameState.Finished => sys.exit
    case GameState.Running => notifyObservers


/* strategy pattern */
  object StrategyMove:
    def decideMove(move: Move): Field =
      if(field.isEdge(move)) EdgeState.handle(move, field) else MidState.handle(move, field)

  object StrategyPlayer:
    def updatePlayer(preStatus: Vector[Vector[Any]], postStatus: Vector[Vector[Any]]): Field =
      val difference = preStatus.indices.map(x => preStatus(x).zip(postStatus(x)).count(x => x._1 != x._2)).sum
      if(difference.equals(1)) AddOnePoint.handle(field) else if(difference.equals(2)) AddTwoPoints.handle(field) else NextPlayer.handle(field)
