package arraystring

/**
 * 至少是其他数字两倍的最大数
 *
 * 在一个给定的数组nums中，总是存在一个最大元素 。
 * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
 * 如果是，则返回最大元素的索引，否则返回-1。
 *
 * 示例 1:
 * 输入: nums = [3, 6, 1, 0]
 * 输出: 1
 * 解释: 6是最大的整数, 对于数组中的其他整数,
 * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
 *
 * 示例 2:
 * 输入: nums = [1, 2, 3, 4]
 * 输出: -1
 * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
 *
 * 提示:
 * nums 的长度范围在[1, 50].
 * 每个 nums[i] 的整数范围在 [0, 100].
 *
 * @author Mingyi
 * @date   2020/5/27
 */
class MoreThanTwice {
    fun dominantIndex(nums: IntArray): Int {
        var maxIndex = 0
        var max = 0
        var maxTwice = 0
        nums.forEachIndexed { index, i ->
            if (max < i) {
                maxTwice = max * 2
                max = i
                maxIndex = index
            } else if (maxTwice < i * 2) {
                maxTwice = i * 2
            }
        }
        if (max < maxTwice) {
            return -1
        }
        return maxIndex
    }
}

fun main() {
//    * 示例 1:
//    * 输入: nums = [3, 6, 1, 0]
//    * 输出: 1
//    * 解释: 6是最大的整数, 对于数组中的其他整数,
//    * 6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
//    *
//    * 示例 2:
//    * 输入: nums = [1, 2, 3, 4]
//    * 输出: -1
//    * 解释: 4没有超过3的两倍大, 所以我们返回 -1.
    val mtt = MoreThanTwice()
    var nums = intArrayOf(3, 6, 1, 0)
//    println(mtt.dominantIndex(nums))
//
//    nums = intArrayOf(1, 2, 3, 4)
//    println(mtt.dominantIndex(nums))
//
//    nums = intArrayOf(0, 0, 3, 2)
//    println(mtt.dominantIndex(nums))

    nums = intArrayOf(0, 1, 1, 2)
    println(mtt.dominantIndex(nums))
}