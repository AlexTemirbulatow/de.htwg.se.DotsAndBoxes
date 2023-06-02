package de.htwg.se.dotsandboxes
package controller.controllerComponent

import model.Field
import model.PlayerState.{AddOnePoint, AddTwoPoints, NextPlayer}

  object StrategyPlayer:
    def updatePlayer(field: Field, preStatus: Vector[Vector[Any]], postStatus: Vector[Vector[Any]]): Field =
      val difference = preStatus.indices.map(x => preStatus(x).zip(postStatus(x)).count(x => x._1 != x._2)).sum
      if(difference.equals(1)) AddOnePoint.handle(field)
      else if(difference.equals(2)) AddTwoPoints.handle(field)
      else NextPlayer.handle(field)
