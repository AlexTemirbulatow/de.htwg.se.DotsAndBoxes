package de.htwg.se.dotsandboxes.model

case class Player(name: String, points: Int = 0, filling: Status)
val playerBlue = Player("Blue", 0, Status.Blue)
val playerRed = Player("Red", 0, Status.Red)
object Player:
  val list = List(playerBlue, playerRed)
  def next = list.iterator.next
