package de.htwg.se.dotsandboxes.model

case class Player(playerId: String, points: Int = 0, status: Status)
val playerBlue = Player("Blue", 0, Status.Blue)
val playerRed = Player("Red", 0, Status.Red)
val playerGreen = Player("Green", 0, Status.Green)
val playerYellow = Player("Yellow", 0, Status.Yellow)
object Player:
    val list = Vector(playerBlue, playerRed, playerGreen, playerYellow)
    def players(index: Int) = list(index)

case class PlayerList(playerList: Vector[Player]):
    def this(playerSize: Int = 2) = this(Vector.tabulate(playerSize)(x => Player.players(x)))
