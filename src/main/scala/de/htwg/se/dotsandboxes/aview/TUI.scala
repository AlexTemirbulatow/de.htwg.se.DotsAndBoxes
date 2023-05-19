package de.htwg.se.dotsandboxes
package aview

import scala.io.StdIn.readLine
import controller.Controller
import model.Move
import util.Observer
import util.GameState

class TUI(controller: Controller) extends Template(controller):
    override def update = println("\n" + controller.toString)

    override def gameLoop: Unit =
        controller.stateHandler(checkState.handle(controller.gameEnd))
        gameLoop

    override def finalStats =
        controller.stats + "\n\n" +
        "_________________________" + "\n\n" +
        controller.winner +
        "\n"

    override def aborted =
        "\nAborted\n"

    override def remove = 
        controller.remove(this)
        false



    /*Game state pattern*/
    object checkState:
        var state = GameState.Running
        def handle(check: Boolean): GameState =
            state = check match
                case true  => finished
                case false => analyseInput(readLine)
            state
        def finished: GameState =
            remove 
            println(finalStats)
            GameState.Finished
        def analyseInput(input: String): GameState = input match
            case "q" =>
                println(aborted)
                remove
                GameState.Aborted
            case _   => val chars = input.toCharArray
                val line = chars(0).toString.toInt
                val x    = chars(1).toString.toInt
                val y    = chars(2).toString.toInt
                controller.publish(controller.put, Move(line, x, y, true))
                GameState.Running
