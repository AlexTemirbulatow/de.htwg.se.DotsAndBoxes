package de.htwg.se.dotsandboxes.model

case class Matrix[T](vec: Vector[Vector[T]]):
  def this(row: Int, col: Int, filling: T) = this(Vector.tabulate(col, row) { (row, col) => filling})
  def cell(row: Int, col: Int): T = vec(row)(col)
  def row(row: Int) = vec(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(colSize, rowSize) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(vec.updated(row, vec(row).updated(col, cell)))
  val rowSize: Int = vec.size
  val colSize: Int = vec(0).size