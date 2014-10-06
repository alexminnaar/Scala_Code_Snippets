package BFS

import scala.collection.immutable.Queue

/**
 * Implementation of Breadth First Search for determining if there is a path between two nodes in a graph.
 */

case class Node(status: String, children: Vector[Node])

case class Graph(nodes: Vector[Node])

object BFS extends App {

  def isConnected(g: Graph, n1: Node, n2: Node): Boolean = {

    var unVisited = Queue[Node]()

    //start at n1
    unVisited.enqueue(n1)

    while (!unVisited.isEmpty) {

      //get next node in the queue
      val nodeToVisit = unVisited.dequeue
      unVisited = nodeToVisit._2

      //check all children of this node and if they have not been visited yet, add to queue
      for (child <- nodeToVisit._1.children) {
        //if we find it then stop
        if (child == n2) {
          return true
        }
        else if (child.status == "unvisited") {
          unVisited.enqueue(child)
        }
      }
    }

    false
  }


}
