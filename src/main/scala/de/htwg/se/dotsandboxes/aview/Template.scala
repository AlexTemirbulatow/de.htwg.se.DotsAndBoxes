package de.htwg.se.dotsandboxes
package aview

import scala.util.Try

import util.{Observer, Event}
import model.fieldComponent.fieldImpl.Move
import controller.controllerComponent.ControllerInterface


/* template pattern */
trait Template(controller: ControllerInterface) extends Observer:
    controller.add(this)
    def run: Unit =
        println("""
     _____        _                            _   ____                     
    |  __ \      | |           /\             | | |  _ \                    
    | |  | | ___ | |_ ___     /  \   _ __   __| | | |_) | _____  _____  ___ 
    | |  | |/ _ \| __/ __|   / /\ \ | '_ \ / _` | |  _ < / _ \ \/ / _ \/ __|
    | |__| | (_) | |_\__ \  / ____ \| | | | (_| | | |_) | (_) >  <  __/\__ \
    |_____/ \___/ \__|___/ /_/    \_\_| |_|\__,_| |____/ \___/_/\_\___||___/
        """)
        println("\n" +
            "--Note\n" +
            "A move consists of:\n\n" +
            "<Line> index: (1) for horizontally (2) for vertically\n" +
            "<X> coordinate: starting at (0)\n" +
            "<Y> coordinate: starting at (0)\n\n" +
            "You can type (q) to quit, (z) to undo (y) to redo,\n" +
            "(s) to save the current game state and (l) to load it.")
        update(Event.Move)
        gameLoop
    def gameLoop: Unit
    def analyseInput(input: String): Option[Move]
    def finalStats: String
    def checkSyntax(vec: Char, x: Char, y: Char): Try[(Int, Int, Int)]
    def syntaxErr: String
