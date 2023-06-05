package de.htwg.se.dotsandboxes
package util

import controller.Controller
import model.{Field, Status}
import scala.io.StdIn.readLine

/* strategy pattern */
object PlayerMode:
    def twoPlayers: Field = new Field(5, 4, Status.Empty, 2)
    def threePlayers: Field = new Field(8, 6, Status.Empty, 3)
    def fourPlayers: Field = new Field(11, 9, Status.Empty, 4)

    val selectPlayerMode = getInput
    def getInput: Field =
        print(
            "\n" +
            "---------------------------------" + "\n" +
            "¦ Welcome to Dots and Boxes TUI ¦" + "\n" +
            "---------------------------------" + "\n" +
            "\n")
        val input = readLine("\nChoose playersize (2-4): ")
        input match
            case "2" => twoPlayers
            case "3" => threePlayers
            case "4" => fourPlayers
            case _   => twoPlayers
