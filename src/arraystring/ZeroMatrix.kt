package arraystring

/**
 * 零矩阵
 *
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * @author Mingyi
 * @date   2020/6/23
 */
class ZeroMatrix {
    fun setZeroes(matrix: Array<IntArray>): Unit {
        // 可以用第一行，第一列代替这两个set
        val zeroRow = mutableSetOf<Int>()
        val zeroColumn = mutableSetOf<Int>()
        matrix.forEachIndexed { row, ints ->
            ints.forEachIndexed { column, i ->
                if (i == 0) {
                    zeroRow.add(row)
                    zeroColumn.add(column)
                }
            }
        }

        // 清0
        zeroRow.forEach { row ->
            for (i in matrix[row].indices) {
                matrix[row][i] = 0
            }
        }
        zeroColumn.forEach { column ->
            for (i in matrix.indices) {
                matrix[i][column] = 0
            }
        }
    }
}

fun main() {
//    示例 1：
//    输入：
//    [
//        [1,1,1],
//        [1,0,1],
//        [1,1,1]
//    ]
//    输出：
//    [
//        [1,0,1],
//        [0,0,0],
//        [1,0,1]
//    ]
    val zm = ZeroMatrix()
    var test = arrayOf(
        intArrayOf(1, 1, 1),
        intArrayOf(1, 0, 1),
        intArrayOf(1, 1, 1)
    )
//    [[0,0,0,5],[4,3,1,4],[0,1,1,4],[1,2,1,3],[0,0,1,1]]
    test = arrayOf(
        intArrayOf(0, 0, 0, 5),
        intArrayOf(4, 3, 1, 4),
        intArrayOf(0, 1, 1, 4),
        intArrayOf(1, 2, 1, 3),
        intArrayOf(0, 0, 1, 1)
    )
    zm.setZeroes(test)

    test.forEach { arr ->
        arr.forEach {
            print("$it ")
        }
        println()
    }
}