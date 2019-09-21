package stack;

import java.util.Stack;

public class Question445 {
    public static void main(String[] args) {
//        int[] nums1 = {7,2,4,3};
//        ListNode l1 = ListNodeUtil.buildLinkList(nums1);
//        int[] nums2 = {0};
//        ListNode l2 = ListNodeUtil.buildLinkList(nums2);
//        ListNodeUtil.printLinkList(new stack.Question445().addTwoNumbers(l1, l2));
    }

    /**
     * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。
     * 它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
     *
     * 栈
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 利用两个栈分别存储两链表的节点值
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode temp = l1;
        while (temp != null) {
            stack1.push(temp.val);
            temp = temp.next;
        }
        temp = l2;
        while (temp != null) {
            stack2.push(temp.val);
            temp = temp.next;
        }

        // 从两栈各取一值相加
        boolean hasMore = false;    // 是否有进位
        ListNode head = null;       // 结果链表
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int num1 = stack1.pop();
            int num2 = stack2.pop();
            int sum = hasMore? num1+num2+1 : num1+num2;
            if (sum >= 10) {
                hasMore = true;
                sum -= 10;
            } else {
                hasMore = false;
            }
            // 根据 sum 构造新节点并插入表头
            ListNode newNode = new ListNode(sum);
            newNode.next = head;
            head = newNode;
        }

        // 最后将非空链表的剩余值加入进去
        while (!stack1.isEmpty()) {
            int num = hasMore? stack1.pop() + 1 : stack1.pop();
            if (num >= 10) {
                hasMore = true;
                num -= 10;
            } else {
                hasMore = false;
            }
            // 根据 num 构造新节点并插入表头
            ListNode newNode = new ListNode(num);
            newNode.next = head;
            head = newNode;
        }
        while (!stack2.isEmpty()) {
            int num = hasMore? stack2.pop() + 1 : stack2.pop();
            if (num >= 10) {
                hasMore = true;
                num -= 10;
            } else {
                hasMore = false;
            }
            // 根据 num 构造新节点并插入表头
            ListNode newNode = new ListNode(num);
            newNode.next = head;
            head = newNode;
        }
        // 注意：如果还有进位，在表头再加一个一
        if (hasMore) {
            ListNode newNode = new ListNode(1);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }
}
