package de.htwg.se.dotsandboxes

import aview.TUI
import controller.Controller
import model.Field
import model.Status

@main def main: Unit =
  TUI(Controller(new Field(3, 2, Status.Empty, 2))).run
