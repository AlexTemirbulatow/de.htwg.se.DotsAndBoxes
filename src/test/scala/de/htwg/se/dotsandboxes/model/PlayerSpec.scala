package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpex extends AnyWordSpec {
    "Player" when {
        "accessing list" should {
            "return the correct player" in {
                Player.list.head should be(Player("Blue", 0, Status.Blue))
                Player.list.last should be(Player("Yellow", 0, Status.Yellow))

                Player.players(0) should be(Player("Blue", 0, Status.Blue))
                Player.players(1) should be(Player("Red", 0, Status.Red))
                Player.players(2) should be(Player("Green", 0, Status.Green))
                Player.players(3) should be(Player("Yellow", 0, Status.Yellow))

                val player1 = Player("Player1", 10, Status.Empty)
                val player2 = Player(playerId = "Player2", status = Status.Empty)

                player1.points should be(10)
                player2.points should be(0)
            }
        }
        "creating a player list" should {
            "create a list with the correct players" in {
                val list1 = new PlayerList(0)
                val list2 = new PlayerList(1)
                val list3 = new PlayerList(2)
                val list4 = new PlayerList(3)
                val list5 = new PlayerList(4)
                val listDefault = new PlayerList()

                list1.playerList shouldBe empty
                list2.playerList should be(Vector(Player("Blue", 0, Status.Blue)))
                list3.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
                list4.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green)))
                list5.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green), Player("Yellow", 0, Status.Yellow)))
                listDefault.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
            }
        }
    }
}
