package stack

import java.util.*

/**
 * 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 * 1
 *  \
 *   2
 *  /
 * 3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
class InorderTraversal {

    /**
     * 不使用递归
     */
    fun inorderTraversal(root: TreeNode?): List<Int> {
        if (root == null) return listOf()

        val result = mutableListOf<Int>()
        val stack = Stack<TreeNode>()
        var current: TreeNode? = root

        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.push(current)
                current = current.left
            }
            current = stack.pop()
            result.add(current.`val`)
            current = current.right
        }
        return result
    }

//    /**
//     * 递归遍历
//     */
//    fun inorderTraversal(root: TreeNode?): List<Int> {
//        if (root == null) return listOf()
//        val result = mutableListOf<Int>()
//        traversal(root, result)
//        return result
//    }
//
//    /**
//     * 中序遍历
//     */
//    private fun traversal(node: TreeNode, list: MutableList<Int>) {
//        node.left?.let {
//            traversal(it, list)
//        }
//        list.add(node.`val`)
//        node.right?.let {
//            traversal(it, list)
//        }
//    }

}

/**
 * Definition for a binary tree node.
 */
class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun main() {
    val itr = InorderTraversal()

    val node = TreeNode(1).apply {
        right = TreeNode(2).apply {
            left = TreeNode(3)
        }
    }

    println(itr.inorderTraversal(node))
}