package de.htwg.se.dotsandboxes.model.fieldComponent.fieldImpl

/*factory pattern*/
trait Connectors:
  val stringRepresentation: String
  override def toString: String

private class Empty extends Connectors:
  val stringRepresentation = ""
  override def toString: String = stringRepresentation

private class Dot extends Connectors:
  val stringRepresentation = "O"
  override def toString: String = stringRepresentation

private class EmptyRow extends Connectors:
  val stringRepresentation = "-"
  override def toString: String = stringRepresentation

private class ConnectedRow extends Connectors:
  val stringRepresentation = "="
  override def toString: String = stringRepresentation

private class EmptyColumn extends Connectors:
  val stringRepresentation = "¦"
  override def toString: String = stringRepresentation

private class ConnectedColumn extends Connectors:
  val stringRepresentation = "‖"
  override def toString: String = stringRepresentation

object Connectors:
  def apply(stringRepresentation: String): String = stringRepresentation match
    case "" => new Empty().toString
    case "O" => new Dot().toString
    case "-" => new EmptyRow().toString
    case "=" => new ConnectedRow().toString
    case "¦" => new EmptyColumn().toString
    case "‖" => new ConnectedColumn().toString
