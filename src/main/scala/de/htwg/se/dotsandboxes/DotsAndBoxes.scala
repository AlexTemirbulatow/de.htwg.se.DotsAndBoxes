package de.htwg.se.dotsandboxes

import aview.TUI
import controller.Controller
import model.Field
import model.Status
import util.PlayerMode

@main def setup = TUI(Controller(PlayerMode.selectPlayerMode)).run
