package de.htwg.se.dotsandboxes
package controller.controllerComponent

import util.Observable
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Move
import model.matrixComponent.matrixImpl.Player


trait ControllerInterface extends Observable:
    def put(move: Move): FieldInterface
    def get(vec: Int, x: Int, y: Int): Any
    def undo: FieldInterface
    def redo: FieldInterface
    def save: FieldInterface
    def load: FieldInterface
    def abort: Unit
    def colSize(row: Int = 0, col: Int = 0): Int
    def rowSize(row: Int = 0): Int
    def playerList: Vector[Player]
    def currentPlayer: String
    def currentPoints: Int
    def gameEnded: Boolean
    def winner: String
    def stats: String
    def publish(doThis: => FieldInterface): Unit
    def publish(doThis: Move => FieldInterface, move: Move): Unit
    override def toString: String
