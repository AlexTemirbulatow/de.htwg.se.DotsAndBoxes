package de.htwg.se.dotsandboxes.model

case class Field(matrix: Matrix[Filled]):
    def this(row: Int, col: Int, filling: Filled) = this(new Matrix(row, col, filling))
    def bar(length: Int = 7, cellNum: Int = 5) = 
        ("O" + "-" * length) * cellNum + "O" + "\n"
    def cells(row: Int, lenght: Int = 7, height: Int = 2) = 
        (matrix.row(0, row).map(_.toString).map(" " * ((lenght - 1) / 2) + _ + " " * ((lenght - 1) / 2)).mkString("|", "|", "|") + "\n" ) * height
    def mesh(lenght: Int = 7) =
        (0 until rowSize).map(cells(_, lenght)).mkString(bar(lenght, colSize), bar(lenght, colSize), bar(lenght, colSize))
    def put(vecIndex: Int, x: Int, y: Int, filling: Filled) = 
        copy(matrix.replaceCell(vecIndex, x, y, filling))
    override def toString = mesh()
    val rowSize = matrix.rowSize(0)
    val colSize = matrix.colSize(0, 0)
