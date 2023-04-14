package de.htwg.se.dotsandboxes

import de.htwg.se.dotsandboxes.model.Filled
import de.htwg.se.dotsandboxes.model.Matrix
import de.htwg.se.dotsandboxes.model.Field
import de.htwg.se.dotsandboxes.model.Player

import scala.io.StdIn.readLine

@main def run: Unit =
  println("\nWelcome to Dots and Boxes\n")
  
  var field = new Field(2, 2, Filled.Red)
  println(field.toString)
  println(field.matrix)
  print("\n")
  field = field.put(0, 0, 0, Filled.Blue)
  field = field.put(0, 1, 1, Filled.Blue)
  println(field.toString)
  println(field.matrix)
  print("\n")
