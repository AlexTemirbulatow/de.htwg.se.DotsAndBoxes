package de.htwg.se.dotsandboxes.model

enum Connectors(StringRepresentation: String):
  override def toString = StringRepresentation
  case Dot extends Connectors("O")
  case EmptyRow extends Connectors("-")
  case ConnectedRow extends Connectors("=")
  case EmptyColumn extends Connectors("¦")
  case ConnectedColumn extends Connectors("‖")
  case Empty extends Connectors("")
