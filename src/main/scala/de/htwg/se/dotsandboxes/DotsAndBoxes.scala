@main def dotsandboxes: Unit =
  println("\nWelcome to Dots and Boxes!\n")
  print(mesh())

def bar(lenght: Int = 6, cellNum: Int = 6) =
  ("o" + "-" * lenght) * cellNum + "o" + "\n"

def cells(lenght: Int = 6, cellNum: Int = 6, height: Int = 2) =
  (("|" + " " * lenght) * cellNum + "|" + "\n") * height

def mesh(lenght: Int = 6, cellNum: Int = 6, height: Int = 2) =
  (bar(lenght, cellNum) + cells(lenght, cellNum, height)) * cellNum + bar(lenght, cellNum)


