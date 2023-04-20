package de.htwg.se.dotsandboxes.model

enum Connectors(StringRepresentation: String):
  override def toString = StringRepresentation
  case Dot extends Connectors("O")
  case EmptyX extends Connectors("-")
  case ConnectedX extends Connectors("=")
  case EmptyY extends Connectors("¦")
  case ConnectedY extends Connectors("‖")
  case Empty extends Connectors("")
