package link;

public class Question92 {
    public static void main(String[] args) {

    }

    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
     * 说明: 1 ≤ m ≤ n ≤ 链表长度。
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) {
            return head;
        }

        ListNode last = null;   //存储位置为m-1的节点
        ListNode next = null;   //存储位置为n+1的节点
        ListNode reverseHead = null;    //反转部分链表的头节点
        ListNode reverseTail = null;    //反转部分链表的尾节点
        int currIndex = 0;       //当前遍历节点的位置
        ListNode curr = head;   //当前遍历节点
        while (curr != null) {
            currIndex++;
            ListNode temp = curr.next;
            if (currIndex == m-1) {
                last = curr;
            } else if (m <= currIndex && currIndex <= n) {
                if (reverseTail == null) {
                    reverseTail = curr;
                }
                curr.next = reverseHead;
                reverseHead = curr;
            } else if (currIndex == n+1) {
                next = curr;
            } else if (currIndex > n+1) {
                break;
            }
            curr = temp;
        }
        if (last == null) {
            //这是从第一个节点开始反转
            head = reverseHead;
        } else {
            last.next = reverseHead;
        }
        reverseTail.next = next;
        return head;
    }
}
