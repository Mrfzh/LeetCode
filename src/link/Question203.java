package link;

public class Question203 {
    public static void main(String[] args) {
        int[] values = {1,2,6,3,4,5,6};
        ListNode head = ListNodeUtil.buildLinkList(values);
        ListNodeUtil.printLinkList(head);
        ListNodeUtil.printLinkList(new Question203().removeElements(head, 6));
    }

    /**
     * 删除链表中等于给定值 val 的所有节点。
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode last = null;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val == val) {
                if (last == null) {
                    // 删除的是头结点
                    head = curr.next;
                } else {
                    // 删除非头节点
                    last.next = curr.next;
                }
                curr = curr.next;
            } else {
                // 不用删除节点
                last = curr;
                curr = curr.next;
            }
        }

        return head;
    }
}
