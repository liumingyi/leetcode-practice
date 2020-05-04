package stack

import java.util.*

/**
 * 岛屿数量 (DFS深度优先搜索)  (广度优先搜索BFS参见[queue.NumberOfIslands])
 *
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。
 * 你可以假设网格的四个边均被水包围。
 */
class NumberOfIslands {

    companion object {
        const val LAND = '1'
        const val WATER = '0'
        val DIRECTIONS = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
    }

    fun numIslands(grid: Array<CharArray>): Int {
        var num = 0
        grid.forEachIndexed { row, arr ->
            arr.forEachIndexed { column, c ->
                if (c == LAND) {
                    num++
                    dfs(grid, row to column)
                }
            }
        }
        return num
    }


    /**
     * 深度优先搜索
     */
    fun dfs(grid: Array<CharArray>, current: Pair<Int, Int>) {
        val m = grid.size
        val n = grid[0].size

        val stack = Stack<Pair<Int, Int>>()
        stack.push(current)
        grid[current.first][current.second] = WATER

        while (stack.isNotEmpty()) {
            val position = stack.pop()
            val row = position.first
            val col = position.second

            DIRECTIONS.forEach {
                val r = row + it.first
                val c = col + it.second
                if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] != LAND) {
                    return@forEach
                }
                stack.push(Pair(r, c))
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