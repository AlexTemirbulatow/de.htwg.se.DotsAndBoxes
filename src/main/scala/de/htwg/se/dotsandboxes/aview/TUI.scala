package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import controller.Controller
import util.Observer
import model.Move

class TUI(controller: Controller) extends Observer:
    controller.add(this)
    override def update: Unit = println("\n" + controller.toString)

    def welcome =
        "\n" +
        "---------------------------------" + "\n" +
        "¦ Welcome to Dots and Boxes TUI ¦" + "\n" +
        "---------------------------------" + "\n" +
        "\n"

    def finished =
        "\n" +
        "-----------------" + "\n" +
        "| Game finished |" + "\n" +
        "-----------------" + "\n" +
        "\n"

    def finalStats =
        controller.stats + "\n\n" +
        "_________________________" + "\n\n" +
        controller.winner +
        "\n"

    def run =
        println(welcome)
        println(controller.toString)
        gameLoop

    def gameLoop: Unit =
        if(controller.gameEnd) println(finished + finalStats)
        analyseInput(readLine) match
            case None       => sys.exit()
            case Some(move) => controller.publish(controller.put, move)
        gameLoop

    def analyseInput(input: String): Option[Move] = input match
        case "q" => None
        case _   => val chars = input.toCharArray
            val line = chars(0).toString.toInt
            val x    = chars(1).toString.toInt
            val y    = chars(2).toString.toInt
            Some(Move(line, x, y, true))
