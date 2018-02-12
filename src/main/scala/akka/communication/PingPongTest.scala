package akka.communication

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

object PingPongTest extends App {
  val mySystem = ActorSystem("mySystem")
  val pingpong = mySystem.actorOf(Props[PingPongActor],"pingpong")

  pingpong ! PING
  
  Thread.sleep(3000)

}

case object PING
case object PONG

class PingPongActor extends Actor {
  val log = Logging(context.system, this)
  var cnt = 10
  override def receive: Receive = {
    case PING => {
      cnt -= 1
      if (cnt > 0 ) {
        log.info("PING")
        sender ! PONG
      }
    }
    case PONG => {
      cnt -= 1
      if (cnt > 0 ) {
        log.info("PONG")
        sender ! PING  
      }
    }
  }
}