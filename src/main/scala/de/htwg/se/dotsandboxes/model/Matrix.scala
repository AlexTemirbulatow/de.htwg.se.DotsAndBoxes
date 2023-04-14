package de.htwg.se.dotsandboxes.model

case class Matrix[T](vec: Vector[Vector[T]], vec2: Vector[Vector[Boolean]], vec3: Vector[Vector[Boolean]]):
  def this(row: Int, col: Int, filling: T) = 
    this(Vector.tabulate(col, row) { (_, _) => filling}, 
    Vector.tabulate(col + 1, row) { (_, _) => false}, 
    Vector.tabulate(col, row + 1) { (_, _) => false})

  def cell(row: Int, col: Int): T = vec(row)(col)
  def row(row: Int) = vec(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(colSizeVec1, rowSizeVec1) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(vec.updated(row, vec(row).updated(col, cell)))
  def rowSize(index: Int) = this.productElement(index)

  val rowSizeVec1: Int = vec.size
  val colSizeVec1: Int = vec(0).size

  val rowSizeVec2: Int = vec2.size
  val colSizeVec2: Int = vec2(0).size
  //val colSizeOdd: Int = vec(1).size