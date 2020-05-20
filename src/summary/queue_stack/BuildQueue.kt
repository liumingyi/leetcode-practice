package summary.queue_stack

import java.util.*

/**
 * 【用栈实现队列】
 *
 * 使用栈实现队列的下列操作：
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 * 示例:
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 * 说明:
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 * @author Mingyi
 * @date   2020/5/20
 */
class MyQueue() {

    /*
     **********************************************************
     * 方法一：使用两个栈互导
     *   A          A           B
     * |   |      |   |       |   |   Step1:
     * |   |      | 2 |       | 1 |   A pop all to stack B
     * | 1 |  ->  | 1 |       | 2 |   A is Empty , B.pop == Queue.push
     *  ---        ---         ---
     *
     *   A         B           A        B
     * |   |     |   |       | 3 |    | 1 |   when Queue.push(3)
     * |   |     | 1 |   ->  | 2 |    | 2 |   1. B pop all to Stack A
     * |*3 |     | 2 |       | 1 |    | 3 |   2. A.push(3)
     *  ---       ---         ---      ---    3. do *Step1
     *
     *   A         B           A        B
     * |   |     |   |       | 4 |    | 1 |   when Queue.push(4)
     * |   |     | 1 |       | 3 |    | 2 |   1. B pop all to Stack A
     * |   |     | 2 |   ->  | 2 |    | 3 |   2. A.push(4)
     * |*4 |     | 3 |       | 1 |    | 4 |   3. do *Step1
     *  ---       ---         ---      ---
     *
     * push 操作的时间复杂度：O(n)
     *  pop 操作的时间复杂度：O(1)
     **********************************************************
     * 方法一代码：
     *     private val stackA = Stack<Int>()
     *     private val stackB = Stack<Int>()
     *
     *     fun push(x: Int) {
     *         while (stackB.isNotEmpty()) {
     *             stackA.push(stackB.pop())
     *         }
     *         stackA.push(x)
     *         while (stackA.isNotEmpty()) {
     *             stackB.push(stackA.pop())
     *         }
     *     }
     *
     *     fun pop(): Int {
     *         return stackB.pop()
     *     }
     *
     *     fun peek(): Int {
     *         return stackB.peek()
     *     }
     *
     *     fun empty(): Boolean {
     *         return stackB.isEmpty()
     *     }
     **********************************************************
     */

    /*
     **********************************************************
     * 方法二：使用两个栈，直到StackB为空时才将StackA导到B中
     *
     *   A        A        B
     * | 3 | -> |   |    | 1 |   1. Queue.push() == A.push()
     * | 2 |    |   |    | 2 |   2. if Queue.pop, A pop all to Stack B
     * | 1 |    |   |    | 3 |         Queue.pop() == B.pop()
     *  ---      ---      ---    3. if B.isEmpty() , A pop all to Stack B
     *
     * push 操作的时间复杂度：O(1)
     *  pop 操作的时间复杂度：摊还时间复杂度 O(1) , 最坏情况的时间负责度 O(n)
     *
     * 参考LeetCode：
     * https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode/
     *
     * 关于摊还时间复杂度：
     *
     * 【摊还分析】
     * 摊还分析给出了所有操作的平均性能。摊还分析的核心在于，最坏情况下的操作一旦发生了一次，
     * 那么在未来很长一段时间都不会再次发生，这样就会均摊每次操作的代价。
     *
     * 来看下面这个例子，从一个空队列开始，依次执行下面这些操作：
     *
     *      push_1, push_2,...,push_n, pop_1, pop_2, ... , pop_n
     *
     * 单次 出队 操作最坏情况下的时间复杂度为 O(n)。
     * 考虑到我们要做 n 次出队操作，如果我们用最坏情况下的时间复杂度来计算的话，那么所有操作的时间复杂度为 O(n^2)
     *
     * 然而，在一系列的操作中，最坏情况不可能每次都发生，可能一些操作代价很小，另一些代价很高。
     * 因此，如果用传统的最坏情况分析，那么给出的时间复杂度是远远大于实际的复杂度的。
     * 例如，在一个动态数组里面只有一些插入操作需要花费线性的时间，而其余的一些插入操作只需花费常量的时间。
     *
     * 在上面的例子中，出队 操作最多可以执行的次数跟它之前执行过 入队 操作的次数有关。
     * 虽然一次 出队 操作代价可能很大，但是每 n 次 入队 才能产生这么一次代价为 n 的 出队 操作。
     * 因此所有操作的总时间复杂度为：
     * n(所有的入队操作产生） + 2 * n(第一次出队操作产生） + n - 1(剩下的出队操作产生），
     * 所以实际时间复杂度为 O(2*n)。于是我们可以得到每次操作的平均时间复杂度为 O(2n/2n)=O(1)。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     **********************************************************
     * 方法二代码：
     *
     *     private val stackA = Stack<Int>()
     *     private val stackB = Stack<Int>()
     *     private var front: Int? = null
     *
     *     fun push(x: Int) {
     *         if (stackA.isEmpty()) {
     *             front = x
     *         }
     *         stackA.push(x)
     *     }
     *
     *     fun pop(): Int {
     *         if (stackB.isEmpty()) {
     *             while (stackA.isNotEmpty()) {
     *                 stackB.push(stackA.pop())
     *             }
     *         }
     *         return stackB.pop()
     *     }
     *
     *     fun peek(): Int {
     *         return if (stackB.isEmpty()) {
     *             front ?: throw RuntimeException("No element")
     *         } else {
     *             stackB.peek()
     *         }
     *     }
     *
     *     fun empty(): Boolean {
     *         return stackB.isEmpty() && stackA.isEmpty()
     *     }
     *
     **********************************************************
     */

    /** Initialize your data structure here. */
    private val stackA = Stack<Int>()
    private val stackB = Stack<Int>()
    private var front: Int? = null

    /** Push element x to the back of queue. */
    fun push(x: Int) {
        if (stackA.isEmpty()) {
            front = x
        }
        stackA.push(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        if (stackB.isEmpty()) {
            while (stackA.isNotEmpty()) {
                stackB.push(stackA.pop())
            }
        }
        return stackB.pop()
    }

    /** Get the front element. */
    fun peek(): Int {
        return if (stackB.isEmpty()) {
            front ?: throw RuntimeException("No element")
        } else {
            stackB.peek()
        }
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return stackB.isEmpty() && stackA.isEmpty()
    }

}

fun main() {
//    * MyQueue queue = new MyQueue();
//    *
//    * queue.push(1);
//    * queue.push(2);
//    * queue.peek();  // 返回 1
//    * queue.pop();   // 返回 1
//    * queue.empty(); // 返回 false

    val queue = MyQueue()

    queue.push(1)
    queue.push(2)
    println(queue.peek())
    println(queue.pop())
    println(queue.empty())
}