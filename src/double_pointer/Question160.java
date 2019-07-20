package double_pointer;

public class Question160 {
    public static void main(String[] args) {

    }

    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     *
     * 注意：
     * 1. 如果两个链表没有交点，返回 null.
     * 2. 在返回结果后，两个链表仍须保持原有的结构。
     * 3. 可假定整个链表结构中没有循环。
     * 4. 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
     *
     * 双指针法
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        // 计算 LA、LB 的长度
        int la = length(headA);
        int lb = length(headB);

        // 较长的先移动 |la - lb| 步
        ListNode a = headA;
        ListNode b = headB;
        if (la > lb) {
            a = move(headA, la-lb);
        } else if (la < lb){
            b = move(headB, lb-la);
        }

        // 两指针同时移动，若某点重合，说明相交
        while (a != null) {
            if (a == b) {
                return a;
            }
            a = a.next;
            b = b.next;
        }

        return null;
    }

    /**
     * 计算链表长度
     */
    private int length(ListNode head) {
        ListNode temp = head;
        int res = 0;
        while (temp != null) {
            res++;
            temp = temp.next;
        }

        return res;
    }

    /**
     * 链表头指针向前走 n 步
     */
    private ListNode move(ListNode head, int n) {
        ListNode temp = head;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
