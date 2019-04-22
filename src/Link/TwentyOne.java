package Link;

public class TwentyOne {
    public static void main(String[] args) {

    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * @param l1 第一个链表的头节点
     * @param l2 第二个链表的头节点
     * @return 合并后链表的头节点
     */
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode curr;  //指向当前最新节点
        if (l1.val < l2.val) {
            curr = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            curr = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode head = new ListNode(0);    //其next域指向新链表的头节点
        head.next = curr;

        while (l1 != null && l2 != null) {  //遍历两个链表
            ListNode temp;
            if (l1.val < l2.val) {
                temp = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                temp = new ListNode(l2.val);
                l2 = l2.next;
            }
            //链表增加新节点
            curr.next = temp;
            curr = curr.next;
        }

        //加上剩余元素
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }

        return head.next;
    }

    /**
     * 递归实现
     */
    private static ListNode mergeTwoLists_recursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists_recursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_recursion(l1, l2.next);
            return l2;
        }
    }

}
