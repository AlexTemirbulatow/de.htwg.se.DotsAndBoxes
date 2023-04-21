package de.htwg.se.dotsandboxes

import scala.io.StdIn.readLine

import de.htwg.se.dotsandboxes.model.Status
import de.htwg.se.dotsandboxes.model.Matrix
import de.htwg.se.dotsandboxes.model.Field
import de.htwg.se.dotsandboxes.model.Player
import de.htwg.se.dotsandboxes.model.Connectors

def welcome() =
  "\n" +
  "------------------------------" + "\n" +
  "¦ Welcome to Dots and Boxes! ¦" + "\n" +
  "------------------------------" + "\n\n" +
  "Both, horizontal and vertical lines are two seperate arrays. To access:\n" +
  "horizontal lines: type 1\n" +
  "vertical lines: type 2\n\n"

@main def run: Unit =
  print(welcome())
  var field = new Field(5, 4, Status.Empty)
  println(field.toString)
  gameLoop(field, Player.next)

def gameLoop(field: Field, player: Player): Unit =
  println(player.name + "s turn")
  print("Enter your move (<line><x><y>, eg. 101, q to quit):\n")
  val input = readLine()
  parseInput(input) match
    case None => 
    case Some(newfield) =>
      println("\n")
      println(newfield)
      gameLoop(newfield, Player.next)

  def parseInput(input: String): Option[Field] =
    input match
      case "q" => None
      case _ => 
        val chars = input.toCharArray
        val line = chars(0) match
          case '1' => 1
          case '2' => 2
        val x = chars(1).toString.toInt
        val y = chars(2).toString.toInt
        Some(field.put(line, x, y, true))
