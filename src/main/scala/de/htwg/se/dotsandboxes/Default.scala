package de.htwg.se.dotsandboxes

import model.fileIoComponent._
import model.fieldComponent.FieldInterface
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerImpl.Controller
import model.fieldComponent.fieldImpl.Field
import model.matrixComponent.matrixImpl.Status


object Default:
    given FieldInterface = new Field(5, 4, Status.Empty, 2)
    given FileIOInterface = xmlImpl.FileIO()
    given ControllerInterface = Controller()
