package de.htwg.se.dotsandboxes.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PlayerSpec extends AnyWordSpec {
    "A Player" should {
        val player = Player("Blue", 0, Filled.Blue)
        "have a name" in {
            player.name should be("Blue")
        }
        "have initialy zero points" in {
            player.points should be(0)
        }
        "have a filling" in {
            player.filling should be(Filled.Blue)
        }
    }
    "A Players List" should {
        "contain players" in {
            Player.list should not be empty
            Player.list.size should be(2)
            Player.list should contain atLeastOneOf(Player("Red", 0, Filled.Red), Player("Green", 0, Filled.Empty))
        }
        "iterate through the list" in {
            val player1 = Player.next
            player1 should be(Player("Blue", 0, Filled.Blue))
        }
        "only for test case append new players" in {
            val newList = Player.list.appended(Player("Green", 0, Filled.Empty))
            newList should contain atLeastOneOf(Player("Green", 0, Filled.Empty), Player("Orange", 0, Filled.Empty))
            val list2 = List(Player("yellow", 0, Filled.Empty))
            val newList2 = newList :+ list2
            newList2.size should be(4)
        }
    }
}
