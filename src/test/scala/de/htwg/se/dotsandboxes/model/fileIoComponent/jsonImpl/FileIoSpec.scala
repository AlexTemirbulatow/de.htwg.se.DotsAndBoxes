package de.htwg.se.dotsandboxes
package model
package fileIoComponent.jsonImpl

import fileIoComponent.jsonImpl.FileIO

import matrixComponent.matrixImpl.Status
import fieldComponent.fieldImpl.{Field, Move}
import controller.controllerComponent.controllerImpl.Controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class FileIoSpec extends AnyWordSpec {
    "A game state" when {
        "saved to json" should {
            "be equal when loaded" in {
                val field: Field = new Field(5, 4, Status.Empty, 2)
                val fileIO = new FileIO()
                fileIO.save(field)
                fileIO.load should be(field)
            }
            "return the correct game state" in {
                val controller = Controller(using new Field(5, 4, Status.Empty, 2), new FileIO())
                controller.publish(controller.put, Move(1, 0, 0, true))
                controller.save should be(controller.field)
                controller.load should be(controller.field)
            }
        }
    }
}
