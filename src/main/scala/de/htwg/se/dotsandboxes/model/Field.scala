package de.htwg.se.dotsandboxes.model

case class Field(matrix: Matrix[Any]):
    def this(row: Int, col: Int, status: Any) = this(new Matrix(row, col, status))
    def bar(length: Int = 7, cellNum: Int = 5, rowIndex: Int) =
        (0 until cellNum).map(xline(rowIndex,_,length)).mkString(
        Connectors.Dot.toString,Connectors.Dot.toString,Connectors.Dot.toString) + "\n"
    def cells(row: Int, length: Int = 7, height: Int = 2) =
        ((0 to colSize()).map(yline(row, _, length)).mkString + "\n") * height
    def mesh(length: Int = 7) =
        ((0 until rowSize()).map(x => bar(length, colSize(), x) + cells(x, length))).mkString + bar(length, colSize(), rowSize())
    def xline(rowIndex: Int, lineIndex: Int, length: Int) =
        if(matrix.cell(1, rowIndex, lineIndex).equals(false)) Connectors.EmptyX.toString * length
        else Connectors.ConnectedX.toString * length
    def yline(rowIndex: Int, lineIndex: Int, length: Int) =
        if(matrix.cell(2, rowIndex, lineIndex).equals(false)) Connectors.EmptyY.toString + status(rowIndex, lineIndex, length)
        else Connectors.ConnectedY.toString + status(rowIndex, lineIndex, length)
    def status(rowIndex: Int, lineIndex: Int, length: Int) =
        if(lineIndex < colSize()) space(length) + matrix.cell(0, rowIndex, lineIndex) + space(length) else Connectors.Empty.toString
    def put(vecIndex: Int, x: Int, y: Int, status: Any): Field = copy(matrix.replaceCell(vecIndex, x, y, status))
    def check(vecIndex: Int, x: Int, y: Int): Field = copy(matrix.checkMove(vecIndex, x, y))
    def get(vecIndex: Int, x: Int, y: Int) = matrix.cell(vecIndex, x, y)
    def rowSize(row: Int = 0) = matrix.rowSize(row)
    def colSize(row: Int = 0, col: Int = 0) = matrix.colSize(row, col)
    def space(length: Int) = " " * ((length - 1) / 2)
    override def toString = mesh()
