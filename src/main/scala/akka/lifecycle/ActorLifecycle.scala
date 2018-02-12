package akka.lifecycle

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.event.Logging

/**
  * Created by gray.ji on 2018. 1. 23..
  */
object ActorLifecycle extends App {
  val ourSystem = ActorSystem("mysystem")
  val testy = ourSystem.actorOf(Props[LifecycleActor], "testy")
  
//  testy ! math.Pi
//  Thread.sleep(1000)
//  testy ! 7
//  Thread.sleep(1000)
//  testy ! "hi there!"
//  Thread.sleep(1000)
//  testy ! List(1, 2, 3)
//  Thread.sleep(1000)
  
  testy ! Nil
  Thread.sleep(1000)
  
//  testy ! "sorry about that"
//  Thread.sleep(1000)
  
  ourSystem.stop(testy)
  Thread.sleep(1000)
  
  ourSystem.terminate
}

class LifecycleActor extends Actor {
  val log = Logging(context.system, this)
  var child: ActorRef = _
  override def receive: Receive = {
    case num: Double => log.info(s"got a double = $num")
    case num: Int => log.info(s"got a integer = $num")
    case list: List[_] => log.info(s"got list = ${list.head}")
    case text: String => child ! text
  }

  override def preStart(): Unit = {
    log.info("preStart")
    child = context.actorOf(Props[StringPrinter], "kiddo")
  }

  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    log.info(s"preRestart because of $reason, during message $message")
    super.preRestart(reason, message)
  }

  override def postRestart(reason: Throwable): Unit = {
    log.info(s"postRestart because of $reason")
    super.postRestart(reason)
  }

  override def postStop(): Unit = log.info("postStop")
}


class StringPrinter extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case msg => log.info(s"child got message '$msg'")
  }
  override def preStart(): Unit = log.info("[child] preStart")

  override def postStop(): Unit = log.info("[child] postStop")
  
}