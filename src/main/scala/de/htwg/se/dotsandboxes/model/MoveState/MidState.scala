package de.htwg.se.dotsandboxes.model
package MoveState

object MidState extends MoveState:
    override def handle(move: Move, field: Field): Field =
        def horizontalState(move: Move): Field = field.checkSquare("downcase", move.x, move.y).checkSquare("upcase", move.x, move.y)
        def verticalState(move: Move): Field = field.checkSquare("rightcase", move.x, move.y).checkSquare("leftcase", move.x, move.y)

        move.vec match
            case 1 => horizontalState(move)
            case 2 => verticalState(move)
