package de.htwg.se.dotsandboxes.model.fieldComponent

import fieldImpl.{Move, Player, Matrix}

trait FieldInterface:
    def bar(length: Int = 7, cellNum: Int = 5, rowIndex: Int): String
    def cells(rowSize: Int, length: Int = 7, height: Int = 2): String
    def mesh(length: Int = 7, height: Int = 2): String
    def rows(rowIndex: Int, colIndex: Int, length: Int): String
    def columns(rowIndex: Int, colIndex: Int, length: Int): String
    def status(rowIndex: Int, colIndex: Int, length: Int): String
    def winner: String
    def stats: String
    def getCell(vecIndex: Int, x: Int, y: Int): Any
    def putCell(vecIndex: Int, x: Int, y: Int, status: Any): FieldInterface
    def isFinished: Boolean
    def isEdge(move: Move): Boolean
    def checkSquare(thisCase: String, x: Int, y: Int): FieldInterface
    def currentPlayer: String
    def currentStatus: Vector[Vector[Any]]
    def currentPoints: Int
    def nextPlayer: FieldInterface
    def updatePlayer: FieldInterface
    def addPoints(points: Int): FieldInterface
    def playerList: Vector[Player]
    def getMatrix: Matrix[Any]
    def rowSize(row: Int = 0): Int
    def colSize(row: Int = 0, col: Int = 0): Int
    def space(length: Int): String
    val maxPosX: Int
    val maxPosY: Int
    override def toString: String
