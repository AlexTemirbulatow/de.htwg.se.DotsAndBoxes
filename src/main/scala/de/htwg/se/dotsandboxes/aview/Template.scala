package de.htwg.se.dotsandboxes
package aview

import util.{Observer, Event}
import controller.Controller
import model.Move
import scala.util.{Try, Success, Failure}

/* template pattern */
trait Template(controller: Controller) extends Observer:
    controller.add(this)
    def run: Unit =
        println(
            "----------------------------------\n" +
            "| Welcome to Dots And Boxes TUI! |\n" +
            "----------------------------------\n")
        println(
            "--Note\n" +
            "A move consists of a:\n\n" +
            "<Line> index: (1) for horizontally (2) for vertically\n" +
            "<X> coordinate: starting at (0)\n" +
            "<Y> coordinate: starting at (0)")
        update(Event.Move)
        gameLoop
    def gameLoop: Unit
    def analyseInput(input: String): Option[Move]
    def finalStats: String
    def checkSyntax(vec: Char, x: Char, y: Char): Try[(Int, Int, Int)]
    def syntaxErr: String
