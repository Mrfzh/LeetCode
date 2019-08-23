package link;

public class Question328 {
    public static void main(String[] args) {
        int[] values = {2,1,3,5,6,4,7,8};
        ListNode head = ListNodeUtil.buildLinkList(values);
        ListNodeUtil.printLinkList(new Question328().oddEvenList(head));
    }

    /**
     * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
     * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
     *
     * 你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
     *
     * 说明:
     * 1. 应当保持奇数节点和偶数节点的相对顺序。
     * 2. 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode head2 = head.next; // 偶数节点的头指针
        ListNode last = head;  // 当前节点的上一节点
        ListNode temp = head2;  // 暂存节点
        ListNode curr = head.next.next; // 当前节点
        int n = 2;  // 节点个数
        while (curr != null) {
            n++;
            last.next = curr;
            last = temp;
            temp = curr;
            curr = curr.next;
        }
        // 根据节点个数，连接两链表
        if (n % 2 == 1) {
            last.next = null;
            temp.next = head2;
        } else {
            last.next = head2;
        }

        return head;
    }
}
