package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Status
import util.Observer

class TUI(controller: Controller) extends Observer:
    controller.add(this)
    def run =
        println(welcome())
        println(controller.field.toString)
        gameLoop()

    def welcome() =
        "\n" +
        "---------------------------------" + "\n" +
        "¦ Welcome to Dots and Boxes TUI ¦" + "\n" +
        "---------------------------------" + "\n" +
        "\n"

    def gameLoop(): Unit =
        println("Blue" + "s turn")
        print("Enter your move (<line><x><y>, eg. 101, q to quit):\n")
        val input = readLine()
        input match
            case "q" =>
            case _   => 
                val chars = input.toCharArray
                val line = chars(0) match
                    case '1' => 1
                    case '2' => 2
                val x = chars(1).toString.toInt
                val y = chars(2).toString.toInt
                controller.put(line, x, y, true)
                println(controller.toString)
                gameLoop()

    override def update: Unit = ???
