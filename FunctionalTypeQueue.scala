case class Queue[T](front: List[T] = Nil, rear: List[T] = Nil) {

    // Returns the first element
    def getHead = {
        if (front.isEmpty && rear.isEmpty)
            "Queue is Empty"
        else
            mirrorQueue.front.head
    }

    // Add a new element to the end and return the queue
    def enqueue(x: T) = {
        new Queue(front, x :: rear)
    }

    // Returns a queue with the first element removed
    def dequeue = {
        new Queue(mirrorQueue.front.tail, mirrorQueue.rear)
    }

    // Invert the queue
    private def mirrorQueue = {
        if (front.isEmpty)
            new Queue(rear.reverse, Nil)
        else
            this
    }
}

object FunctionalTypeQueue extends App {

    val queueList = List(1, 2, 3)

    val queueOrigin = queueList.foldLeft(new Queue[Int])(_.enqueue(_))
    println(queueOrigin.getHead) // 1

    // Enqueue 101
    val queueA = queueOrigin.enqueue(101)
    println(queueA) // Queue(List(),List(101, 3, 2, 1))

    // Enqueue 102
    val queueB = queueA.enqueue(102)
    println(queueB) // Queue(List(),List(102, 101, 3, 2, 1))

    // Enqueue 101
    val queueC = queueB.enqueue(103)
    println(queueC) // Queue(List(),List(103, 102, 101, 3, 2, 1))

    // 3 times dequeue
    val queueD = queueC.dequeue.dequeue.dequeue
    println(queueD.getHead) // 101
    println(queueD) // Queue(List(101, 102, 103),List())

    // 3 times dequeue
    val queueE = queueD.dequeue.dequeue.dequeue
    println(queueE.getHead) // Queue is Empty
    println(queueE) // Queue(List(),List())

}


// =====================================
//         Output Sample
// =====================================
// $ scalac FunctionalTypeQueue.scala 
// $ scala FunctionalTypeQueue
// 1
// Queue(List(),List(101, 3, 2, 1))
// Queue(List(),List(102, 101, 3, 2, 1))
// Queue(List(),List(103, 102, 101, 3, 2, 1))
// 101
// Queue(List(101, 102, 103),List())
// Queue is Empty
// Queue(List(),List())