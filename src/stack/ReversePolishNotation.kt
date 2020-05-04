package stack

import java.util.*

/**
 * 逆波兰表达式求值
 *
 * 根据逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * 示例 1：
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 *
 * 示例 2：
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 *
 * 示例 3：
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 */
class ReversePolishNotation {

    /*
     ***********************************************
     * 知识点：
     * 逆波兰式，也叫后缀表达式（将运算符写在操作数之后）
     ***********************************************
     */

    fun evalRPN(tokens: Array<String>): Int? {
        val stack = Stack<Int>()
        tokens.forEach { s ->
            when (s) {
                "+", "-", "*", "/" -> {
                    if (stack.size < 2) {
                        return null
                    }
                    val current = stack.pop()
                    val previous = stack.pop()
                    calculate(previous, current, s)?.let {
                        stack.push(it)
                    } ?: return null
                }
                else -> {
                    stack.push(s.toInt())
                }
            }
        }
        return stack.pop()
    }

    private fun calculate(pre: Int, cur: Int, s: String): Int? {
        return when (s) {
            "+" -> {
                pre + cur
            }
            "-" -> {
                pre - cur
            }
            "*" -> {
                pre * cur
            }
            "/" -> {
                pre / cur
            }
            else -> {
                null
            }
        }
    }
}

fun main() {
    val rpn = ReversePolishNotation()

//    输入: ["2", "1", "+", "3", "*"]
//    输出: 9
//    解释: ((2 + 1) * 3) = 9
    println(rpn.evalRPN(arrayOf("2", "1", "+", "3", "*")))

//    示例 2：
//    输入: ["4", "13", "5", "/", "+"]
//    输出: 6
//    解释: (4 + (13 / 5)) = 6
    println(rpn.evalRPN(arrayOf("4", "13", "5", "/", "+")))
//
////    示例 3：
////    输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
////    输出: 22
    println(rpn.evalRPN(arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")))
}