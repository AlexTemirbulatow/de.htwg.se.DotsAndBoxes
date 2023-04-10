package de.htwg.se.dotsandboxes.model

enum Filled(StringRepresentation: String):
  override def toString = StringRepresentation
  case Blue extends Filled("B")
  case Red extends Filled("R")
  case Empty extends Filled("E")
