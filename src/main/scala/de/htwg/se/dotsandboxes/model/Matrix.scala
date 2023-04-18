package de.htwg.se.dotsandboxes.model

case class Matrix[T](vecFill: Vector[Vector[T]], vecX: Vector[Vector[Any]], vecY: Vector[Vector[Any]]):
  def this(row: Int, col: Int, filling: T) = 
    this(Vector.tabulate(col, row) { (_, _) => filling}, 
    Vector.tabulate (col + 1, row) { (_, _) => false}, 
    Vector.tabulate (col, row + 1) { (_, _) => false})

  def cell(vecIndex: Int, row: Int, col: Int): Any = vector(vecIndex)(row)(col)
  def row(vecIndex: Int, row: Int) = vector(vecIndex)(row)
  def replaceCell(vecIndex: Int, row: Int, col: Int, cell: Any): Matrix[Any] = 
    copy(vecX = vector(vecIndex).updated(row, vector(vecIndex)(row).updated(col, cell))) // Fehler
  def rowSize(vecIndex: Int): Int = vector(vecIndex).size
  def colSize(vecIndex: Int, row: Int) = vector(vecIndex)(row).size
  def vector(vecIndex: Int) = this.productElement(vecIndex).asInstanceOf[Vector[Vector[Any]]]
