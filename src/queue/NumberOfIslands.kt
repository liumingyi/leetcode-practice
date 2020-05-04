package queue

import java.util.*

/**
 * 岛屿数量
 *
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 */
class NumberOfIslands {

    /*
     ********************************************
     * 遍历网格，如果是'1'（陆地）启动广度优先搜索；
     * 将检索过的陆地全部置为'0'，标识已经检索。
     * 一次广度优先搜索就能将连接在一起的陆地全部检索。
     * 所以：启动几次广度优先搜索就有几个岛屿
     *
     * (同样可以使用'深度优先搜索')
     * Code Skill：将检索过的陆地全部置为'0',以避免重复
     * 查找。
     ********************************************
     */

    companion object {
        const val LAND = '1'
        const val WATER = '0'
        val DIRECTIONS = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    }

    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        var islandNum = 0
        grid.forEachIndexed { row, chars ->
            chars.forEachIndexed { col, c ->
                if (c == LAND) {
                    islandNum++
                    bfsLand(grid, row to col)
                }
            }
        }
        return islandNum
    }

    /**
     * 广度优先搜索
     */
    private fun bfsLand(grid: Array<CharArray>, pair: Pair<Int, Int>) {
        val m = grid.size
        val n = grid[0].size

        val q = LinkedList<Pair<Int, Int>>()
        q.offer(pair)
        grid[pair.first][pair.second] = WATER
        while (q.isNotEmpty()) {
            val point = q.poll()
            val row = point.first
            val col = point.second
            DIRECTIONS.forEach {
                val r = row + it.first
                val c = col + it.second
                if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] == WATER) {
                    return@forEach
                }
                q.offer(r to c)
                grid[r][c] = WATER
            }
        }
    }
}

fun main() {
    /*
    * 输入:
    * 11110
    * 11010
    * 11000
    * 00000
    */
    val grid = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '0', '0')
    )
    /*
    * 11000
    * 11000
    * 00100
    * 00011
     */
    val grid2 = arrayOf(
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '1', '0', '0'),
        charArrayOf('0', '0', '0', '1', '1')
    )


    testSolution(grid)
    testSolution(grid2)
}

private fun testSolution(grid: Array<CharArray>) {
    println("=======Source=======")
    grid.forEach {
        it.forEach { it1 ->
            print("$it1  ")
        }
        println()
    }
    println("--------------------")
    val noi = NumberOfIslands()
    val islandNum = noi.numIslands(grid)
    println("Island: $islandNum")
}