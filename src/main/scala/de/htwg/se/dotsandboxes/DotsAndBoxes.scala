package de.htwg.se.dotsandboxes

import aview.TUI
import controller.Controller
import model.Field
import model.Status

@main def setup = TUI(Controller(new Field(5, 4, Status.Empty, 2))).run
