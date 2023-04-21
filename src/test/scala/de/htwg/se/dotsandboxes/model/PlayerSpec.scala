package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
    "A Player" should {
        val player = Player("Blue", 0, Status.Blue)
        "have a name" in {
            player.name should be("Blue")
        }
        "have initialy zero points" in {
            player.points should be(0)
        }
        "have a filling" in {
            player.filling should be(Status.Blue)
        }
    }
    "A Players List" should {
        "contain players" in {
            Player.list should not be empty
            Player.list.size should be(2)
            Player.list should contain atLeastOneOf(Player("Red", 0, Status.Red), Player("Green", 0, Status.Empty))
        }
        "iterate through the list" in {
            val player1 = Player.next
            player1 should be(Player("Blue", 0, Status.Blue))
        }
        "only for test case append new players" in {
            val newList = Player.list.appended(Player("Green", 0, Status.Empty))
            newList should contain atLeastOneOf(Player("Green", 0, Status.Empty), Player("Orange", 0, Status.Empty))
            val list2 = List(Player("yellow", 0, Status.Empty))
            val newList2 = newList :+ list2
            newList2.size should be(4)
        }
    }
}
