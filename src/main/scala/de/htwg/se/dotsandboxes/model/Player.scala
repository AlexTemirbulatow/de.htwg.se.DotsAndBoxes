package de.htwg.se.dotsandboxes.model

case class Player(playerId: String, points: Int = 0, status: Status)
object Player:
    val list = Vector(
        Player("Blue", 0, Status.Blue),
        Player("Red", 0, Status.Red),
        Player("Green", 0, Status.Green),
        Player("Yellow", 0, Status.Yellow))
case class PlayerList(playerList: Vector[Player]):
    def this(playerSize: Int = 2) = this(Vector.tabulate(playerSize)(x => Player.list(x)))
