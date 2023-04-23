package de.htwg.se.dotsandboxes

import aview.TUI
import controller.Controller
import model.Field
import model.Matrix
import model.Status

@main def main: Unit =
  TUI(Controller(new Field(5, 4, Status.Empty))).run
