package akka.parentchild

import akka.actor.{Actor, ActorIdentity, ActorSystem, Identify, Props}
import akka.event.Logging

object ActorIdentify extends App {
  val mySystem = ActorSystem("mySystem")
  val checker = mySystem.actorOf(Props[CheckActor],"checker")
  
  checker ! "../*"
  Thread.sleep(1000)
  
  checker ! "../../*"
  Thread.sleep(1000)
  
  checker ! "/system/*"
  Thread.sleep(1000)
  
  checker ! "/user/checker2"
  Thread.sleep(1000)
  
  checker ! "akka://OurExampleSystem/system"
  Thread.sleep(1000)
  
  mySystem.stop(checker)
  Thread.sleep(1000)

}


class CheckActor extends Actor {
  val log = Logging(context.system, this)
  override def receive: Receive = {
    case path: String => 
      log.info(s"checking path => $path")
      context.actorSelection(path) ! Identify(path)
    case ActorIdentity(path, Some(ref)) => log.info(s"found actor $ref on $path")
    case ActorIdentity(path, None) => log.info(s"could not found an actor on $path")
  }
}