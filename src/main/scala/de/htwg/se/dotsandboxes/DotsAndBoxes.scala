package de.htwg.se.dotsandboxes

import aview.TUI
import controller.Controller
import model.Field
import model.Matrix
import model.Status

@main def main: Unit =
  val field = new Field(5, 4, Status.Empty)
  val controller = Controller(field)
  val tui = TUI(controller)
  tui.run
