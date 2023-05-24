package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Move
import util.Observer

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
            case None =>
            case Some(move) => controller.publish(controller.put, move)
        gameLoop

    override def analyseInput(input: String): Option[Move] = input match
        case "q" => None
        case "z" => controller.publish(controller.undo); None
        case "y" => controller.publish(controller.redo); None
        case _   => val chars = input.toCharArray
            val line = chars(0).toString.toInt
            val x    = chars(1).toString.toInt
            val y    = chars(2).toString.toInt
            Some(Move(line, x, y, true))
