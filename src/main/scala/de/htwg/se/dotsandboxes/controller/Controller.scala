package de.htwg.se.dotsandboxes
package controller

import model._
import util.Observable
import util.GameState

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
    def decideMove(move: Move) = if(field.isEdge(move)) doEdge(move) else doMid(move)
    def doEdge(move: Move) = EdgeState.handle(move)
    def doMid(move: Move) = MidState.handle(move)

  object StrategyPlayer:
    def updatePlayer(preStatus: Vector[Vector[Any]], postStatus: Vector[Vector[Any]]) =
      val difference = preStatus.indices.map(x => preStatus(x).zip(postStatus(x)).count(x => x._1 != x._2)).sum
      if(difference.equals(1)) addOnePoint else if(difference.equals(2)) addTwoPoints else nextPlayer
    def nextPlayer = field.nextPlayer
    def addOnePoint = field.addPoint.updatePlayer
    def addTwoPoints = field.addPoint.addPoint.updatePlayer

  object MidState:
    var state = field
    def handle(move: Move) =
      state = move.vec match
        case 1 => horizontalState(move)
        case 2 => verticalState(move)
      state
    def horizontalState(move: Move) = field.checkSquare("downcase", move.x, move.y).checkSquare("upcase", move.x, move.y)
    def verticalState(move: Move) = field.checkSquare("rightcase", move.x, move.y).checkSquare("leftcase", move.x, move.y)

  object EdgeState:
    var state = field
    def handle(move: Move) =
      state = (move.vec, move.x, move.y) match
        case (1, 0, _) => downCase(move.x, move.y)
        case (1, x, _) if x == field.maxPosX => upCase(move.x, move.y)
        case (2, _, 0) => rightCase(move.x, move.y)
        case (2, _, y) if y == field.maxPosY => leftCase(move.x, move.y)
      state
    def downCase(x: Int, y: Int) = field.checkSquare("downcase", x, y)
    def upCase(x: Int, y: Int) = field.checkSquare("upcase", x, y)
    def rightCase(x: Int, y: Int) = field.checkSquare("rightcase", x, y)
    def leftCase(x: Int, y: Int) = field.checkSquare("leftcase", x, y)
