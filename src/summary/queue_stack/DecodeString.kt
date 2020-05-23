package summary.queue_stack

import java.util.*

/**
 * 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * @author Mingyi
 * @date   2020/5/22
 */
class DecodeString {

    /*
     ********************************************************
     * eg: s = 3[a2[c]]
     *  遍历检查 s 的每一位
     *  num 记录每一段字符串的标记数字 : 初始 0
     *  re 记录每一段字符串的重复string : 初始 ""
     *  1. 3 -> 累加 num
     *  2. [ -> 标识一个重复字符串的开始 -> Stack.push(num, re)
     *       重置 num = 0 , re = ""
     *         |       |
     * stack:  |       |
     *         | (3,") |
     *          -------
     *  3. a -> 累加 re
     *  4. 2 -> 累加 num
     *  5. [ -> 重复step2
     *         |         |
     * stack:  | (2,"a") |
     *         | (3, "") |
     *          ---------
     *  6. c -> 累加 re
     *  7. ] -> top = Stack.pop()
     *          re = top.re + re.repeat(top.num)
     *             =   "a"  + "c".repeat(2)
     *             = "acc"
     *  8. ] -> 重复 step7
     *          re = "" + "acc".repeat(3)
     *             = "accaccacc"
     * End.
     ********************************************************
     */
    fun decodeString(s: String): String {
        val stack = Stack<Pair<Int, String>>()
        var re = ""
        var num = 0
        s.forEach { c ->
            when (c) {
                '[' -> {
                    stack.push(Pair(num, re))
                    num = 0
                    re = ""
                }
                ']' -> {
                    val (n, r) = stack.pop()
                    re = r + re.repeat(n)
                }
                in '0'..'9' -> {
                    num = num * 10 + c.toString().toInt()
                }
                else -> {
                    re += c.toString()
                }
            }
        }
        return re
    }
}

fun main() {
//    * 示例:
//    * s = "3[a]2[bc]", 返回 "aaabcbc".
//    * s = "3[a2[c]]", 返回 "accaccacc".
//    * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
    val ds = DecodeString()
    println(ds.decodeString("3[a]2[bc]"))
    println(ds.decodeString("3[a2[c]]"))
    println(ds.decodeString("3[abc]3[cd]ef"))

}
