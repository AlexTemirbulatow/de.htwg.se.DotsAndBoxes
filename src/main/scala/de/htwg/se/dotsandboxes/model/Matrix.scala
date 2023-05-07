package de.htwg.se.dotsandboxes.model

case class Matrix[T](vecStatus: Vector[Vector[Any]], vecRow: Vector[Vector[Any]], vecCol: Vector[Vector[Any]], list: Vector[Player], currentPlayer: Player):
  def this(rowSize: Int, colSize: Int, status: Any, playerSize: Int = 2) =
    this(Vector.tabulate(colSize, rowSize) {(_, _) => status},
    Vector.tabulate (colSize + 1, rowSize) {(_, _) =>  false},
    Vector.tabulate (colSize, rowSize + 1) {(_, _) =>  false},
    new PlayerList(playerSize).playerList, Player.list.head)
  val maxPosX = rowSize(1) - 1
  val maxPosY = colSize(2, 0) - 1
  def cell(vecIndex: Int, row: Int, col: Int): Any = vector(vecIndex)(row)(col)
  def row(vecIndex: Int, row: Int) = vector(vecIndex)(row)
  def replaceCell(vecIndex: Int, row: Int, col: Int, cell: Any): Matrix[Any] = vecIndex match
    case 0 => copy(vecStatus = vector(0).updated(row, vector(0)(row).updated(col, cell)))
    case 1 => copy(vecRow = vector(1).updated(row, vector(1)(row).updated(col, cell)))
    case 2 => copy(vecCol = vector(2).updated(row, vector(2)(row).updated(col, cell)))
  def rowSize(vecIndex: Int): Int = vector(vecIndex).size
  def colSize(vecIndex: Int, row: Int) = vector(vecIndex)(row).size
  def vector(vecIndex: Int) = this.productElement(vecIndex).asInstanceOf[Vector[Vector[Any]]]
  def checkEdge(vecIndex: Int, x: Int, y: Int): Matrix[Any] = (vecIndex, x, y) match
    case (1, 0, _) => checkSquare("downcase", x, y)
    case (1, x, _) if x == maxPosX => checkSquare("upcase", x, y)
    case (2, _, 0) => checkSquare("rightcase", x, y)
    case (2, _, y) if y == maxPosY => checkSquare("leftcase", x, y)
  def checkSquare(thisCase: String, x: Int, y: Int): Matrix[Any] = thisCase match
    case "downcase" => if((cell(1, x + 1, y), cell(2, x, y), cell(2, x, y + 1)).toList.forall(_ == true))
      replaceCell(0, x, y, currentPlayer.status) else copy()
    case "upcase" => if((cell(1, x - 1, y), cell(2, x - 1, y), cell(2, x - 1, y + 1)).toList.forall(_ == true))
      replaceCell(0, x - 1 , y, currentPlayer.status) else copy()
    case "rightcase" => if((cell(2, x, y + 1), cell(1, x, y), cell(1, x + 1, y)).toList.forall(_ == true))
      replaceCell(0, x, y, currentPlayer.status) else copy()
    case "leftcase" => if((cell(2, x, y - 1), cell(1, x, y - 1), cell(1, x + 1, y - 1)).toList.forall(_ == true))
      replaceCell(0, x, y - 1, currentPlayer.status) else copy()
  def isEdge(move: Move) = move.vec match
    case 1 => if(move.x == 0 || move.x == maxPosX) true else false
    case 2 => if(move.y == 0 || move.y == maxPosY) true else false
  def playerIndex = list.indices.map(x => list(x).playerId).indexOf(currentPlayer.playerId)
  def updatePlayer: Matrix[Any] = copy(currentPlayer = list(playerIndex))
  def addPoint: Matrix[Any] = copy(list = list.updated(playerIndex, list(playerIndex).copy(points = list(playerIndex).points + 1)))
  def changePlayer: Matrix[Any] = if(playerIndex == list.size - 1) copy(currentPlayer = list.head) else copy(currentPlayer = list(playerIndex + 1))
