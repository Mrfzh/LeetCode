package link;

public class Question147 {
    public static void main(String[] args) {
        int[] values = {4, 2, 1, 3};
        ListNode head = ListNodeUtil.buildLinkList(values);
        ListNodeUtil.printLinkList(head);
        ListNodeUtil.printLinkList(new Question147().insertionSortList(head));
    }

    /**
     * 对链表进行插入排序。
     *
     * 插入排序算法：
     * 1. 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
     * 2. 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
     * 3. 重复直到所有输入数据插入完为止。
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode last = head;
        ListNode curr = head.next;
        ListNode next;
        while (curr != null) {
            //存放当前遍历节点的下一节点
            next = curr.next;
            //比较头结点
            if (curr.val < head.val) {
                curr.next = head;
                head = curr;
                last.next = next;
                curr = next;
                continue;
            }
            ListNode tempLast = head;
            ListNode temp = head.next;
            boolean isInsert = false;
            for (; temp != curr; tempLast = temp, temp = temp.next) {
                if (curr.val < temp.val) {
                    //将其插入到tempLast和temp中间
                    curr.next = temp;
                    tempLast.next = curr;
                    last.next = next;
                    curr = next;
                    isInsert = true;
                    break;
                }
            }
            //如果还没有找到位置插入，说明当前位置就是插入的位置
            if (!isInsert) {
                last = curr;
                curr = next;
            }
        }

        return head;
    }
}
