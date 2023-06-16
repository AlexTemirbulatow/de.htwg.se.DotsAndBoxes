package de.htwg.se.dotsandboxes
package util.moveState

import model.fieldComponent.fieldImpl.Move
import model.fieldComponent.FieldInterface


object EdgeState extends MoveState:
    override def handle(move: Move, field: FieldInterface): FieldInterface =
        def downCase(x: Int, y: Int) = field.checkSquare("downcase", x, y)
        def upCase(x: Int, y: Int) = field.checkSquare("upcase", x, y)
        def rightCase(x: Int, y: Int) = field.checkSquare("rightcase", x, y)
        def leftCase(x: Int, y: Int) = field.checkSquare("leftcase", x, y)

        (move.vec, move.x, move.y) match
            case (1, 0, _) => downCase(move.x, move.y)
            case (1, x, _) if x == field.maxPosX => upCase(move.x, move.y)
            case (2, _, 0) => rightCase(move.x, move.y)
            case (2, _, y) if y == field.maxPosY => leftCase(move.x, move.y)
