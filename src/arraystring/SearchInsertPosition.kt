package arraystring

/**
 * 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * PS: 这题目怕是过分简单了哦，还是另有玄机？ A：二分查找
 *
 * @author Mingyi
 * @date   2020/6/10
 */

class SearchInsertPosition {

    /*
     ************************************************
     * 普通的查找：时间复杂度为O(n)
     * 使用'二分查找'提高效率，时间复杂度O(log n)
     ************************************************
     * 顺序查找O(n):
     * fun searchInsert(nums: IntArray, target: Int): Int {
     *    val size = nums.size
     *    nums.forEachIndexed { index, i ->
     *        if (i == target) {
     *            return index
     *        }
     *        if (i > target) {
     *            return index
     *        }
     *        if (index == size - 1) {
     *            return index + 1
     *        }
     *    }
     *    return 0
     * }
     ************************************************
     */

    // 二分查找
    fun searchInsert(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        while (left <= right) {
            val mid = (left + right) / 2
            val tamp = nums[mid]
            when {
                tamp == target -> return mid
                tamp < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return left
    }

    /*
     ***************************************************
     * 二分查找的模板：
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/search-insert-position/solution/hua-jie-suan-fa-35-sou-suo-cha-ru-wei-zhi-by-guanp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *  public int searchInsert(int[] nums, int target) {
     *      int left = 0, right = nums.length - 1; // 注意
     *      while(left <= right) { // 注意
     *          int mid = (left + right) / 2; // 注意
     *          if(nums[mid] == target) { // 注意
     *              // 相关逻辑
     *          } else if(nums[mid] < target) {
     *              left = mid + 1; // 注意
     *          } else {
     *              right = mid - 1; // 注意
     *          }
     *      }
     *      // 相关返回值
     *      return 0;
     *  }
     *
     * And :
     *
     *  public int searchInsert(int[] nums, int target) {
     *      int left = 0, right = nums.length; // 注意
     *      while(left < right) { // 注意
     *          int mid = (left + right) / 2; // 注意
     *          if(nums[mid] == target) {
     *              // 相关逻辑
     *          } else if(nums[mid] < target) {
     *              left = mid + 1; // 注意
     *          } else {
     *              right = mid; // 注意
     *          }
     *      }
     *      // 相关返回值
     *      return 0;
     *  }
     *
     ***************************************************
     */
}

fun main() {

    val sip = SearchInsertPosition()
    val arr = intArrayOf(1, 3, 5, 6)
//    * 示例 1:
//    * 输入: [1,3,5,6], 5
//    * 输出: 2
    println(sip.searchInsert(arr, 5))
//    * 示例 2:
//    * 输入: [1,3,5,6], 2
//    * 输出: 1
    println(sip.searchInsert(arr, 2))
//    * 示例 3:
//    * 输入: [1,3,5,6], 7
//    * 输出: 4
    println(sip.searchInsert(arr, 7))
//    * 示例 4:
//    * 输入: [1,3,5,6], 0
//    * 输出: 0
    println(sip.searchInsert(arr, 0))
}