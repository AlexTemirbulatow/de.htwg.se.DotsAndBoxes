case class Test(a: Int, b: Int)

val c = Test(10, 10)
val d = c.a
val e = c.b
val f = c.copy(a = 15)


def bar(lenght: Int = 6, cellNum: Int = 6): String =
    ("o" + "-" * lenght) * cellNum + "o" + "\n"
def cells(lenght: Int = 6, cellNum: Int = 6, height: Int = 2): String =
    (("|" + " " * lenght) * cellNum + "|" + "\n") * height
def mesh(lenght: Int = 6, cellNum: Int = 3, height: Int = 2): String =
    "\n" + (bar(lenght, cellNum) + cells(lenght, cellNum, height)) * cellNum + bar(lenght, cellNum)

bar()

cells()

mesh()


1 + 2
3 + 3 * 3
