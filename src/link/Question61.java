package link;

public class Question61 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     *
     * @param head
     * @param k
     * @return
     */
    private static ListNode rotateRight(ListNode head, int k) {
        int n = 0;  //链表节点个数
        ListNode temp = head;
        ListNode end = null;   //最后一个节点
        while (temp != null) {
            n++;
            if (temp.next != null) {
                temp = temp.next;
            } else {
                end = temp;
                break;
            }
        }

        int move = -1;
        if (n != 0) {
            move = k % n;
        }
        if (n <= 1 || move == 0) {
            return head;
        }

        temp = head;
        ListNode newEnd = null;
        ListNode newHead = null;
        for (int i = 0; i <= n - move; i++) {
            if (i == n - move - 1) {
                newEnd = temp;
            } else if (i == n - move) {
                newHead = temp;
            }
            temp = temp.next;
        }

        end.next = head;
        if (newEnd != null) {
            newEnd.next = null;
        }

        return newHead;
    }
}
