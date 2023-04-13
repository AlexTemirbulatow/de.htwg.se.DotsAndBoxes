<<<<<<< HEAD
package de.htwg.se.dotsandboxes.model

case class Player(name: String, points: Int = 0, filling: Filled)

val playerBlue = Player("Player Blue", 0, Filled.Blue)
val playerRed = Player("Player Red", 0, Filled.Red)

object Player:
  val list = List(playerBlue, playerRed)
  def next = list.iterator.next
=======
package de.htwg.se.dotsandboxes.model

case class Player(name: String, points: Int = 0, filling: Filled)

val playerBlue = Player("Player Blue", 0, Filled.Blue)
val playerRed = Player("Player Red", 0, Filled.Red)

//object Player:
//  val list = List(playerBlue, playerRed)
  //def next = list.iterator.next
>>>>>>> d9d1fb74d6cbf968da15f0fef726ca1c023e9c0b
