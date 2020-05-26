package summary.queue_stack

import java.util.*

/**
 * 542. 01矩阵
 *
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 *
 * @author Mingyi
 * @date   2020/5/24
 */
class Matrix01 {

    companion object {
        val DIRECTIONS = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    }

    /*
     *********************************************************
     * 方法一：广度优先搜索
     * 对每一个为1的点,进行广度优先搜索。
     * 同时也可：
     * 从为0的点出发，反推所有1的点距离
     *********************************************************
     *
     *********************************************************
     * 方法二：动态规划
     *********************************************************
     */
//    从零开始广度优先遍历
//    fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> {
//        if (matrix.isEmpty()) return matrix
//        val m = matrix.size
//        val n = matrix[0].size
//        val queue: Queue<Pair<Int, Int>> = LinkedList()
//        val measured = mutableSetOf<Pair<Int, Int>>()
//        matrix.forEachIndexed { r, arr ->
//            arr.forEachIndexed { c, num ->
//                if (num == 0) {
//                    queue.offer(r to c)
//                    measured.add(r to c)
//                } else {
//                    matrix[r][c] = 0
//                }
//            }
//        }
//        while (queue.isNotEmpty()) {
//            val cur = queue.poll()
//            DIRECTIONS.forEach { (r, c) ->
//                val newR = cur.first + r
//                val newC = cur.second + c
//                if (newC < 0 || newR < 0 || newC >= n || newR >= m || (newR to newC) in measured) {
//                    // out bounds
//                    return@forEach
//                }
//                matrix[newR][newC] = matrix[cur.first][cur.second] + 1
//                queue.offer(newR to newC)
//                measured.add(newR to newC)
//            }
//        }
//        return matrix
//    }

    // 从1开始进行广度优先搜索。
    fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> {
        if (matrix.isEmpty()) return matrix
        val m = matrix.size
        val n = matrix[0].size
        val queue: Queue<Pair<Int, Int>?> = LinkedList()
        var distance = 1
        matrix.forEachIndexed { r, arr ->
            arr.forEachIndexed inner@{ c, num ->
                if (num == 0) {
                    return@inner
                }
                queue.offer(r to c)
                queue.offer(null)
                while (queue.isNotEmpty()) {
                    val current = queue.poll()
                    if (current == null) {
                        distance++
                        if (queue.isNotEmpty()) {
                            queue.offer(null)
                        }
                        continue
                    }
                    DIRECTIONS.forEach dir@{
                        val nextR = current.first + it.first
                        val nextC = current.second + it.second
                        if (nextR < 0 || nextC < 0 || nextR >= m || nextC >= n) {
                            // out bound
                            return@dir
                        }
                        if (matrix[nextR][nextC] == 0) {
                            matrix[r][c] = distance
                            distance = 1
                            queue.clear()
                            return@inner
                        } else {
                            queue.offer(nextR to nextC)
                        }
                    }
                }
            }
        }
        return matrix
    }
}

fun main() {
    val mt = Matrix01()
//    val matrix = arrayOf(
//        intArrayOf(0, 0, 0),
//        intArrayOf(0, 1, 0),
//        intArrayOf(0, 0, 0)
//    )
//    val matrix = arrayOf(
//        intArrayOf(0, 0, 0),
//        intArrayOf(0, 1, 0),
//        intArrayOf(1, 1, 1)
//    )

//    val matrix = arrayOf(
////      intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
//        intArrayOf(1, 0, 1, 1, 0, 0, 1, 0, 0, 1),//0
//        intArrayOf(0, 1, 1, 0, 1, 0, 1, 0, 1, 1),//1
//        intArrayOf(0, 0, 1, 0, 1, 0, 0, 1, 0, 0),//2
//        intArrayOf(1, 0, 1, 0, 1, 1, 1, 1, 1, 1),//3
//        intArrayOf(0, 1, 0, 1, 1, 0, 0, 0, 0, 1),//4
//        intArrayOf(0, 0, 1, 0, 1, 1, 1, 0, 1, 0),//5
//        intArrayOf(0, 1, 0, 1, 0, 1, 0, 0, 1, 1),//6
//        intArrayOf(1, 0, 0, 0, 1, 1, 1, 1, 0, 1),//7
//        intArrayOf(1, 1, 1, 1, 1, 1, 1, 0, 1, 0),//8
//        intArrayOf(1, 1, 1, 1, 0, 1, 0, 0, 1, 1) //9
//    )

    val matrix = arrayOf(
//      intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        intArrayOf(1, 1, 0, 0, 1, 0, 0, 1, 1, 0),//0
        intArrayOf(1, 0, 0, 1, 0, 1, 1, 1, 1, 1),//1
        intArrayOf(1, 1, 1, 0, 0, 1, 1, 1, 1, 0),//2
        intArrayOf(0, 1, 1, 1, 0, 1, 1, 1, 1, 1),//3
        intArrayOf(0, 0, 1, 1, 1, 1, 1, 1, 1, 0),//4
        intArrayOf(1, 1, 1, 1, 1, 1, 0, 1, 1, 1),//5
        intArrayOf(0, 1, 1, 1, 1, 1, 1, 0, 0, 1),//6
        intArrayOf(1, 1, 1, 1, 1, 0, 0, 1, 1, 1),//7
        intArrayOf(0, 1, 0, 1, 1, 0, 1, 1, 1, 1),//8
        intArrayOf(1, 1, 1, 0, 1, 0, 1, 1, 1, 1)//9
    )

    matrix.forEach { arr ->
        arr.forEach {
            print(it)
        }
        println()
    }
    println()
    val result = mt.updateMatrix(matrix)
    result.forEach { arr ->
        arr.forEach {
            print(it)
        }
        println()
    }

    val right = arrayOf(
        intArrayOf(1, 0, 1, 1, 0, 0, 1, 0, 0, 1),
        intArrayOf(0, 1, 1, 0, 1, 0, 1, 0, 1, 1),
        intArrayOf(0, 0, 1, 0, 1, 0, 0, 1, 0, 0),
        intArrayOf(1, 0, 1, 0, 1, 1, 1, 1, 1, 1),
        intArrayOf(0, 1, 0, 1, 1, 0, 0, 0, 0, 1),
        intArrayOf(0, 0, 1, 0, 1, 1, 1, 0, 1, 0),
        intArrayOf(0, 1, 0, 1, 0, 1, 0, 0, 1, 1),
        intArrayOf(1, 0, 0, 0, 1, 2, 1, 1, 0, 1),
        intArrayOf(2, 1, 1, 1, 1, 2, 1, 0, 1, 0),
        intArrayOf(3, 2, 2, 1, 0, 1, 0, 0, 1, 1)
    )

    /*
     * 输入：
     * [[1,1,0,0,1,0,0,1,1,0],[1,0,0,1,0,1,1,1,1,1],[1,1,1,0,0,1,1,1,1,0],[0,1,1,1,0,1,1,1,1,1],[0,0,1,1,1,1,1,1,1,0],
     * [1,1,1,1,1,1,0,1,1,1],[0,1,1,1,1,1,1,0,0,1],[1,1,1,1,1,0,0,1,1,1],[0,1,0,1,1,0,1,1,1,1],[1,1,1,0,1,0,1,1,1,1]]
     */
    val r2 = arrayOf(
        intArrayOf(2, 1, 0, 0, 1, 0, 0, 1, 1, 0),
        intArrayOf(1, 0, 0, 1, 0, 1, 1, 2, 2, 1),
        intArrayOf(1, 1, 1, 0, 0, 1, 2, 2, 1, 0),
        intArrayOf(0, 1, 2, 1, 0, 1, 2, 3, 2, 1),
        intArrayOf(0, 0, 1, 2, 1, 2, 1, 2, 1, 0),
        intArrayOf(1, 1, 2, 3, 2, 1, 0, 1, 1, 1),
        intArrayOf(0, 1, 2, 3, 2, 1, 1, 0, 0, 1),
        intArrayOf(1, 2, 1, 2, 1, 0, 0, 1, 1, 2),
        intArrayOf(0, 1, 0, 1, 1, 0, 1, 2, 2, 3),
        intArrayOf(1, 2, 1, 0, 1, 0, 1, 2, 3, 4)
    )
}