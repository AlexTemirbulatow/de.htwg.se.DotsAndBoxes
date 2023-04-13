package de.htwg.se.dotsandboxes

import de.htwg.se.dotsandboxes.model.Filled
import de.htwg.se.dotsandboxes.model.Matrix
import de.htwg.se.dotsandboxes.model.Field
import de.htwg.se.dotsandboxes.model.Player


@main def run: Unit =
  println("\nWelcome to Dots and Boxes\n")
  val field = new Field(5, 4, Filled.Empty)
  println(field.toString)
  println(field.matrix)
  print("\n")



/*
val even = Vector.tabulate(5)(_ => false)
val odd = Vector.tabulate(6)(_ => false)

val vic = Vector(even, odd, even, odd)

def line() = 
  for (i <- 0 until vic(0).size)
    if (vic(0)(i) == false)
      println("=")
  
def startGame(): Unit =
  val field = new Field(5, 4, Filled.Empty)
  println(field.toString)
  println(field.matrix)
  print("\n")
  */
