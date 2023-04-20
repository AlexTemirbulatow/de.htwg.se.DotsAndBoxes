package de.htwg.se.dotsandboxes.model

case class Player(name: String, points: Int = 0, filling: Filled)
val playerBlue = Player("Blue", 0, Filled.Blue)
val playerRed = Player("Red", 0, Filled.Red)
object Player:
  val list = List(playerBlue, playerRed)
  def next = list.iterator.next
