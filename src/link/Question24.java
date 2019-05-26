package link;

public class Question24 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode l2 = new ListNode(2);
        head.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;
        ListNode l4 = new ListNode(4);
        l3.next = l4;
        ListNode l5 = new ListNode(5);
        l4.next = l5;
        l5.next = null;

        print(swapPairs_recursion(head));
    }

    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * @param head 给定链表的头结点
     * @return 返回交换后的链表头结点
     */
    private static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode currentNode;       //保存当前结点
        ListNode nextNode = head;  //保存下一结点
        ListNode lastNode = null;  //保存上一结点
        ListNode newHead = null;    //保存新链表的头结点
        for (int i = 1; nextNode != null; i++) {
            System.out.println("run, i = " + i);
            currentNode = nextNode;
            if (i % 2 == 1) {   //单数结点
                lastNode = currentNode;
                if (currentNode.next == null) {
                    nextNode = null;
                }
                if (currentNode.next != null && currentNode.next.next == null) {
                    nextNode = currentNode.next;
                    currentNode.next = null;
                }
                if (currentNode.next != null && currentNode.next.next != null
                        && currentNode.next.next.next == null) {
                    nextNode = currentNode.next;
                    currentNode.next = currentNode.next.next;
                }
                if (currentNode.next != null && currentNode.next.next != null
                        && currentNode.next.next.next != null) {
                    nextNode = currentNode.next;
                    currentNode.next = currentNode.next.next.next;
                }
            } else {    //偶数结点
                if (i == 2) {
                    newHead = currentNode;
                }
                nextNode = currentNode.next;
                currentNode.next = lastNode;
            }
        }

        return newHead;
    }

    /**
     * 递归实现
     */
    private static ListNode swapPairs_recursion (ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs_recursion(head.next.next);
        next.next = head;

        return next;
    }

    private static void print(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }
}
