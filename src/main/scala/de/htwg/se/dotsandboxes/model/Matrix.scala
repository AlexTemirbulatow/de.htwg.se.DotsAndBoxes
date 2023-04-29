package de.htwg.se.dotsandboxes.model

case class Matrix[T](vecStatus: Vector[Vector[Any]], vecX: Vector[Vector[Any]], vecY: Vector[Vector[Any]]):
  def this(row: Int, col: Int, status: Any) =
    this(Vector.tabulate(col, row) { (_, _) => status},
    Vector.tabulate (col + 1, row) { (_, _) =>  false},
    Vector.tabulate (col, row + 1) { (_, _) =>  false})
  def cell(vecIndex: Int, row: Int, col: Int): Any = vector(vecIndex)(row)(col)
  def row(vecIndex: Int, row: Int) = vector(vecIndex)(row)
  def replaceCell(vecIndex: Int, row: Int, col: Int, cell: Any): Matrix[Any] = vecIndex match
    case 0 => copy(vecStatus = vector(vecIndex).updated(row, vector(vecIndex)(row).updated(col, cell)))
    case 1 => copy(vecX = vector(vecIndex).updated(row, vector(vecIndex)(row).updated(col, cell)))
    case 2 => copy(vecY = vector(vecIndex).updated(row, vector(vecIndex)(row).updated(col, cell)))
    case _ => copy()
  def rowSize(vecIndex: Int): Int = vector(vecIndex).size
  def colSize(vecIndex: Int, row: Int) = vector(vecIndex)(row).size
  def vector(vecIndex: Int) = this.productElement(vecIndex).asInstanceOf[Vector[Vector[Any]]]
  val maxPosX = rowSize(1) - 1
  val maxPosY = colSize(2, 0) - 1
  def checkMove(vecIndex: Int, x: Int, y: Int): Matrix[Any] = (vecIndex, x, y) match
    case (1, 0, _)                 => checkSquare(1, x, y)                       // CASE   1: only down
    case (1, x, _) if x == maxPosX => checkSquare(2, x, y)                       // CASE   2: only up
    case (1, _, _)                 => (1 to 2).map(checkSquare(_, x, y)).last    // CASE 1/2: both         unfinished
    case (2, _, 0)                 => checkSquare(3, x, y)                       // CASE   3: only right
    case (2, _, y) if y == maxPosY => checkSquare(4, x, y)                       // CASE   4: only left
    case (2, _, _)                 => (3 to 4).map(checkSquare(_, x, y)).last    // CASE 3/4: both         unfinished
    case (_, _, _)                 => copy()
  def checkSquare(caseNumber: Int, x: Int, y: Int): Matrix[Any] = caseNumber match
    case 1 => if((cell(1, x + 1, y), cell(2, x, y), cell(2, x, y + 1)).toList.forall(_ == true))
      replaceCell(0, x, y, Status.Blue) else copy()
    case 2 => if((cell(1, x - 1, y), cell(2, x - 1, y), cell(2, x - 1, y + 1)).toList.forall(_ == true))
      replaceCell(0, x - 1 , y, Status.Blue) else copy()
    case 3 => if((cell(2, x, y + 1), cell(1, x, y), cell(1, x + 1, y)).toList.forall(_ == true))
      replaceCell(0, x, y, Status.Blue) else copy()
    case 4 => if((cell(2, x, y - 1), cell(1, x, y - 1), cell(1, x + 1, y - 1)).toList.forall(_ == true))
      replaceCell(0, x, y - 1, Status.Blue) else copy()

  /*

  TODO:
  --check both squares properly
  --add player
  --better data structure

  */
