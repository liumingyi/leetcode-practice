package summary.queue_stack

import java.util.*

/**
 * 用队列实现栈
 *
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 * 注意:
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 *
 * @author Mingyi
 * @date   2020/5/21
 */
class BuildStack {

    /*
     ******************************************
     * LinkedList 既拥有栈的操作也拥有队列的操作
     ******************************************
     */

    /** Initialize your data structure here. */
    private var queueA: Queue<Int> = LinkedList()
    private var queueB: Queue<Int> = LinkedList()

    private var top: Int? = null

    /** Push element x onto stack. */
    fun push(x: Int) {
        queueA.offer(x)
        top = x
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        while (queueA.size > 1) {
            if (queueA.size == 2) {
                top = queueA.peek()
            }
            queueB.offer(queueA.poll())
        }
        val temp = queueA
        queueA = queueB
        queueB = temp
        return queueB.poll()
    }

    /** Get the top element. */
    fun top(): Int {
        return top ?: throw RuntimeException("No element")
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return queueA.isEmpty()
    }
}

fun main() {
    /*
     * Your MyStack object will be instantiated and called as such:
     * var obj = MyStack()
     * obj.push(x)
     * var param_2 = obj.pop()
     * var param_3 = obj.top()
     * var param_4 = obj.empty()
     */
    val bs = BuildStack()
    bs.push(1)
    bs.push(2)
//    bs.push(3)
    println(bs.top())
    println(bs.pop())
    println(bs.empty())

}