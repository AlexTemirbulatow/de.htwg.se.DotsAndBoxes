package de.htwg.se.dotsandboxes
package util

import model.Move

enum GameState:
    case Running(move: Move)
    case Finished
    case Aborted