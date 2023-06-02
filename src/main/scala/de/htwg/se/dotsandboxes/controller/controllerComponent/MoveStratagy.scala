package de.htwg.se.dotsandboxes
package controller.controllerComponent

import model.{Field, Move}
import model.MoveState.{EdgeState, MidState}

/* strategy pattern */
  object StrategyMove:
    def decideMove(move: Move, field: Field): Field =
      if(field.isEdge(move)) EdgeState.handle(move, field) else MidState.handle(move, field)
