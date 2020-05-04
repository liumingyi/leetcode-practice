package No1

/**
 * 1.Two Sum:
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 * 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun main() {
    var result = twoSum(intArrayOf(2, 7, 11, 15), 9)
    println(result.joinToString(","))
    result = twoSum(intArrayOf(3, 2, 4), 6)
    println(result.joinToString(","))
    result = twoSum(intArrayOf(3, 3), 6)
    println(result.joinToString(","))
}

//fun `1~20`.twoSum(nums: IntArray, target: Int): IntArray {
//    val map = mutableMapOf<Int, Int>()
//    // copy value to map, key is position, value is number
//    for (i in nums.indices) {
//        map[nums[i]] = i
//    }
//    nums.forEachIndexed { index, n ->
//        val m = target - n
//        if (map.keys.contains(m) && map[m] != index) {
//            return intArrayOf(index, map[m]!!)
//        }
//    }
//    throw IllegalArgumentException()
//}

/********************************
 *
 ********************************/
// 可简化：单次循环
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    nums.forEachIndexed { index, n ->
        val delta = target - n
        map[delta]?.let { return intArrayOf(it, index) }
        map[n] = index
    }
    throw IllegalArgumentException()
}
