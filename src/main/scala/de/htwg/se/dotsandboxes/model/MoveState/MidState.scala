package de.htwg.se.dotsandboxes.model
package MoveState

object MidState extends MoveState:
    override def handle(move: Move, field: Field): Field =
        var state = field
        state = move.vec match
            case 1 => horizontalState(move)
            case 2 => verticalState(move)
        def horizontalState(move: Move) = field.checkSquare("downcase", move.x, move.y).checkSquare("upcase", move.x, move.y)
        def verticalState(move: Move) = field.checkSquare("rightcase", move.x, move.y).checkSquare("leftcase", move.x, move.y)
        state
