package No1

/**
 *
 *
 * Example:
 * var li = `1~20`.ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class `1~20`.ListNode(var `val`: Int) {
 *     var next: `1~20`.ListNode? = null
 * }
 */
class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null
        var carry = 0
        var node1 = l1
        var node2 = l2
        val result = ListNode(0)
        var pre = result
        while (node1 != null || node2 != null || carry > 0) {
            val sum = ((node1?.`val` ?: 0) + (node2?.`val` ?: 0) + carry)
            carry = sum / 10
            node1 = node1?.next
            node2 = node2?.next
            val next = ListNode(sum % 10)
            pre.next = next
            pre = next
        }
        return result.next
    }
}

fun main() {
//    [2,4,3]
//    [5,6,4] 7->0->8 807
    var l1 = ListNode(2).apply {
        next = ListNode(4).apply {
            next = ListNode(3)
        }
    }
    var l2 = ListNode(5).apply {
        next = ListNode(6).apply {
            next = ListNode((4))
        }
    }
    test(l1, l2)

//    l1=[0,1]，l2=[0,1,2] 0->2->2 220
    l1 = ListNode(0).apply {
        next = ListNode(1)
    }
    l2 = ListNode(0).apply {
        next = ListNode(1).apply {
            next = ListNode(2)
        }
    }
    test(l1, l2)

//    l1=[]，l2=[0,1]  10  01
    l1 = ListNode(0)
    l2 = ListNode(0).apply {
        next = ListNode(1)
    }
    test(l1, l2)

//    l1=[9,9]，l2=[1]  0->0->1  100
    l1 = ListNode(9).apply {
        next = ListNode(9)
    }
    l2 = ListNode(1)
    test(l1, l2)
}

private fun test(l1: ListNode, l2: ListNode) {
    val s = Solution()
    val node = s.addTwoNumbers(l1, l2)
    print(node?.`val`)
    print(node?.next?.`val`)
    print(node?.next?.next?.`val`)
    println()
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
