# Scala : Functional Type Queue

## Functional Type Queue
Data structure with first get, enqueue, dequeue.  
Even if the element is enqueue / dequeue, the contents are not changed and a new queue is returned.  

## Sample Code
```Scala
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
```
