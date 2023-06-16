package de.htwg.se.dotsandboxes.model.matrixComponent.matrixImpl


case class PlayerList(playerList: Vector[Player]):
    def this(playerSize: Int = 2) = this(Vector.tabulate(playerSize)(x => list(x)))

case class Player(playerId: String, points: Int = 0, status: Status)

val list = List(
    Player("Blue", 0, Status.Blue),
    Player("Red", 0, Status.Red),
    Player("Green", 0, Status.Green),
    Player("Yellow", 0, Status.Yellow))
