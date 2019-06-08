package link;

public class Question83 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        int lastVal = head.val;
        ListNode temp = head.next;
        ListNode lastNode = head;
        while (temp != null) {
            if (temp.val == lastVal) {
                //删除该节点
                lastNode.next = temp.next;
            } else {
                lastVal = temp.val;
                lastNode = temp;
            }
            temp = temp.next;
        }

        return head;
    }
}
