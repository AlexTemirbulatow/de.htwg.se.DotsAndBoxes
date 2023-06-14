package de.htwg.se.dotsandboxes
package util.moveState

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Move

trait MoveState { def handle(move: Move, field: FieldInterface): FieldInterface }
