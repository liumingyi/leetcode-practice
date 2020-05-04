package queue

import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * 完全平方数
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
class CompleteSquare {

    /*
     * 解法：贪心 + BFS
     *****************************************************************************
     * 最初想法：使用BFS，从比目标数小的最大平方数开始，使用目标数-平方数得到剩下的数
     * 一直下去，但是这样无法找到最小组合。所以应该是 贪心 + BFS。固定分解的个数。以个数为层级
     * 抽象出一颗树。
     * 示例：
     *        目标数: 12                .... 第一层
     *           ×                     不是完全平方数进入下一层
     * 11(=12-1) , 8(=12-4), 3(=12-9)  .... 第二层
     *    ×      ,    ×    ,    ×      没有完全平方数进入下一层
     *
     * 10(11-1)  , 7(8-1)  , 2(=3-1)   .... 第三层
     * 7 (11-4)    4(8-4)              存在完全平方数4，则最短路径3层 4 + 4 + 4
     * 3 (11-9)
     *
     *****************************************************************************
     * 此题的另4种解法：
     *
     * 方法一：暴力枚举法 [超出时间限制]
     *
     * 方法二：动态规划 （看不懂 T_T ）
     *
     * 方法三：贪心枚举 ， 在暴力枚举基础上加入贪心算法
     *
     * 方法四：数学方法。这个最牛逼了，但是完全看不懂，T_T
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *****************************************************************************
     */

    fun numSquares(n: Int): Int {
        if (isPerfectSquare(n)) return 1

        val q = LinkedList<Int?>()
        q.offer(n)
        q.offer(null)

        var step = 2

        while (q.isNotEmpty()) {
            val num = q.poll()
            if (num == null) {
                step++
                if (q.peek() != null) q.offer(null)
                continue
            }
            var s = 1
            while (s * s <= num) {
                val d = num - s * s
                if (isPerfectSquare(d)) {
                    return step
                } else {
                    q.offer(d)
                }
                s++
            }
        }
        return step
    }

    private fun isPerfectSquare(d: Int): Boolean {
        val sqrt = sqrt(d.toDouble())
        return (sqrt - floor(sqrt)) == .0
    }
}

fun main() {
    /* 示例 1:
     * 输入: n = 12
     * 输出: 3
     * 解释: 12 = 4 + 4 + 4.
     */
    val cs = CompleteSquare()
    var n = 12
    println("$n >> ${cs.numSquares(n)}")

    /* 示例 2:
     * 输入: n = 13
     * 输出: 2
     * 解释: 13 = 4 + 9.
     */
    n = 13
    println("$n >> ${cs.numSquares(n)}")


    /* 示例 3:
     * 输入: n = 1
     * 输出: 1
     * 解释: 1.
     */
    n = 1
    // 1
    println("$n >> ${cs.numSquares(n)}")

    n = 2
    // 2
    println("$n >> ${cs.numSquares(n)}")

    n = 4
    // 1
    println("$n >> ${cs.numSquares(n)}")
}