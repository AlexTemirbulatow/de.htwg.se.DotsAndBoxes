package de.htwg.se.dotsandboxes.model

case class Matrix[T](vecFill: Vector[Vector[T]], vecX: Vector[Vector[Boolean]], vecY: Vector[Vector[Boolean]]):
  def this(row: Int, col: Int, filling: T) = 
    this(Vector.tabulate(col, row) { (_, _) => filling}, 
    Vector.tabulate (col + 1, row) { (_, _) => false}, 
    Vector.tabulate (col, row + 1) { (_, _) => false})

  def cell(vecIndex: Int, row: Int, col: Int): T = this.productElement(vecIndex).asInstanceOf[Vector[Vector[T]]](row)(col)
  def row(vecIndex: Int, row: Int) = this.productElement(vecIndex).asInstanceOf[Vector[Vector[T]]](row)
  def replaceCell(vecIndex: Int, row: Int, col: Int, cell: T): Matrix[T] = 
    //copy(vec1.updated(row, vec1(row).updated(col, cell)))
    this.copy(this.productElement(vecIndex).asInstanceOf[Vector[Vector[T]]].updated(
    row, this.productElement(vecIndex).asInstanceOf[Vector[Vector[T]]](row).updated(col, cell)))
  def rowSize(vecIndex: Int): Int = this.productElement(vecIndex).asInstanceOf[Vector[Vector[T]]].size
  def colSize(vecIndex: Int, row: Int) = this.productElement(vecIndex).asInstanceOf[Vector[Vector[T]]](row).size
