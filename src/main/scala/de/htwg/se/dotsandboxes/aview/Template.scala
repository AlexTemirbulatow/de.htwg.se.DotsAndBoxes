package de.htwg.se.dotsandboxes
package aview

import util.Observer
import controller.Controller
import model.Move
import scala.util.Try

/* template pattern */
abstract class Template(controller: Controller) extends Observer:
    controller.add(this)
    def run: Unit =
        update
        gameLoop
    def gameLoop: Unit
    def analyseInput(input: String): Option[Move]
    def finalStats: String
    def checkSyntax(vec: Char, x: Char, y: Char): Try[(Int, Int, Int)]
    def syntaxErr: String
