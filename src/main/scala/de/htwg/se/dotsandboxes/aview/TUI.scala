package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Move
import util.Observer
import scala.util.{Try, Success, Failure}

class TUI(controller: Controller) extends Template(controller):
    override def update = println("\n" + controller.toString)

    override def finalStats =
        controller.stats + "\n\n" +
        "_________________________" + "\n\n" +
        controller.winner +
        "\n"

    override def gameLoop: Unit =
        if(controller.gameEnd) println(finalStats)
        analyseInput(readLine) match
            case Some(move) => controller.publish(controller.put, move)
            case None =>
        gameLoop

    override def analyseInput(input: String): Option[Move] = input match
        case "q" => sys.exit
        case "z" => controller.publish(controller.undo); None
        case "y" => controller.publish(controller.redo); None
        case _   =>
            val chars = input.toCharArray
            chars.size match
                case 3 => checkSyntax(chars(0), chars(1), chars(2)) match
                    case Success(move) => Some(Move(move(0), move(1), move(2), true))
                    case Failure(_)    => println(syntaxErr); None
                case _ => println(syntaxErr); None

    override def checkSyntax(vec: Char, x: Char, y: Char): Try[(Int, Int, Int)] =
        Try(vec.toString.toInt, x.toString.toInt, y.toString.toInt)

    override def syntaxErr: String =
        "Incorrect syntax. Try again:"
