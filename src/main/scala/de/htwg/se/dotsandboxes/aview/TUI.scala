package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Status
import model.Move
import util.Observer

class TUI(controller: Controller) extends Observer:
    def welcome() =
    "\n" +
    "---------------------------------" + "\n" +
    "¦ Welcome to Dots and Boxes TUI ¦" + "\n" +
    "---------------------------------" + "\n" +
    "\n"

    controller.add(this)
    override def update: Unit = println("\n" + controller.field.toString)

    def run =
        println(welcome())
        println(controller.field.toString)
        gameLoop()

    def gameLoop(): Unit = 
        analyseInput(readLine) match
            case None       => sys.exit()
            case Some(move) => controller.publish(controller.put, move)
        gameLoop()

    def analyseInput(input: String): Option[Move] = input match
        case "q" => None
        case _   =>
            val chars = input.toCharArray
            val line = chars(0).toString.toInt
            val x = chars(1).toString.toInt
            val y = chars(2).toString.toInt
            Some(Move(line, x, y, true))
