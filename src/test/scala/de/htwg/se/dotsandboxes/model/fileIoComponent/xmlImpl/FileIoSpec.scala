package de.htwg.se.dotsandboxes.model
package fileIoComponent.xmlImpl

import fileIoComponent.xmlImpl.FileIO

import fieldComponent.fieldImpl.Field
import matrixComponent.matrixImpl.Status

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._



class FileIoSpec extends AnyWordSpec {
    "A field" when {
        val field: Field = new Field(2, 2, Status.Empty, 2)
        "saved to xml" should {
            val fileIO = new FileIO()
            "be equal when loaded" in {
                fileIO.save(field)
                fileIO.load should be(field)
            }
        }
    }
}