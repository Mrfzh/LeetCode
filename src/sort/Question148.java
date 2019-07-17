package sort;

public class Question148 {
    public static void main(String[] args) {
//        int[] values = {-1, 2, 1, 3, 7, 5, 9, 8};
        int[] values = {-55,19,14,5,-3,1,8,5,11,15};
        ListNode head = ListNodeUtil.buildLinkList(values);
        ListNodeUtil.printLinkList(new Question148().sortList(head));
    }

    /**
     * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
//        quickSort(head, null);
//        return head;
        return mergeSort(head);
    }

    /**
     * 对指定的链表部分进行排序
     *
     * 第一次写的快排
     *
     * @param head 该部分链表的头结点
     * @param tail 该部分链表的尾节点
     */
    private void sort(ListNode head, ListNode tail) {
//        System.out.print("curr = ");
//        ListNodeUtil.printLinkList(head);

        if (head == tail) {
            return;
        }
        if (head.next != null && head.next == tail) {
            if (head.val > tail.val) {
                swap(head, tail);
            }
            return;
        }

        ListNode frontTail = null;         //前部分的尾节点
        ListNode frontTailLast = null;     //前部分的尾节点的前一节点
        ListNode behindHead = null;        //后部分的头结点
        ListNode behindTail = null;        //后部分的尾节点
        ListNode temp = head.next;  //当前遍历的节点
        int standard = head.val;    //基准
        while (temp != tail) {
            //当前节点值比基准小
            if (temp.val < standard) {
                if (behindHead == null) {
                    //此时没有比基准大的节点，不用交换
                    //更新前半部分的指针
                    frontTailLast = (frontTailLast == null)? head : frontTailLast.next;
                    frontTail = temp;
                } else {
                    //和后半部分的头结点交换
                    swap(temp, behindHead);
                    //更新相应指针
                    frontTailLast = (frontTailLast == null)? head : frontTailLast.next;
                    frontTail = behindHead;
                    behindHead = behindHead.next;
                    behindTail = behindTail.next;
                }
            }
            //当前节点值比基准大
            else {
                //不用变，改变相应指针
                if (behindHead == null) {
                    behindHead = temp;
                }
                behindTail = temp;
            }
            temp = temp.next;
        }
        //还要将尾节点插入到相应位置
        if (temp != null) {
            //当前节点值比基准小
            if (temp.val < standard) {
                if (behindHead == null) {
                    //此时没有比基准大的节点，不用交换
                    //更新前半部分的指针
                    frontTailLast = (frontTailLast == null)? head : frontTailLast.next;
                    frontTail = temp;
                } else {
                    //和后半部分的头结点交换
                    swap(temp, behindHead);
                    //更新相应指针
                    frontTailLast = (frontTailLast == null)? head : frontTailLast.next;
                    frontTail = behindHead;
                    behindHead = behindHead.next;
                    behindTail = behindTail.next;
                }
            }
            //当前节点值比基准大
            else {
                //不用变，改变相应指针
                if (behindHead == null) {
                    behindHead = temp;
                }
                behindTail = temp;
            }
        }

        if (frontTail == null) {
            //说明后面的节点全比基准大
            sort(head.next, tail);
            return;
        }
        //将头结点和前部分的尾节点交换
        swap(head, frontTail);

        //递归
        sort(head, frontTailLast);
        sort(behindHead, behindTail);
    }

    /**
     * 交换两节点的值
     */
    private void swap(ListNode L1, ListNode L2) {
        int temp = L1.val;
        L1.val = L2.val;
        L2.val = temp;
    }

    /**
     * 更简洁的快排实现
     * 从head节点开始，到tail的前一个结点
     */
    private void quickSort(ListNode head, ListNode tail) {
        if (head == tail || head.next == tail) {
            return;
        }

        int pivot = head.val;       //基准值
        ListNode center = head;
        ListNode curr = head.next;
        while (curr != tail) {
            if (curr.val < pivot) {
                center = center.next;
                swap(center, curr);
            }
            curr = curr.next;
        }

        //最终在center左边的是比基准值小的
        swap(head, center);

        quickSort(head, center);
        quickSort(center.next, tail);
    }

    /**
     * 归并排序
     */
    private ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //先利用快慢指针找到中点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode right = mergeSort(slow.next);  //排好右半部分
        slow.next = null;
        ListNode left = mergeSort(head);        //排好左半部分

        return merge(left, right);
    }

    /**
     * 合并两个已排好序的链表
     */
    private ListNode merge(ListNode L1, ListNode L2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (L1 != null && L2 != null) {
            if (L1.val < L2.val) {
                curr.next = L1;
                curr = L1;
                L1 = L1.next;
            } else {
                curr.next = L2;
                curr = L2;
                L2 = L2.next;
            }
        }
        if (L1 != null) {
            curr.next = L1;
        } else {
            curr.next = L2;
        }

        return dummy.next;
    }
}
