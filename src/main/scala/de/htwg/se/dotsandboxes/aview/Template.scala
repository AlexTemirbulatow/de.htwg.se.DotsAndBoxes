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
            $$$$$$$\             $$\                      $$$$$$\                  $$\       $$$$$$$\                                     
            $$  __$$\            $$ |                    $$  __$$\                 $$ |      $$  __$$\
            $$ |  $$ | $$$$$$\ $$$$$$\    $$$$$$$\       $$ /  $$ |$$$$$$$\   $$$$$$$ |      $$ |  $$ | $$$$$$\  $$\   $$\  $$$$$$\   $$$$$$$\
            $$ |  $$ |$$  __$$\\_$$  _|  $$  _____|      $$$$$$$$ |$$  __$$\ $$  __$$ |      $$$$$$$\ |$$  __$$\ \$$\ $$  |$$  __$$\ $$  _____|
            $$ |  $$ |$$ /  $$ | $$ |    \$$$$$$\        $$  __$$ |$$ |  $$ |$$ /  $$ |      $$  __$$\ $$ /  $$ | \$$$$  / $$$$$$$$ |\$$$$$$\
            $$ |  $$ |$$ |  $$ | $$ |$$\  \____$$\       $$ |  $$ |$$ |  $$ |$$ |  $$ |      $$ |  $$ |$$ |  $$ | $$  $$<  $$   ____| \____$$\
            $$$$$$$  |\$$$$$$  | \$$$$  |$$$$$$$  |      $$ |  $$ |$$ |  $$ |\$$$$$$$ |      $$$$$$$  |\$$$$$$  |$$  /\$$\ \$$$$$$$\ $$$$$$$  |
            \_______/  \______/   \____/ \_______/       \__|  \__|\__|  \__| \_______|      \_______/  \______/ \__/  \__| \_______|\_______/
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
