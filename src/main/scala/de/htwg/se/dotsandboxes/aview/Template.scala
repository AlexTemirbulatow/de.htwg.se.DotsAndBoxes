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
    def finalStats: String
    def aborted: String
    def remove: Boolean
