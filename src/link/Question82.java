package link;

public class Question82 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        boolean isDeleteValValid = false;
        int deleteVal = 0;

        ListNode currNode = head.next;
        ListNode lastNode = head;
        ListNode llastNode = null;

        while (currNode != null) {
            if (lastNode != null && lastNode.val == currNode.val) {
                //删除该节点和上一节点，并记录下删除元素
                if (lastNode == head) {
                    head = currNode.next;
                    lastNode = llastNode = null;
                } else {
                    llastNode.next = currNode.next;
                    lastNode = llastNode;
                    llastNode = null;
                }
                isDeleteValValid = true;
                deleteVal = currNode.val;
            } else if (isDeleteValValid && deleteVal == currNode.val) {
                //删除当前节点
                if (currNode == head) {
                    head = currNode.next;
                } else {
                    lastNode.next = currNode.next;
                }
            } else {
                //更新节点
                llastNode = lastNode;
                lastNode = currNode;
            }

            currNode = currNode.next;
        }

        return head;
    }
}
