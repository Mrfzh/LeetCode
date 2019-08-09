package link;

public class Question234 {
    public static void main(String[] args) {
        int[] values = {1,2,3,2,4,2,3,2,1};
        ListNode head = ListNodeUtil.buildLinkList(values);
        System.out.println(new Question234().isPalindrome(head));
    }

    /**
     * 请判断一个链表是否为回文链表。
     *
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        int n = 0;
        // 先计算节点个数
        ListNode temp = head;
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        // 反转前半部分的链表
        ListNode left = head;
        ListNode next = head.next;
        left.next = null;
        for (int i = 1; i < n/2; i++) {
            temp = next.next;
            next.next = left;
            left = next;
            next = temp;
        }
        ListNode right;
        if (n % 2 == 1) {
            right = next.next;
        } else {
            right = next;
        }

        // 比较两半部分的链表，如果相同则说明是回文链表
        while (left != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        return true;
    }

    /**
     * 优化：使用快慢指针，然后逆转后半部分的链表
     */
}
