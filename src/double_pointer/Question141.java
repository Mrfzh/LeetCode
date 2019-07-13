package double_pointer;

public class Question141 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个链表，判断链表中是否有环。
     *
     * 双指针法
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        //slow和fast指针一开始都执行头结点，之后slow指针每次走一步
        //fast指针每次走两步，如果最终slow指针和fast指针能再次相遇
        //说明链表中有环

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
