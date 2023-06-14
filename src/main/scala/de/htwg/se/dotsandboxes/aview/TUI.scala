package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import scala.util.{Try, Success, Failure}

import Default.given

import util.Event
import model.fieldComponent.fieldImpl.Move
import controller.controllerComponent.ControllerInterface

class TUI(using controller: ControllerInterface) extends Template(controller):
    override def update(event: Event): Unit = event match
        case Event.Abort => sys.exit
        case Event.End   => print(finalStats)
        case Event.Move  => print(controller.toString)

    override def gameLoop: Unit =
        analyseInput(readLine) match
            case Some(move) => controller.publish(controller.put, move)
            case None =>
        gameLoop

    override def analyseInput(input: String): Option[Move] = input match
        case "q" => controller.abort; None
        case "z" => controller.publish(controller.undo); None
        case "y" => controller.publish(controller.redo); None
        case _   =>
            val chars = input.toCharArray
            chars.size match
                case 3 => checkSyntax(chars(0), chars(1), chars(2)) match
                    case Success(move) => Some(Move(move(0), move(1), move(2), true))
                    case Failure(_)    => print(syntaxErr); None
                case _ => print(syntaxErr); None

    override def checkSyntax(vec: Char, x: Char, y: Char): Try[(Int, Int, Int)] =
        Try(vec.toString.toInt, x.toString.toInt, y.toString.toInt)

    override def finalStats: String =
        "\n" +
        controller.winner + "\n" +
        "_________________________" + "\n\n" +
        controller.stats +
        "\n"

    override def syntaxErr: String =
        "\nIncorrect syntax. Try again: "
