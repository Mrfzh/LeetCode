package design;

import java.util.Random;

/**
 * LeetCode 第 382 题
 *
 * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 *
 * 进阶:
 * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 *
 * 蓄水池抽样法
 *
 */
public class Solution {

    private ListNode head;

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int res = head.val;     // 头结点进入蓄水池
        ListNode temp = head.next;
        int index = 1;  // 记录当前节点（temp）的索引
        Random random = new Random();
        while (temp != null) {
            // 获得 [0, index] 中的随机数
            int rand = random.nextInt(index+1);
            // 当获得的随机数为 0 时，替换掉蓄水池中的节点
            if (rand == 0) {
                res = temp.val;
            }
            // 下一节点
            index++;
            temp = temp.next;
        }

        return res;
    }
}
