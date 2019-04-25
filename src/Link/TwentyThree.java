package Link;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TwentyThree {
    public static void main(String[] args) {

    }

    /**
     * 合并 k 个排序链表，返回合并后的排序链表
     *
     * @param lists 要合并的链表数组
     * @return 合并后的链表头指针
     */
    private static ListNode mergeKLists(ListNode[] lists) {
        //递归实现
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode[] remain = Arrays.copyOfRange(lists, 1, lists.length);
        return mergeTwoLists(lists[0], mergeKLists(remain));
    }

    /**
     * 优化：利用递归和分支相结合的方法
     * @param lists
     * @return
     */
    private static ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        if (lists.length == 2) {
            return mergeTwoLists(lists[0], lists[1]);
        }

        //划分为两个子数组
        int mid = lists.length / 2;
        ListNode[] left = new ListNode[mid];
        for (int i = 0; i < mid; i++) {
            left[i] = lists[i];
        }
        ListNode[] right = new ListNode[lists.length - mid];
        for (int i = 0; i < lists.length - mid; i++) {
            right[i] = lists[i+mid];
        }

        return mergeTwoLists(mergeKLists2(left), mergeKLists2(right));
    }

    /**
     * 合并两个有序链表
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
}
