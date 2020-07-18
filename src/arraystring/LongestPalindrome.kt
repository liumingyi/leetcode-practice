package arraystring


/**
 * 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author Mingyi
 * @date   2020/7/6
 */
class LongestPalindrome {

    /**
     *********************************
     * 1. 暴力解法,找出所有大于等于2的子串，然后判断其是否为回文串 (力扣上直接超时了)
     * 2. 动态规划
     * 3. 中心扩散法
     * 4. Manacher 算法 (了解思想即可)
     *********************************
     */

    /*
     * 暴力解法 O(n^3)
     */
    fun longestPalindrome(s: String): String {
        if (s.isBlank() || s.length == 1) return s
        val arr = s.toCharArray()
        var max = ""
        for (i in 1..arr.size) {
            for (j in 0..(arr.size - i)) {
                val child = arr.sliceArray(j until i + j).joinToString("")
                if (checkIsPalindrome(child)) {
                    if (child.length > max.length) {
                        max = child
                    }
                }
            }
        }
        return if (max.isBlank()) {
            arr[0].toString()
        } else {
            max
        }
    }

    private fun checkIsPalindrome(s: String): Boolean {
        val arr = s.toCharArray()
        var start = 0
        var end = arr.size - 1
        while (start < end) {
            if (arr[start] != arr[end]) return false
            ++start
            --end
        }
        return true
    }

    /*
     *****************************************************************
     * 2.中心扩散法 O(n^2)
     * 枚举回文串的中心位置，向两头扩散。问题：奇数和偶数串的处理。(按组同时处理)
     *****************************************************************
     */
    fun longestPalindrome_2(s: String): String {
        if (s.isBlank() || s.length == 1) return s

        val chars = s.toCharArray()
        var max = 1
        var res = s.substring(0, 1)

        for (i in 0..chars.size - 2) {
            val odd = centerSpread(s, i, i) //奇
            val even = centerSpread(s, i, i + 1) //偶
            val bigger = odd.takeIf { odd.length > even.length } ?: even
            if (bigger.length > max) {
                max = bigger.length
                res = bigger
            }
        }
        return res
    }

    // 中心扩散
    private fun centerSpread(s: String, left: Int, right: Int): String {
        var i = left
        var j = right
        while (i >= 0 && j < s.length) {
            if (s[i] != s[j]) {
                break
            }
            --i
            ++j
        }
        return s.substring(i + 1, j)
    }
}

fun main() {

    /*
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
     */
    val lp = LongestPalindrome()
//    println("Longest: ${lp.longestPalindrome("babad")}") //bab
//    println("Longest: ${lp.longestPalindrome("cbbd")}")//bb
//    println("Longest: ${lp.longestPalindrome("a")}")//a
//    println("Longest: ${lp.longestPalindrome("")}")// ""
//    println("Longest: ${lp.longestPalindrome("ac")}") //a
//    println("Longest: ${lp.longestPalindrome("bb")}")//bb
//    println("Longest: ${lp.longestPalindrome("anugnxshgonmqydttcvmtsoaprxnhpmpovdolbidqiyqubirkvhwppcdyeouvgedccipsvnobrccbndzjdbgxkzdbcjsjjovnhpnbkurxqfupiprpbiwqdnwaqvjbqoaqzkqgdxkfczdkznqxvupdmnyiidqpnbvgjraszbvvztpapxmomnghfaywkzlrupvjpcvascgvstqmvuveiiixjmdofdwyvhgkydrnfuojhzulhobyhtsxmcovwmamjwljioevhafdlpjpmqstguqhrhvsdvinphejfbdvrvabthpyyphyqharjvzriosrdnwmaxtgriivdqlmugtagvsoylqfwhjpmjxcysfujdvcqovxabjdbvyvembfpahvyoybdhweikcgnzrdqlzusgoobysfmlzifwjzlazuepimhbgkrfimmemhayxeqxynewcnynmgyjcwrpqnayvxoebgyjusppfpsfeonfwnbsdonucaipoafavmlrrlplnnbsaghbawooabsjndqnvruuwvllpvvhuepmqtprgktnwxmflmmbifbbsfthbeafseqrgwnwjxkkcqgbucwusjdipxuekanzwimuizqynaxrvicyzjhulqjshtsqswehnozehmbsdmacciflcgsrlyhjukpvosptmsjfteoimtewkrivdllqiotvtrubgkfcacvgqzxjmhmmqlikrtfrurltgtcreafcgisjpvasiwmhcofqkcteudgjoqqmtucnwcocsoiqtfuoazxdayricnmwcg")}")//bb

    println("Longest: ${lp.longestPalindrome_2("babad")}") //bab
    println("Longest: ${lp.longestPalindrome_2("cbbd")}")//bb
    println("Longest: ${lp.longestPalindrome_2("a")}")//a
    println("Longest: ${lp.longestPalindrome_2("")}")// ""
    println("Longest: ${lp.longestPalindrome_2("ac")}") //a
    println("Longest: ${lp.longestPalindrome_2("bb")}")//bb
    println("Longest: ${lp.longestPalindrome_2("anugnxshgonmqydttcvmtsoaprxnhpmpovdolbidqiyqubirkvhwppcdyeouvgedccipsvnobrccbndzjdbgxkzdbcjsjjovnhpnbkurxqfupiprpbiwqdnwaqvjbqoaqzkqgdxkfczdkznqxvupdmnyiidqpnbvgjraszbvvztpapxmomnghfaywkzlrupvjpcvascgvstqmvuveiiixjmdofdwyvhgkydrnfuojhzulhobyhtsxmcovwmamjwljioevhafdlpjpmqstguqhrhvsdvinphejfbdvrvabthpyyphyqharjvzriosrdnwmaxtgriivdqlmugtagvsoylqfwhjpmjxcysfujdvcqovxabjdbvyvembfpahvyoybdhweikcgnzrdqlzusgoobysfmlzifwjzlazuepimhbgkrfimmemhayxeqxynewcnynmgyjcwrpqnayvxoebgyjusppfpsfeonfwnbsdonucaipoafavmlrrlplnnbsaghbawooabsjndqnvruuwvllpvvhuepmqtprgktnwxmflmmbifbbsfthbeafseqrgwnwjxkkcqgbucwusjdipxuekanzwimuizqynaxrvicyzjhulqjshtsqswehnozehmbsdmacciflcgsrlyhjukpvosptmsjfteoimtewkrivdllqiotvtrubgkfcacvgqzxjmhmmqlikrtfrurltgtcreafcgisjpvasiwmhcofqkcteudgjoqqmtucnwcocsoiqtfuoazxdayricnmwcg")}")//bb

}