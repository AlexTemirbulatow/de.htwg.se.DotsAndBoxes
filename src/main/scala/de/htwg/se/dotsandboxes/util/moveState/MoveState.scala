package de.htwg.se.dotsandboxes
package util.moveState

import model.fieldComponent.fieldImpl.Move
import model.fieldComponent.FieldInterface

trait MoveState { def handle(move: Move, field: FieldInterface): FieldInterface }
