package arraystring

/**
 *
 * 旋转矩阵
 *
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *
 * 不占用额外内存空间能否做到？
 *
 * @author Mingyi
 * @date   2020/6/20
 */
class RotateMatrix {
    /*
     * 翻转
     */
    fun rotate(matrix: Array<IntArray>): Unit {
        val n = matrix.size
        // 水平翻转
        for (i in 0 until n / 2) {
            for (j in 0 until n) {
                matrix[i][j] = matrix[n - 1 - i][j].also { matrix[n - 1 - i][j] = matrix[i][j] }
            }
        }
        // 对角翻转
        for (i in 0 until n) {
            for (j in 0 until i) {
                matrix[i][j] = matrix[j][i].also { matrix[j][i] = matrix[i][j] }
            }
        }
    }
}


fun main() {

//    给定 matrix =
//    [
//        [1,2,3],
//        [4,5,6],
//        [7,8,9]
//    ],
//
//    原地旋转输入矩阵，使其变为:
//    [
//        [7,4,1],
//        [8,5,2],
//        [9,6,3]
//    ]
    val rm = RotateMatrix()

    val test = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )

//    test[0][1] = test[2][0].also { test[2][0] = test[0][1] }
    test.forEach { arr ->
        arr.forEach {
            print("$it ")
        }
        println()
    }

    println("============")

    rm.rotate(test)
    test.forEach { arr ->
        arr.forEach {
            print("$it ")
        }
        println()
    }
}