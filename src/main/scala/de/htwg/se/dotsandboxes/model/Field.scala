package de.htwg.se.dotsandboxes.model

case class Field(matrix: Matrix[Any]):
    def this(rowSize: Int, colSize: Int, status: Any, playerSize: Int = 2) = this(new Matrix(rowSize, colSize, status, playerSize))
    def bar(length: Int = 7, cellNum: Int = 5, rowIndex: Int) = (0 until cellNum).map(rows(rowIndex,_,length)).mkString(
        Connectors.Dot.toString, Connectors.Dot.toString, Connectors.Dot.toString) + "\n"
    def cells(rowSize: Int, length: Int = 7, height: Int = 2) =
        ((0 to colSize()).map(columns(rowSize, _, length)).mkString + "\n") * height
    def mesh(length: Int = 7) =
        ((0 until rowSize()).map(x => bar(length, colSize(), x) + cells(x, length))).mkString + bar(length, colSize(), rowSize())
    def rows(rowIndex: Int, colIndex: Int, length: Int) = getCell(1, rowIndex, colIndex) match
        case false => Connectors.EmptyRow.toString * length
        case true  => Connectors.ConnectedRow.toString * length
    def columns(rowIndex: Int, colIndex: Int, length: Int) = getCell(2, rowIndex, colIndex) match
        case false => Connectors.EmptyColumn.toString + status(rowIndex, colIndex, length)
        case true  => Connectors.ConnectedColumn.toString + status(rowIndex, colIndex, length)
    def status(rowIndex: Int, colIndex: Int, length: Int) = (colIndex < colSize()) match
        case false => Connectors.Empty.toString
        case true  => space(length) + getCell(0, rowIndex, colIndex) + space(length)
    def winner = if(playerList.indices.map(playerList(_).points).count(_ == playerList.maxBy(_._2).points) > 1) "It's a draw!"
        else s"Player ${playerList.maxBy(_._2).playerId} wins!"
    def stats = playerList.indices.map(x => s"Player ${playerList(x).playerId} [points: ${playerList(x).points}]").mkString("\n")
    def getCell(vecIndex: Int, x: Int, y: Int) = matrix.cell(vecIndex, x, y)
    def putCell(vecIndex: Int, x: Int, y: Int, status: Any): Field = copy(matrix.replaceCell(vecIndex, x, y, status))
    def isFinished = (matrix.vector(1) ++ matrix.vector(2)).forall(_.forall(_.equals(true)))
    def isEdge(move: Move) = matrix.isEdge(move)
    def doEdge(vecIndex: Int, x: Int, y: Int): Field = copy(matrix.checkEdge(vecIndex, x, y))
    def doMid(thisCase: String, x: Int, y: Int): Field = copy(matrix.checkSquare(thisCase, x, y))
    def currentPlayer = matrix.currentPlayer.playerId
    def currentStatus = matrix.vector(0)
    def currentPoints = matrix.currentPlayer.points
    def nextPlayer: Field = copy(matrix.changePlayer)
    def updatePlayer: Field = copy(matrix.updatePlayer)
    def addPoint: Field = copy(matrix.addPoint)
    def playerList = matrix.list
    def getMatrix = matrix
    def rowSize(row: Int = 0) = matrix.rowSize(row)
    def colSize(row: Int = 0, col: Int = 0) = matrix.colSize(row, col)
    def space(length: Int) = " " * ((length - 1) / 2)
    override def toString = mesh()
