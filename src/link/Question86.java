package link;

public class Question86 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
     * 你应当保留两个分区中每个节点的初始相对位置。
     * 
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode p = null;  //保存插入位置的上一节点
        ListNode last = head;   //当前遍历节点的上一节点
        ListNode curr = head;   //当前遍历节点
        while (curr != null) {
            ListNode next = curr.next;
            if (curr.val < x) {
                if (p == null) { //说明这是第一个小于x的节点
                    if(curr != head) {
                        //该节点成为头结点
                        last.next = curr.next;
                        curr.next = head;
                        head = curr;
                    }
                } else {
                    //这里要判断节点是否要转移
                    if (p != last) {    //要转移
                        last.next = curr.next;
                        curr.next = p.next;
                        p.next = curr;
                    } else {    //不需要转移
                        last = curr;
                    }
                }
                //该节点成为下一插入位置的上一节点
                p = curr;
            } else {
                last = curr;
            }
            curr = next;
        }

        return head;
    }
}
