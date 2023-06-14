package de.htwg.se.dotsandboxes.model
package matrixComponent.matrixImpl

import com.google.inject.Inject

import fieldComponent.fieldImpl.Move
import matrixComponent.MatrixInterface

case class Matrix[T] @Inject (vecStatus: Vector[Vector[Any]], vecRow: Vector[Vector[Any]], vecCol: Vector[Vector[Any]], list: Vector[Player], currentPlayer: Player) extends MatrixInterface[T]:
  def this(rowSize: Int, colSize: Int, status: Any, playerSize: Int = 2) =
    this(Vector.tabulate(colSize, rowSize) {(_, _) => status},
    Vector.tabulate (colSize + 1, rowSize) {(_, _) =>  false},
    Vector.tabulate (colSize, rowSize + 1) {(_, _) =>  false},
    new PlayerList(playerSize).playerList, list.head)
  override val maxPosX = rowSize(1) - 1
  override val maxPosY = colSize(2, 0) - 1
  override def rowSize(vecIndex: Int): Int = vector(vecIndex).size
  override def colSize(vecIndex: Int, row: Int): Int = vector(vecIndex)(row).size
  override def cell(vecIndex: Int, row: Int, col: Int): Any = vector(vecIndex)(row)(col)
  override def row(vecIndex: Int, row: Int): Vector[Any] = vector(vecIndex)(row)
  override def replaceCell(vecIndex: Int, row: Int, col: Int, cell: Any): Matrix[Any] = vecIndex match
    case 0 => copy(vecStatus = vector(0).updated(row, vector(0)(row).updated(col, cell)))
    case 1 => copy(vecRow = vector(1).updated(row, vector(1)(row).updated(col, cell)))
    case 2 => copy(vecCol = vector(2).updated(row, vector(2)(row).updated(col, cell)))
  override def vector(vecIndex: Int): Vector[Vector[Any]] = this.productElement(vecIndex).asInstanceOf[Vector[Vector[Any]]]
  override def checkSquare(thisCase: String, x: Int, y: Int): Matrix[Any] = thisCase match
    case "downcase" => if((cell(1, x + 1, y), cell(2, x, y), cell(2, x, y + 1)).toList.forall(_ == true))
      replaceCell(0, x, y, currentPlayer.status) else copy()
    case "upcase" => if((cell(1, x - 1, y), cell(2, x - 1, y), cell(2, x - 1, y + 1)).toList.forall(_ == true))
      replaceCell(0, x - 1 , y, currentPlayer.status) else copy()
    case "rightcase" => if((cell(2, x, y + 1), cell(1, x, y), cell(1, x + 1, y)).toList.forall(_ == true))
      replaceCell(0, x, y, currentPlayer.status) else copy()
    case "leftcase" => if((cell(2, x, y - 1), cell(1, x, y - 1), cell(1, x + 1, y - 1)).toList.forall(_ == true))
      replaceCell(0, x, y - 1, currentPlayer.status) else copy()
  override def isEdge(move: Move): Boolean = move.vec match
    case 1 => if(move.x == 0 || move.x == maxPosX) true else false
    case 2 => if(move.y == 0 || move.y == maxPosY) true else false
  override def currentPlayerId: String = currentPlayer.playerId
  override def currentPoints: Int = currentPlayer.points
  override def updatePlayer: Matrix[Any] = copy(currentPlayer = list(playerIndex))
  override def playerIndex: Int = list.indices.map(x => list(x).playerId).indexOf(currentPlayer.playerId)
  override def playerList: Vector[Player] = list
  override def getMatrix: MatrixInterface[Any] = this.asInstanceOf[MatrixInterface[Any]]
  override def addPoints(points: Int): Matrix[Any] = copy(list = list.updated(playerIndex, list(playerIndex).copy(points = list(playerIndex).points + points)))
  override def changePlayer: Matrix[Any] = if(playerIndex == list.size - 1) copy(currentPlayer = list.head) else copy(currentPlayer = list(playerIndex + 1))
