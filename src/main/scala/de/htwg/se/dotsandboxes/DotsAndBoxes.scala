package de.htwg.se.dotsandboxes

import aview.{GUI, TUI}
import model.fieldComponent.fieldImpl.{Field, Status}
import controller.controllerComponent.controllerImpl.Controller

@main def setup: Unit =
    val controller = Controller(new Field(5, 4, Status.Empty, 2))
    GUI(controller); TUI(controller).run
