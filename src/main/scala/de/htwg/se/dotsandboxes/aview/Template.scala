package de.htwg.se.dotsandboxes
package aview

import util.Observer
import controller.Controller
import model.Move

/* template pattern */
abstract class Template(controller: Controller) extends Observer:
    controller.add(this)
    def run: Unit =
        welcome
        update
        gameLoop
    def gameLoop: Unit
    def analyseInput(input: String): Option[Move]
    def welcome: String
    def finalStats: String
