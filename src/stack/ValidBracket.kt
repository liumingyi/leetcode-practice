package stack

import java.util.*

/**
 * 有效括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */
class ValidBracket {
    /*
     ********************************************
     * 新单词：( ) parenthesis or round brackets 圆括号
     *        [ ] square brackets 方括号
     *        {} curly brackets or braces 大括号
     ********************************************
     */

    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        for (c in s) {
            when (c) {
                '(' -> stack.push(')')
                '[' -> stack.push(']')
                '{' -> stack.push('}')
                else -> {
                    if (stack.isEmpty() || stack.pop() != c) {
                        return false
                    }
                }
            }
        }
        return stack.isEmpty()
    }

    /*
     *****************************************************************
     *  思路：
     * 栈顶是'('的时候 => push ']','}'是非法的 => push ')' 则闭合将'('出栈；
     * 其他两个括号相同处理，结束的时候如果栈为空表示合法，反正非法
     *
     * 编码上可以简化：
     * 如果是'('将其闭合的')'入栈，减少一次判断,即栈顶的元素就是下一次应该输入的元素
     * 如果扫描到结束符号，和栈顶的符号不一致表示是非法的
     *****************************************************************
     */
    fun isValid_0(s: String): Boolean {
        val stack = Stack<Char>()
        s.forEach { c ->
            if (stack.isEmpty()) {
                stack.push(c)
                return@forEach
            }

            when (stack.peek()) {
                '(' -> {
                    if (c == ']' || c == '}') {
                        return false
                    }
                    if (c == ')') {
                        stack.pop()
                        return@forEach
                    }
                    stack.push(c)
                }
                '[' -> {
                    if (c == ')' || c == '}') {
                        return false
                    }
                    if (c == ']') {
                        stack.pop()
                        return@forEach
                    }
                    stack.push(c)

                }
                '{' -> {
                    if (c == ')' || c == ']') {
                        return false
                    }
                    if (c == '}') {
                        stack.pop()
                        return@forEach
                    }
                    stack.push(c)
                }
            }
        }
        return stack.isEmpty()
    }
}

fun main() {

    val vb = ValidBracket()
    // true
    println(vb.isValid(""))
    // true
    println(vb.isValid("()"))
    // true
    println(vb.isValid("()[]{}"))
    // false
    println(vb.isValid("(]"))
    // false
    println(vb.isValid("([)]"))
    // true
    println(vb.isValid("{[]}"))
}
