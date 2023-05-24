package de.htwg.se.dotsandboxes
package model
package MoveState

trait MoveState { def handle(move: Move, field: Field): Field }
