package de.htwg.se.dotsandboxes.model

enum Status(StringRepresentation: String):
  override def toString = StringRepresentation
  case Blue extends Status("B")
  case Red extends Status("R")
  case Empty extends Status("E")
