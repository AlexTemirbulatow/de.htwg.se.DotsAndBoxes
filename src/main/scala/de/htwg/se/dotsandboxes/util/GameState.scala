package de.htwg.se.dotsandboxes
package util

import scala.io.StdIn.readLine
import controller.Controller

enum GameState:
    case Running
    case Finished
    case Aborted
