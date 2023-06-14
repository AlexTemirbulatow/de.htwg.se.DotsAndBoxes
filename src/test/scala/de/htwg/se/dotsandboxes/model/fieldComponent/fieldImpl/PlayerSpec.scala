package de.htwg.se.dotsandboxes.model.fieldComponent.fieldImpl

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpex extends AnyWordSpec {
    "Player" when {
        "accessing list" should {
            "return the correct player and list" in {
                list should be(List(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red),
                    Player("Green", 0, Status.Green), Player("Yellow", 0, Status.Yellow)))
                list shouldBe a[List[Player]]
                list.size should be(4)

                list.head should be(Player("Blue", 0, Status.Blue))
                list.last should be(Player("Yellow", 0, Status.Yellow))

                list(0) should be(Player("Blue", 0, Status.Blue))
                list(1) should be(Player("Red", 0, Status.Red))
                list(2) should be(Player("Green", 0, Status.Green))
                list(3) should be(Player("Yellow", 0, Status.Yellow))
            }
            "return the correct player initials" in {
                val player1 = Player("Player1", 10, Status.Blue)
                val player2 = Player(playerId = "Player2", status = Status.Yellow)
                val player3 = Player(playerId = "Player3", points = 5, status = Status.Green)

                player1.playerId should be("Player1")
                player1.points should be(10)
                player1.status should be(Status.Blue)

                player2.playerId should be("Player2")
                player2.points should be(0)
                player2.status should be(Status.Yellow)

                player3.playerId should be("Player3")
                player3.points should be(5)
                player3.status should be(Status.Green)


                list(0).playerId should be("Blue")
                list(0).points should be(0)
                list(0).status should be(Status.Blue)

                list(1).playerId should be("Red")
                list(1).points should be(0)
                list(1).status should be(Status.Red)

                list(2).playerId should be("Green")
                list(2).points should be(0)
                list(2).status should be(Status.Green)

                list(3).playerId should be("Yellow")
                list(3).points should be(0)
                list(3).status should be(Status.Yellow)
            }
        }
        "creating a player list" should {
            "create a list with the correct players" in {
                val list0 = new PlayerList(0)
                val list1 = new PlayerList(1)
                val list2 = new PlayerList(2)
                val list3 = new PlayerList(3)
                val list4 = new PlayerList(4)
                val listDefault = new PlayerList()

                list0.playerList shouldBe empty
                list1.playerList should be(Vector(Player("Blue", 0, Status.Blue)))
                list2.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
                list3.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green)))
                list4.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red), Player("Green", 0, Status.Green), Player("Yellow", 0, Status.Yellow)))
                list4.playerList should be(list)
                listDefault.playerList should be(Vector(Player("Blue", 0, Status.Blue), Player("Red", 0, Status.Red)))
            }
        }
    }
}
