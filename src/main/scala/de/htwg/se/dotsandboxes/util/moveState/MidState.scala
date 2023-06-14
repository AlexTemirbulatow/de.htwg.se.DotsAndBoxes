package de.htwg.se.dotsandboxes
package util.moveState

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Move

object MidState extends MoveState:
    override def handle(move: Move, field: FieldInterface): FieldInterface =
        def horizontalState(move: Move): FieldInterface = field.checkSquare("downcase", move.x, move.y).checkSquare("upcase", move.x, move.y)
        def verticalState(move: Move): FieldInterface = field.checkSquare("rightcase", move.x, move.y).checkSquare("leftcase", move.x, move.y)

        move.vec match
            case 1 => horizontalState(move)
            case 2 => verticalState(move)
