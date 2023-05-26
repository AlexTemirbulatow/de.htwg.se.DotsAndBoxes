package de.htwg.se.dotsandboxes
package util

trait Observer { def update: Unit }

trait Observable:
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer) = subscribers = subscribers :+ s
  def remove(s: Observer) = subscribers = subscribers.filterNot(o => o == s)
  def notifyObservers = subscribers.foreach(o => o.update)


  /*move.vec match
      case 1 =>
        (move.x, move.y) match
        case (0, _) => 
          field.putCell(move.vec, move.x, move.y, false).putCell(0, move.x, move.y, Status.Empty)
        case (x, _) if x == field.maxPosX =>
          field.putCell(move.vec, move.x, move.y, false).putCell(0, move.x - 1, move.y, Status.Empty)
        case _ => 
          field.putCell(move.vec, move.x, move.y, false).putCell(0, move.x, move.y, Status.Empty).putCell(0, move.x - 1, move.y, Status.Empty)
      case 2 =>
        (move.x, move.y) match
        case (_, 0) => 
          field.putCell(move.vec, move.x, move.y, false).putCell(0, move.x, move.y, Status.Empty)
        case (_, y) if y == field.maxPosY =>
          field.putCell(move.vec, move.x, move.y, false).putCell(0, move.x, move.y - 1, Status.Empty)
        case _ => 
          field.putCell(move.vec, move.x, move.y, false).putCell(0, move.x, move.y, Status.Empty).putCell(0, move.x, move.y - 1, Status.Empty)*/

    /*move.vec match
      case 1 =>
        (move.x, move.y) match
        case (0, _) => 
          field.putCell(move.vec, move.x, move.y, true).checkSquare("downcase", move.x, move.y)
        case (x, _) if x == field.maxPosX =>
          field.putCell(move.vec, move.x, move.y, true).checkSquare("upcase", move.x - 1, move.y)
        case _ => 
          field.putCell(move.vec, move.x, move.y, true).checkSquare("downcase", move.x, move.y).checkSquare("upcase", move.x - 1, move.y)
      case 2 =>
        (move.x, move.y) match
        case (_, 0) => 
          field.putCell(move.vec, move.x, move.y, true).checkSquare("rightcase", move.x, move.y)
        case (_, y) if y == field.maxPosY =>
          field.putCell(move.vec, move.x, move.y, true).checkSquare("leftcase", move.x, move.y - 1)
        case _ => 
          field.putCell(move.vec, move.x, move.y, true).checkSquare("rightcase", move.x, move.y).checkSquare("leftcase", move.x, move.y - 1)*/