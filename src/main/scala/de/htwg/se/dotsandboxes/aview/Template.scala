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
        update(Event.Move)
        gameLoop
    def gameLoop: Unit
    def analyseInput(input: String): Option[Move]
    def finalStats: String
    def checkSyntax(vec: Char, x: Char, y: Char): Try[(Int, Int, Int)]
    def syntaxErr: String
