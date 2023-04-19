package de.htwg.se.dotsandboxes.model

import de.htwg.se.dotsandboxes.model.Matrix

case class Field(matrix: Matrix[Any]):
    def this(row: Int, col: Int, filling: Any) = this(new Matrix(row, col, filling))
    def bar(length: Int = 7, cellNum: Int = 5, rowIndex: Int) = 
        (0 until rowSize).map(xline(_, rowIndex, length)).mkString("O", "O", "O") + "\n"
    def cells(row: Int, lenght: Int = 7, height: Int = 2) = 
        (matrix.row(0, row).map(_.toString).map(" " * ((lenght - 1) / 2) + _ + " " * ((lenght - 1) / 2)).mkString("|", "|", "|") + "\n" ) * height
    def mesh(lenght: Int = 7) =
        //(0 until rowSize).map(bar(lenght, colSize, 0) + cells(0, lenght)).mkString("", "", bar(lenght, colSize, rowSize))
        //(0 until rowSize).map(cells(_, lenght)).mkString(
        //bar(lenght, colSize, 0), (1 until rowSize - 1).map(bar(lenght, colSize, _)), bar(lenght, colSize, rowSize))
        val a = (0 until rowSize).map(bar(lenght, colSize, _)) // 3
        //val b = (0 until rowSize).map(cells(_, lenght))     // 2
        //(0 until rowSize).map((bar(lenght, colSize, i) + cells(i, lenght))).mkString("", "", a(rowSize))
        //v.foreach{ case(i,j) => println(i, j) }
        a.map(_ + cells(0, lenght)).mkString + bar(lenght, colSize, rowSize)
        //a(0) + b(0) + a(1) + b(1) + a(2)
        //(0 until rowSize).map(a(_)).mkString("", "", a(rowSize))
    def put(vecIndex: Int, x: Int, y: Int, filling: Any) = 
        copy(matrix.replaceCell(vecIndex, x, y, filling))
    override def toString = mesh()
    def xline(lineIndex: Int, rowIndex: Int, cellNumm: Int) =
        if (matrix.cell(1, rowIndex, lineIndex).equals(false)) "-" * cellNumm else "=" * cellNumm

    val rowSize = matrix.rowSize(0)
    val colSize = matrix.colSize(0, 0)
