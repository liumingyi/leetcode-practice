package stack

import java.util.*

/**
 * 目标和
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例 1:
 *
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 * 注意:
 *
 * 数组非空，且长度不会超过20。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果能被32位整数存下。
 */
class TargetSum {

    /**
     *******************************************************
     * 将在数组中插入符号后计算得到的数看做一棵树
     * 使用DFS遍历整个树，查找出在最终点结果为target的路径数量
     *
     * TODO 该题目可作动态规划求解
     *******************************************************
     */
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        var count = 0

        val stack = Stack<Pair<Int, Int>>()
        stack.push(Pair(0, -1))

        while (stack.isNotEmpty()) {
            val top = stack.pop()

            val level = top.second + 1
            if (level >= nums.size) {
                continue
            }

            val sum = top.first + nums[level]
            val diff = top.first - nums[level]

            if (level == nums.size - 1) {
                if (sum == target) {
                    count++
                }
                if (diff == target) {
                    count++
                }
                continue
            }

            stack.push(Pair(sum, level))
            stack.push(Pair(diff, level))

        }
        return count
    }
}


fun main() {

    val ts = TargetSum()
    var nums = intArrayOf(1, 1, 1, 1, 1)
    var target = 3
    println(ts.findTargetSumWays(nums, target))


    nums = intArrayOf(1)
    target = 1
    println(ts.findTargetSumWays(nums, target))

    nums = intArrayOf(1, 0)
    target = 1
    println(ts.findTargetSumWays(nums, target))
}