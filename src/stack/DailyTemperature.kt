package stack

import java.util.*

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
class DailyTemperature {


    fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = Stack<Pair<Int, Int>>()
        val size = temperatures.size
        loop@ for (i in size - 1 downTo 0) {
            val t = temperatures[i]
            var popNum = 0
            while (stack.isNotEmpty()) {
                if (t >= stack.peek().first) {
                    popNum += stack.peek().second
                    stack.pop()
                } else {
                    temperatures[i] = popNum + 1
                    stack.push(Pair(t, temperatures[i]))
                    continue@loop
                }
            }
            stack.push(Pair(t, 0))
            temperatures[i] = 0
        }
        return temperatures
    }

}

fun main() {
    val dt = DailyTemperature()

    var result: IntArray? = null
    // temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
    // 输出 [1, 1, 4, 2, 1, 1, 0, 0]。
//    result = dt.dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73))
//    println(result.joinToString(","))


//    输入：
//    [89,62,70,58,47,47,46,76,100,70]
//    输出：
//    [4,1,4,3,1,2,1,1,0,0]
//    预期：
//    [8,1,5,4,3,2,1,1,0,0]
    result = dt.dailyTemperatures(intArrayOf(89, 62, 70, 58, 47, 47, 46, 76, 100, 70))
    println(result.joinToString(","))
}