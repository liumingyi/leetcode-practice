package arraystring

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * @author Mingyi
 * @date   2020/6/30
 */
class LongestCommonPrefix {

    /*
     **********************************************
     * 将string看作第二维数组，以第一行为参考量，纵向比较
     * 逐步靠近最大公共前缀
     **********************************************
     */
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        val result = mutableListOf<Char>()
        var index = 0
        while (true) {
            val currentChar = strs[0].getOrNull(index) ?: return result.joinToString("")
            strs.forEach { s ->
                if (index >= s.length) {
                    return result.joinToString("")
                }
                if (currentChar == s[index]) {
                    return@forEach
                } else {
                    return result.joinToString("")
                }
            }
            result.add(currentChar)
            ++index
        }
    }

    /*
     ****************************************************
     * 分治法 , 二分查找
     ****************************************************
     */
}

fun main() {

//    输入: ["flower","flow","flight"]
//    输出: "fl"
    val lcp = LongestCommonPrefix()
//    println(lcp.longestCommonPrefix(arrayOf("flower", "flow", "flight")))
//    println(lcp.longestCommonPrefix(arrayOf("dog", "racecar", "car")))
    println(lcp.longestCommonPrefix(arrayOf("aa", "aa")))
}