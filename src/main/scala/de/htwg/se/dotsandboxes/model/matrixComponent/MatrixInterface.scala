package de.htwg.se.dotsandboxes.model
package matrixComponent

import fieldComponent.fieldImpl.Move


trait MatrixInterface[T]:
  val maxPosX: Int
  val maxPosY: Int
  def rowSize(vecIndex: Int): Int
  def colSize(vecIndex: Int, row: Int): Int
  def cell(vecIndex: Int, row: Int, col: Int): Any
  def row(vecIndex: Int, row: Int): Vector[Any]
  def replaceCell(vecIndex: Int, row: Int, col: Int, cell: Any): MatrixInterface[Any]
  def vector(vecIndex: Int): Vector[Vector[Any]]
  def checkSquare(thisCase: String, x: Int, y: Int): MatrixInterface[Any]
  def isEdge(move: Move): Boolean
  def currentPlayerInfo: (String, Int)
  def currentPoints: Int
  def updatePlayer(curPlayerIndex: Int = currentPlayerInfo._2): MatrixInterface[Any]
  def playerIndex: Int
  def playerList: Vector[matrixImpl.Player]
  def getMatrix: MatrixInterface[Any]
  def getPoints(index: Int): Int
  def addPoints(curPlayerIndex: Int = currentPlayerInfo._2, points: Int): MatrixInterface[Any]
  def changePlayer: MatrixInterface[Any]
