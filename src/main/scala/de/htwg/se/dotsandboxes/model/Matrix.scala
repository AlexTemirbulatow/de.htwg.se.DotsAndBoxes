package de.htwg.se.dotsandboxes.model

case class Matrix[T](vecStatus: Vector[Vector[Any]], vecX: Vector[Vector[Any]], vecY: Vector[Vector[Any]]):
  def this(row: Int, col: Int, status: T) = 
    this(Vector.tabulate(col, row) { (_, _) => status}, 
    Vector.tabulate (col + 1, row) { (_, _) => false}, 
    Vector.tabulate (col, row + 1) { (_, _) => false})

  def cell(vecIndex: Int, row: Int, col: Int): Any = vector(vecIndex)(row)(col)
  def row(vecIndex: Int, row: Int) = vector(vecIndex)(row)
  def replaceCell(vecIndex: Int, row: Int, col: Int, cell: Any): Matrix[Any] =
    vecIndex match
      case 0 => copy(vecStatus = vector(vecIndex).updated(row, vector(vecIndex)(row).updated(col, cell)))
      case 1 => copy(vecX = vector(vecIndex).updated(row, vector(vecIndex)(row).updated(col, cell)))
      case 2 => copy(vecY = vector(vecIndex).updated(row, vector(vecIndex)(row).updated(col, cell)))
      case _ => copy()
  def rowSize(vecIndex: Int): Int = vector(vecIndex).size
  def colSize(vecIndex: Int, row: Int) = vector(vecIndex)(row).size
  def vector(vecIndex: Int) = this.productElement(vecIndex).asInstanceOf[Vector[Vector[Any]]]

  
  /*-----------Game logic [Version 1.0]-----------
  
  TODO:
  --check both squares
  --add player
  --better data structure

  */

  def checkMove(vecIndex: Int, x: Int, y: Int): Matrix[Any] =
    vecIndex match
      case 1 =>
        //(For edge: only down(y = 0) OR up(y = max. size) check | For mid: down AND up check)
        if(x == 0) checkSquare(1, x, y)                 	  // CASE 1: only down
        else if(x == rowSize(1)) checkSquare(2, x, y)       // CASE 2: only up
        else checkSquare(3, x, y)                           // CASE 3: both
      case 2 =>
        //(For edge: only right(x = 0) OR left(x = max. size) check | For mid: right AND left check)
        if(y == 0) checkSquare(4, x, y)                     // CASE 4: only right
        else if(y == colSize(2, 0)) checkSquare(5, x, y)    // CASE 5: only left
        else checkSquare(6, x, y)                           // CASE 6: both
      case _ => copy()
  def checkSquare(caseNumber: Int, x: Int, y: Int): Matrix[Any] =
    caseNumber match
      case 1 => // only down
        if(cell(1, x + 1, y).equals(true) && cell(2, x, y).equals(true) && cell(2, x, y + 1).equals(true))
          replaceCell(0, x, y, Status.Blue) else copy()
      case 2 => // only up
        if(cell(1, x - 1, y).equals(true) && cell(2, x - 1, y).equals(true) && cell(2, x - 1, y + 1).equals(true))
          replaceCell(0, x -1 , y, Status.Blue) else copy()
      case 3 => // both
        //checkSquare(1, x, y)
        checkSquare(2, x, y)
      case 4 => // only right
        if(cell(2, x, y + 1).equals(true) && cell(1, x, y).equals(true) && cell(1, x + 1, y).equals(true))
          replaceCell(0, x, y, Status.Blue) else copy()
      case 5 => // only left
        if(cell(2, x, y - 1).equals(true) && cell(1, x, y - 1).equals(true) && cell(1, x + 1, y - 1).equals(true))
          replaceCell(0, x, y - 1, Status.Blue) else copy()
      case 6 => // both
        //checkSquare(4, x, y)
        checkSquare(5, x, y)
