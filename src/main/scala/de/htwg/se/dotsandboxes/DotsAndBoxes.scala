package de.htwg.se.dotsandboxes

import de.htwg.se.dotsandboxes.model.Filled
import de.htwg.se.dotsandboxes.model.Matrix
import de.htwg.se.dotsandboxes.model.Field


@main def run: Unit =
  println("\nWelcome to Dots and Boxes\n")
  var field = new Field(5, 4, Filled.Empty)
  println(field.toString)
  print("\n")
