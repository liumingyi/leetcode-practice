package arraystring

/**
 * 对角线遍历
 *
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 *
 * @author Mingyi
 * @date   2020/6/24
 */
class DiagonalOrder {

    fun findDiagonalOrder(matrix: Array<IntArray>): IntArray {
        if (matrix.isEmpty()) return intArrayOf()

        val result = mutableListOf<Int>()

        val m = matrix.size
        val n = matrix[0].size

        var down = false

        var xPoint = 0
        var yPoint = 0

        repeat(m + n - 1) {
            while (xPoint in 0 until n && yPoint >= 0 && yPoint < m) {
                result.add(matrix[yPoint][xPoint])
                if (down) {
                    xPoint--
                    yPoint++
                } else {
                    yPoint--
                    xPoint++
                }
            }
            down = if (down) {
                xPoint++
                if (yPoint > m - 1) {
                    xPoint++
                    yPoint--
                }
                false
            } else {
                yPoint++
                if (xPoint > n - 1) {
                    yPoint++
                    xPoint--
                }
                true
            }
        }
        return result.toIntArray()
    }
}

fun main() {

//    输入:
//    [
//        [ 1, 2, 3 ],
//        [ 4, 5, 6 ],
//        [ 7, 8, 9 ]
//    ]
//    输出:  [1,2,4,7,5,3,6,8,9]

    val dio = DiagonalOrder()

    var matrix = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )

//    matrix = arrayOf(
//        intArrayOf(1, 2, 3, 4),
//        intArrayOf(5, 6, 7, 8),
//        intArrayOf(9, 10, 11, 12)
//    )

    val arr = dio.findDiagonalOrder(matrix)

    println(arr.joinToString(" "))
}