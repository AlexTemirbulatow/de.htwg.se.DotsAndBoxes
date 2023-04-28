package de.htwg.se.dotsandboxes.model

case class Matrix[T](vecStatus: Vector[Vector[Any]], vecX: Vector[Vector[Any]], vecY: Vector[Vector[Any]]):
  def this(row: Int, col: Int, status: T) = 
    this(Vector.tabulate(col, row) { (_, _) => status}, 
    Vector.tabulate (col + 1, row) { (_, _) => false}, 
    Vector.tabulate (col, row + 1) { (_, _) => false})

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

  
  /*

  TODO:
  --check both squares
  --add player
  --better data structure

  */

  def checkMove(vecIndex: Int, x: Int, y: Int): Matrix[Any] = vecIndex match
    case 1 =>                                             //(Edge case: only down OR up check | Mid case: down AND up check)
      if(x == 0) checkSquare(1, x, y)                     // CASE   1: only down
      else if(x == rowSize(1)) checkSquare(2, x, y)       // CASE   2: only up
      else                                                // CASE 1/2: both
        //checkSquare(1, x, y)
        checkSquare(2, x, y)
    case 2 =>                                             //(Edge case: only right OR left check | Mid case: right AND left check)
      if(y == 0) checkSquare(3, x, y)                     // CASE   3: only right
      else if(y == colSize(2, 0)) checkSquare(4, x, y)    // CASE   4: only left
      else                                                // CASE 3/4: both
        //checkSquare(3, x, y)
        checkSquare(4, x, y)
    case _ => copy()

  def checkSquare(caseNumber: Int, x: Int, y: Int): Matrix[Any] = caseNumber match
    case 1 => // check down
      if(cell(1, x + 1, y).equals(true) && cell(2, x, y).equals(true) && cell(2, x, y + 1).equals(true))
        replaceCell(0, x, y, Status.Blue) else copy()
    case 2 => // check up
      if(cell(1, x - 1, y).equals(true) && cell(2, x - 1, y).equals(true) && cell(2, x - 1, y + 1).equals(true))
        replaceCell(0, x -1 , y, Status.Blue) else copy()
    case 3 => // check right
      if(cell(2, x, y + 1).equals(true) && cell(1, x, y).equals(true) && cell(1, x + 1, y).equals(true))
        replaceCell(0, x, y, Status.Blue) else copy()
    case 4 => // check left
      if(cell(2, x, y - 1).equals(true) && cell(1, x, y - 1).equals(true) && cell(1, x + 1, y - 1).equals(true))
        replaceCell(0, x, y - 1, Status.Blue) else copy()
