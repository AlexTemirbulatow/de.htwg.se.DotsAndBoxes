package de.htwg.se.dotsandboxes
package util

import playerState._
import model.fieldComponent.FieldInterface

/* stratagy pattern */
object PlayerStratagy:
  def updatePlayer(field: FieldInterface, preStatus: Vector[Vector[Any]], postStatus: Vector[Vector[Any]]): FieldInterface =
    val difference = preStatus.indices.map(x => preStatus(x).zip(postStatus(x)).count(x => x._1 != x._2)).sum
    if(difference.equals(1)) AddOnePoint.handle(field)
    else if(difference.equals(2)) AddTwoPoints.handle(field)
    else NextPlayer.handle(field)
