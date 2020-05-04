package queue


class MyCircularQueue(private val size: Int) {

    /** Initialize your data structure here. Set the size of the queue to be k. */
    private val data = IntArray(size)

    private var head = -1
    private var tail = -1

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    fun enQueue(value: Int): Boolean {
        if (isFull()) {
            return false
        }
        if (isEmpty()) {
            head = 0
        }
        tail = (tail + 1) % size
        data[tail] = value
        return true
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    fun deQueue(): Boolean {
        if (isEmpty()) {
            return false
        }
        if (head == tail) {
            head = -1
            tail = -1
            return true
        }
        head = (head + 1) % size
        return true
    }

    /** Get the front item from the queue. */
    fun Front(): Int {
        if (isEmpty()) {
            return -1
        }
        return data[head]
    }

    /** Get the last item from the queue. */
    fun Rear(): Int {
        if (isEmpty()) {
            return -1
        }
        return data[tail]
    }

    /** Checks whether the circular queue is empty or not. */
    fun isEmpty(): Boolean {
        return head == -1
    }

    /** Checks whether the circular queue is full or not. */
    fun isFull(): Boolean {
        return ((tail + 1) % size) == head
    }

}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * var obj = MyCircularQueue(k)
 * var param_1 = obj.enQueue(value)
 * var param_2 = obj.deQueue()
 * var param_3 = obj.Front()
 * var param_4 = obj.Rear()
 * var param_5 = obj.isEmpty()
 * var param_6 = obj.isFull()
 */
fun main() {

    val k = 4
    val value = 10

    val obj = MyCircularQueue(k)
    val param_1 = obj.enQueue(value)
    val param_2 = obj.deQueue()
    val param_3 = obj.Front()
    val param_4 = obj.Rear()
    val param_5 = obj.isEmpty()
    val param_6 = obj.isFull()
    print("enQueue$value: $param_1 ,deQueue: $param_2 ,Front: $param_3 ,Rear: $param_4 ,isEmpty: $param_5 ,isFull: $param_6 ,")
}
