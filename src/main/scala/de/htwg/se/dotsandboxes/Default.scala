package de.htwg.se.dotsandboxes

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Field
import model.matrixComponent.matrixImpl.Status
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerImpl.Controller


object Default:
    given FieldInterface = new Field(5, 4, Status.Empty, 4)
    given ControllerInterface = Controller()
