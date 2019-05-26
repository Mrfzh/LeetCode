package link;

import java.util.ArrayList;
import java.util.List;

public class Question19 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     *
     * @param head 给定的链表头结点
     * @param n
     * @return 删除后链表的头结点
     */
    private static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        List<ListNode> listNodes = new ArrayList<>();   //存储每个节点的地址
        listNodes.add(head);
        ListNode listNode = head.next;
        while (listNode != null) {
            listNodes.add(listNode);
            listNode = listNode.next;
        }

        int k = listNodes.size();   //节点总数
        //删除结点操作
        if (k == n) {
            return head.next;
        } else {
            try {
                listNodes.get(k-n-1).next = listNodes.get(k-n+1);
            } catch (Exception e) {
                listNodes.get(k-n-1).next = null;
            }
        }


        return head;
    }

    /**
     * 双指针法：提供两个指针，开始的时候两个指针的next域都指向头结点，第一个指针先向后走n+1个结点
     * 然后两个指针同时走，当第一个指针为null的时候，第二个就指向要删除的结点的前一个结点，
     * 之后将该结点的next域指向要删除结点的下一结点即可。
     */
    private static ListNode removeNthFromEnd_DoublePointer(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        //新建两个指针，两指针的next域都指向头结点
        ListNode temp = new ListNode(0);
        temp.next = head;   //只是temp的next域指向head，temp的next域改变（例如置为null）不影响head
                            //所以最后要返回temp.next而不是temp
        ListNode first = temp;
        ListNode second = temp;

        //第一个指针先向后走n+1个结点
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        //结束循环后第二个指针就指向要删除结点的前一个结点
        //将该结点的next域指向要删除结点的下一结点
        second.next = second.next.next;

        return temp.next;
    }

}
