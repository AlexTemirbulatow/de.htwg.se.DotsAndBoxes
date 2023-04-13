package de.htwg.se.dotsandboxes.model

case class Field(matrix: Matrix[Filled]):
    def this(row: Int, col: Int, filling: Filled) = this(new Matrix(row, col, filling))
    def bar(length: Int = 7, cellNum: Int = 5) = 
        ("O" + "-" * length) * cellNum + "O" + "\n"
    def cells(row: Int, lenght: Int = 7, height: Int = 2) = 
        (matrix.row(row).map(_.toString).map(" " * ((lenght - 1) / 2) + _ + " " * ((lenght - 1) / 2)).mkString("|", "|", "|") + "\n" ) * height
    def mesh(lenght: Int = 7) =
        (0 until rowSize).map(cells(_, lenght)).mkString(bar(lenght, colSize), bar(lenght, colSize), bar(lenght, colSize))
    def put(filling: Filled, x: Int, y: Int) = 
        copy(matrix.replaceCell(x, y, filling))
    override def toString = mesh()
    val rowSize = matrix.rowSize
    val colSize = matrix.colSizeEven
    /*
    val even = Vector.tabulate(5)(_ => false)
    val odd = Vector.tabulate(6)(_ => false)
    val vic = Vector(even, odd, even, odd)
    */

