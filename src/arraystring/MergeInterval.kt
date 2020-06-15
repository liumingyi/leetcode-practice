package arraystring

import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * @author Mingyi
 * @date   2020/6/15
 */
class MergeInterval {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        Arrays.sort(intervals) { o1, o2 -> o1[0] - o2[0] }
        val merged = arrayListOf<IntArray>()
        intervals.forEach {
            if (merged.isEmpty() || it[0] > merged[merged.lastIndex][1]) {
                merged.add(it)
                return@forEach
            }
            merged[merged.lastIndex][0] = min(it[0], merged[merged.lastIndex][0])
            merged[merged.lastIndex][1] = max(it[1], merged[merged.lastIndex][1])
        }
        return merged.toTypedArray()
    }
}

fun main() {
    val mi = MergeInterval()
    var input = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 6),
        intArrayOf(8, 10),
        intArrayOf(15, 18)
    )
    mi.merge(input).forEach { arr ->
        print("[${arr[0]}, ${arr[1]}] ")
    }
//    示例 1:
//    输入: [[1,3],[2,6],[8,10],[15,18]]
//    输出: [[1,6],[8,10],[15,18]]
//    解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].

    println()
    input = arrayOf(
        intArrayOf(1, 4),
        intArrayOf(4, 5)
    )
    mi.merge(input).forEach { arr ->
        print("[${arr[0]}, ${arr[1]}] ")
    }
//    示例 2:
//    输入: [[1,4],[4,5]]
//    输出: [[1,5]]
//    解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
}