package de.htwg.se.dotsandboxes.util


/*observer pattern*/
trait Observer { def update(event: Event): Unit }

trait Observable:
  var subscribers: Vector[Observer] = Vector()
  def add(newSub: Observer) = subscribers = subscribers :+ newSub
  def remove(oldSub: Observer) = subscribers = subscribers.filterNot(o => o == oldSub)
  def notifyObservers(event: Event) = subscribers.foreach(o => o.update(event))

enum Event:
  case Abort
  case Move
  case End
