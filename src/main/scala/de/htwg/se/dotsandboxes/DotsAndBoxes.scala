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
  print("\n\n")

  val matrix = new Matrix(3, 3, Filled.Empty)
  println(matrix.rowSize(1))

