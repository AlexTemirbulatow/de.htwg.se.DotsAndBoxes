package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Move
import util.Observer

class TUI(controller: Controller) extends Template(controller):
    override def update = println("\n" + controller.toString)

    override def gameLoop: Unit =
        if(controller.gameEnd) println(finalStats)
        analyseInput(readLine) match
            case None => print("\naborted\n")
            case Some(move) => controller.publish(controller.put, move)
        gameLoop

    override def analyseInput(input: String) = input match
        case "q" => None
        case _   => val chars = input.toCharArray
            val line = chars(0).toString.toInt
            val x    = chars(1).toString.toInt
            val y    = chars(2).toString.toInt
            Some(Move(line, x, y, true))

    override def finalStats =
        controller.stats + "\n\n" +
        "_________________________" + "\n\n" +
        controller.winner +
        "\n"
