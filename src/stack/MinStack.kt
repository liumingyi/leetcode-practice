package stack

/**
 *
 * 最小栈
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 */
class MinStack {

    /*
     ****************************************************************************
     * 使用两个栈来实现最小栈：一个数据栈，一个记录最小值的辅助栈
     *
     * 两个犯了错误的点:
     * 1. push的时候,小于或者等于当前最小值的数都应该push进辅助栈。（此处等于尤其重要）
     *
     * 2. pop的时候，只有当数据栈出栈元素和辅助栈的栈顶元素相等时，辅助栈才执行出栈操作
     *    (这一点在使用list实现辅助栈时候尤其容易忽略，直接remove,在使用list实现栈时自始至终都应该用不到remove)
     *
     ****************************************************************************
     */

    private val list = mutableListOf<Int>() // 使用list实现的Stack,也可直接使用Stack
    private val min = mutableListOf<Int>() // 使用list实现的Stack,也可直接使用Stack

    fun push(item: Int) {
        if (list.isEmpty() || item <= min[min.size - 1]) {
            min.add(item)
        }
        list.add(item)

    }

    fun pop() {
        if (list.isEmpty()) {
            return
        }
        val lastItem = list.removeAt(list.size - 1)
        val minTop = min[min.size - 1]
        if (minTop == lastItem) {
            min.removeAt(min.size - 1)
        }
    }

    fun top(): Int? {
        if (list.isEmpty()) {
            return null
        }
        return list[list.size - 1]
    }

    fun getMin(): Int? {
        if (min.isEmpty()) {
            return null
        }
        return min[min.size - 1]
    }
}

fun main() {
    /*
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.getMin();   --> Returns -3.
     * minStack.pop();
     * minStack.top();      --> Returns 0.
     * minStack.getMin();   --> Returns -2.
     */
//    ["MinStack","push","top","getMin"]
//    [[],[-1],[],[]]
    val minStack = MinStack()
//    minStack.push(-1)
//    println(minStack.top())
//    minStack.push(-2)
//    minStack.push(0)
//    minStack.push(-3)
//    println(minStack.getMin())
//    minStack.pop()
//    println(minStack.getMin())


//    ["MinStack","push","push","push","getMin","pop","getMin"]
//    [[],[0],[1],[0],[],[],[]]
//    预期[null,null,null,null,0,null,0]
//    minStack.push(0)
//    minStack.push(1)
//    minStack.push(0)
//    println(minStack.getMin())
//    minStack.pop()
//    println(minStack.getMin())


//    ["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
//    [[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]
//    预期 [null,null,null,null,null,null,-1024,null,-1024,null,512]
    minStack.push(512)
    minStack.push(-1024)
    minStack.push(-1024)
    minStack.push(512)
    minStack.pop()
    println(minStack.getMin())
    minStack.pop()
    println(minStack.getMin())
    minStack.pop()
    println(minStack.getMin())

}