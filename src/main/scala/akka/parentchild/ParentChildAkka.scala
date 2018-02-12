package akka.parentchild

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by gray.ji on 2018. 1. 22..
  */
object ParentChildAkka extends App {
  val ourSystem = ActorSystem("outSystem")
  val parent = ourSystem.actorOf(Props[ParentActor], "parent")
  
  parent ! "create"
  parent ! "create"
  Thread.sleep(1000)
  
  parent ! "hi"
  Thread.sleep(1000)

  parent ! "stop"
  Thread.sleep(1000)

  ourSystem.terminate()
}


class ParentActor extends Actor {
  var log = Logging(context.system, this)
  override def receive: Receive = {
    case "create" =>
      context.actorOf(Props[ChildActor])
    case "hi" => 
      log.info("Kids, say hi!")
      for (c <- context.children) c ! "hi"
    case "stop" =>
      log.info("parent stopping")
      context.stop(self)
  }
}

class ChildActor extends Actor {
  var log = Logging(context.system, this)
  override def receive: Receive = {
    case "hi" =>
      val parent = context.parent
      log.info(s"myParent $parent made me say hi!")
  }

  override def postStop(): Unit = log.info("child stopped!")
}