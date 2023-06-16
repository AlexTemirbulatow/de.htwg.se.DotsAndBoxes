package de.htwg.se.dotsandboxes.model
package fieldComponent.fieldImpl

import fieldComponent.FieldInterface
import matrixComponent.MatrixInterface
import matrixComponent.matrixImpl.{Matrix, Player}


case class Field (matrix: MatrixInterface[Any]) extends FieldInterface:
    def this(rowSize: Int, colSize: Int, status: Any, playerSize: Int = 2) = this(new Matrix(rowSize, colSize, status, playerSize))
    override def bar(length: Int = 7, cellNum: Int = 5, rowIndex: Int): String = (0 until cellNum).map(rows(rowIndex,_,length)).mkString(
        Connectors("O"), Connectors("O"), Connectors("O")) + "\n"
    override def cells(rowSize: Int, length: Int = 7, height: Int = 2): String =
        ((0 to colSize()).map(columns(rowSize, _, length)).mkString + "\n") * height
    override def mesh(length: Int = 7, height: Int = 2): String =
        ((0 until rowSize()).map(x => bar(length, colSize(), x) + cells(x, length, height))).mkString + bar(length, colSize(), rowSize())
    override def rows(rowIndex: Int, colIndex: Int, length: Int): String = getCell(1, rowIndex, colIndex) match
        case false => Connectors("-") * length
        case true  => Connectors("=") * length
    override def columns(rowIndex: Int, colIndex: Int, length: Int): String = getCell(2, rowIndex, colIndex) match
        case false => Connectors("¦") + status(rowIndex, colIndex, length)
        case true  => Connectors("‖") + status(rowIndex, colIndex, length)
    override def status(rowIndex: Int, colIndex: Int, length: Int): String = (colIndex < colSize()) match
        case false => Connectors("")
        case true  => space(length) + getCell(0, rowIndex, colIndex) + space(length)
    override def winner: String = if(playerList.indices.map(playerList(_).points).count(_ == playerList.maxBy(_._2).points) > 1) "It's a draw!"
        else s"Player ${playerList.maxBy(_._2).playerId} wins!"
    override def stats: String = playerList.indices.map(x => s"Player ${playerList(x).playerId} [points: ${playerList(x).points}]").mkString("\n")
    override def getCell(vecIndex: Int, x: Int, y: Int): Any = matrix.cell(vecIndex, x, y)
    override def putCell(vecIndex: Int, x: Int, y: Int, status: Any): Field = copy(matrix.replaceCell(vecIndex, x, y, status))
    override def isFinished: Boolean = (matrix.vector(1) ++ matrix.vector(2)).forall(_.forall(_.equals(true)))
    override def isEdge(move: Move): Boolean = matrix.isEdge(move)
    override def checkSquare(thisCase: String, x: Int, y: Int): Field = copy(matrix.checkSquare(thisCase, x, y))
    override def currentPlayer: String = matrix.currentPlayerId
    override def currentStatus: Vector[Vector[Any]] = matrix.vector(0)
    override def currentPoints: Int = matrix.currentPoints
    override def nextPlayer: Field = copy(matrix.changePlayer)
    override def updatePlayer: Field = copy(matrix.updatePlayer)
    override def addPoints(points: Int): Field = copy(matrix.addPoints(points))
    override def playerList: Vector[Player] = matrix.playerList
    override def getMatrix: MatrixInterface[Any] = matrix.getMatrix
    override def rowSize(row: Int = 0): Int = matrix.rowSize(row)
    override def colSize(row: Int = 0, col: Int = 0): Int = matrix.colSize(row, col)
    override def space(length: Int): String = " " * ((length - 1) / 2)
    override val maxPosX = matrix.maxPosX
    override val maxPosY = matrix.maxPosY
    override def toString = mesh()
