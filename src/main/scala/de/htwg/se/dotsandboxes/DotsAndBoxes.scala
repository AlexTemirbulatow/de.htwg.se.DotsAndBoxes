package de.htwg.se.dotsandboxes

import aview.{TUI, GUI}
import controller.Controller
import model.{Field, Status}
import util.PlayerMode

@main def setup: Unit = 
    val controller = Controller(new Field(5, 4, Status.Empty, 2))
    GUI(controller); TUI(controller).run
