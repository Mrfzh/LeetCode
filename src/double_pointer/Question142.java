package double_pointer;

public class Question142 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     *
     * 双指针法
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        //先寻找是否有环
        ListNode slow = head;
        ListNode fast = head;
        ListNode meet = null;   //记录相遇点（如果有环的话）
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                meet = slow;
                break;
            }
        }
        if (!hasCycle) {
            return null;
        }

        ListNode start = head;
        //一指针从起点开始走，一指针从相遇点开始走，两指针相遇的点就是入环点
        //证明：假设起点距入环点的距离为a，入环点走到相遇点的距离为b，相遇点走到入环点的距离为c
        //易知：2(a + b) = a + b + n(b + c)，其中n为快指针在环中多走的圈数
        //化简后得：a = (n-1)(b+c) + c，由于(n-1)(b+c)表示在环中饶了n-1圈，可看作没走
        //所以最终 a = c，即起点到入环点和相遇点到入环点的距离一样，两指针相遇的点就是入环点
        while (true) {
            if (start == meet) {
                return start;
            }
            start = start.next;
            meet = meet.next;
        }
    }
}
