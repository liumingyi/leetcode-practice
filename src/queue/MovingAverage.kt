package queue

import java.util.*

/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 *
 * 示例:
 *
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
class MovingAverage(private val size: Int) {

    /** Initialize your data structure here. */
    private val queue: Queue<Int> = LinkedList()
    private var sum = 0

    fun next(value: Int): Double {
        if (queue.size >= size) {
            sum -= queue.poll()
        }
        if (queue.offer(value)) {
            sum += value
        }
        return sum / queue.size.toDouble()
    }

//    fun next(value: Int): Double {
//        if (queue.size >= size) {
//            queue.poll()
//        }
//        queue.offer(value)
//        var sum = 0.0
//        for (element in queue) {
//            sum += element
//        }
//        return sum / queue.size
//    }

}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * var obj = MovingAverage(size)
 * var param_1 = obj.next(`val`)
 */
fun main() {
    val size = 3
    val obj = MovingAverage(size)
    var param_1 = obj.next(1)
    println(">>0. $param_1")
    param_1 = obj.next(10)
    println(">>1. $param_1")
    param_1 = obj.next(3)
    println(">>2. $param_1")
    param_1 = obj.next(5)
    println(">>3. $param_1")
}