package de.htwg.se.dotsandboxes
package aview

import util.Observer
import util.GameState
import controller.Controller
import model.Move

abstract class Template(controller: Controller) extends Observer:
    controller.add(this)
    def run: Unit =
        update
        gameLoop
    def gameLoop: Unit
    def analyseInput(input: String): Option[Move]
    def finalStats: String