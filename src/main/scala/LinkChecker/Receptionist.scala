package LinkChecker

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.Props

/**
 * Created by alex on 10/08/14.
 */

object Receptionist {

  private case class Job(client: ActorRef, url: String)

  case class Get(url: String)

  case class Result(url: String, links: Set[String])

  case class Failed(url: String)

}

class Receptionist extends Actor {

  import Receptionist._

  var reqNo = 0

  def receive = waiting

  //This happens if job queue is empty
  val waiting: Receive = {
    case Get(url) =>
      context.become(runNext(Vector(Job(sender, url))))

  }

  def running(queue: Vector[Job]): Receive = {
    //If links are received from controller, send result to client and run next job
    case Controller.Result(links) =>
      val job = queue.head
      job.client ! Result(job.url, links)
      context.stop(sender)
      context.become(runNext(queue.tail))
    //If new url is received, make a job out of it and enqueue it
    case Get(url) =>
      context.become(enqueueJob(queue, Job(sender, url)))
  }

  def runNext(queue: Vector[Job]): Receive = {
    reqNo += 1
    if (queue.isEmpty) waiting
    //create a controller actor and send it the required info, then call running
    else {
      val controller = context.actorOf(Props[Controller], s"c$reqNo")
      controller ! Controller.Check(queue.head.url, 2)
      running(queue)
    }
  }


  def enqueueJob(queue: Vector[Job], job: Job): Receive = {
    //add job to job vector (unless there are too many), then call running
    if (queue.size > 3) {
      sender ! Failed(job.url)
      running(queue)
    } else running(queue :+ job)
  }
}
