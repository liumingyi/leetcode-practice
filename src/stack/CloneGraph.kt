package stack

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * 克隆图
 *
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 *
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list<Node>）。
 *
 * class Node {
 *      public int val;
 *      public List<Node> neighbors;
 * }
 *
 *
 * 测试用例格式：
 *
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 *
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 *
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回
 *
 */

/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList<Node?>()
 * }
 */

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()

    fun fetchValue(): String {
        return "$this : $`val` [ ${neighbors.map { it?.`val` }.joinToString(" , ")} ]"
    }
}

class CloneGraph {

    /**
     ******************************************
     * 深度优先遍历：
     * 使用一个HashMap避免节点多次访问
     ******************************************
     */
    private val visited = HashMap<Node, Node>()

    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        val copy = Node(node.`val`)
        if (node in visited.keys) return visited[node]
        visited[node] = copy
        node.neighbors.filterNotNull().forEach {
            copy.neighbors.add(cloneGraph(it))
        }
        return copy
    }

    /*
     **************************************************
     * 不使用递归
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null

        val visited = HashMap<Node, Node>()
        visited[node] = Node(node.`val`)

        val stack = Stack<Node>()
        stack.push(node)

        while (stack.isNotEmpty()) {
            val top = stack.pop()
            top.neighbors.filterNotNull().forEach {
                if (it !in visited.keys) {
                    visited[it] = Node(it.`val`)
                    stack.push(it)
                }
                visited[top]?.neighbors?.add(visited[it])
            }
        }

        return visited[node]
    }
     *
     **************************************************
     */

}

fun main() {
    val cg = CloneGraph()

    /*
     * 1 --------  2
     * |           |
     * 4 --------  3
     */
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)
    val node4 = Node(4)

    node1.apply {
        neighbors.add(node2)
        neighbors.add(node4)
    }
    node2.apply {
        neighbors.add(node1)
        neighbors.add(node3)
    }
    node3.apply {
        neighbors.add(node2)
        neighbors.add(node4)
    }
    node4.apply {
        neighbors.add(node1)
        neighbors.add(node3)
    }

    println(node1.fetchValue())
    println(node2.fetchValue())
    println(node3.fetchValue())
    println(node4.fetchValue())
    println("==========↓  copy  ↓==========")

    printGraph(cg.cloneGraph(node1))

}

fun printGraph(start: Node?) {
    val set = HashSet<Int>()
    val stack = Stack<Node>()
    stack.add(start)

    while (stack.isNotEmpty()) {
        val n = stack.pop()
        if (n.`val` in set) continue
        set.add(n.`val`)
        println(n.fetchValue())
        n.neighbors.forEach {
            stack.push(it)
        }
    }
}
