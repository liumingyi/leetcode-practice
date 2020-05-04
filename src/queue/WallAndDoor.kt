package queue

import java.util.*

/**
 * 墙与门
 *
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 2^31 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近 门的距离，如果无法到达门，则填 INF 即可。
 *
 * 示例：
 *
 * 给定二维网格：
 *
 *  INF  -1  0  INF
 *  INF INF INF  -1
 *  INF  -1 INF  -1
 *  0  -1 INF INF
 *
 * 运行完你的函数后，该网格应该变成：
 *
 *  3  -1   0   1
 *  2   2   1  -1
 *  1  -1   2  -1
 *  0  -1   3   4
 */
class Solution {

    companion object {
        const val WALL = -1
        const val GATE = 0
        const val EMPTY = Int.MAX_VALUE
        val DIRECTIONS = listOf(
            Pair(1, 0),
            Pair(-1, 0),
            Pair(0, 1),
            Pair(0, -1)
        )
    }

    /**
     * 暴力查找
     *
     * 时间复杂度：O((mn)^2)
     * 控件复杂度：O(mn)
     */
    fun wallsAndGates(rooms: Array<IntArray>): Unit {
        if (rooms.isEmpty()) return
        rooms.forEachIndexed { row, arr ->
            arr.forEachIndexed { col, _ ->
                if (rooms[row][col] == EMPTY) {
                    rooms[row][col] = calculateDistanceToNearestGate(rooms, row, col)
                }
            }
        }
    }

    private fun calculateDistanceToNearestGate(rooms: Array<IntArray>, startRow: Int, startCol: Int): Int {
        val m = rooms.size
        val n = rooms[0].size // room : type
        val distance = Array(m) { IntArray(n) } // distance
        val queue = LinkedList<Pair<Int, Int>>() // point
        queue.offer(Pair(startRow, startCol))
        while (queue.isNotEmpty()) {
            val currentPoint = queue.poll()
            val row = currentPoint.first
            val col = currentPoint.second
            DIRECTIONS.forEach {
                val r = row + it.first
                val c = col + it.second
                if (r < 0 || c < 0 || r >= m || c >= n
                    || rooms[r][c] == WALL
                    || distance[r][c] != 0
                ) {
                    return@forEach
                }
                distance[r][c] = distance[row][col] + 1
                if (rooms[r][c] == GATE) {
                    return distance[r][c]
                }
                queue.offer(Pair(r, c))
            }
        }
        return Int.MAX_VALUE
    }

    /***************************************
     *
     * 从门开始进行广度有限搜索(BFS , Breadth-First Search)
     *
     **************************************/

    fun wallsAndGates_01(rooms: Array<IntArray>): Unit {
        if (rooms.isEmpty()) return

        val m = rooms.size
        val n = rooms[0].size

        val queue: Queue<Pair<Int, Int>> = LinkedList()
        rooms.forEachIndexed { row, arr ->
            arr.forEachIndexed { col, i ->
                if (i == GATE) {
                    queue.add(Pair(row, col))
                }
            }
        }

        while (queue.isNotEmpty()) {
            val point = queue.poll()
            val row = point.first
            val col = point.second
            DIRECTIONS.forEach {
                val r = row + it.first
                val c = col + it.second
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    return@forEach
                }
                if (rooms[r][c] < rooms[row][col] + 1) return@forEach
                rooms[r][c] = rooms[row][col] + 1
                queue.add(Pair(r, c))
            }
        }
    }
}

fun main() {
    val inf = 2147483647 //2^31 - 1
    val testArray = arrayOf(
        intArrayOf(inf, -1, 0, inf),
        intArrayOf(inf, inf, inf, -1),
        intArrayOf(inf, -1, inf, -1),
        intArrayOf(0, -1, inf, inf)
    )

    println("-------Source-------")

    testArray.forEach {
        it.forEach { it1 ->
            val show = if (it1 == inf) "INF" else "$it1 "
            print("$show  ")
        }
        println()
    }

    println("--------------------")

    val s = Solution()
    s.wallsAndGates_01(testArray)
    testArray.forEach {
        it.forEach { it1 ->
            val show = if (it1 == inf) "INF" else "$it1 "
            print("$show  ")
        }
        println()
    }
}