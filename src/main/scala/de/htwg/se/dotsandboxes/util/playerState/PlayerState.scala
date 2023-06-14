package de.htwg.se.dotsandboxes
package util.playerState

import model.fieldComponent.FieldInterface

trait PlayerState { def handle(field: FieldInterface): FieldInterface }
