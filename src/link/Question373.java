package link;

import java.util.ArrayList;
import java.util.List;

public class Question373 {
    public static void main(String[] args) {
        int[] nums1 = {1,1,2};
        int[] nums2 = {1,2,3};
        System.out.println(new Question373().kSmallestPairs(nums1, nums2, 9));
    }

    private int[] nums1;
    private int[] nums2;

    /**
     * 给定两个以升序排列的整形数组 nums1 和 nums2, 以及一个整数 k。
     *
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2。
     * 找到和最小的 k 对数字 (u1,v1), (u2,v2) ... (uk,vk)。
     *
     * 定义一个单链表存储结构
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 判空
        if (nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        this.nums1 = nums1;
        this.nums2 = nums2;
        Node head = initLinkList();
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        while (head != null) {
            add(res, head);
            count++;
            if (count == k) {
                // 已收集完毕
                break;
            }
            if (head.index2 == nums2.length-1) {
                // 舍弃当前节点
                head = head.next;
                continue;
            }

            // 更新头结点
            head.index2++;
            // 下面两种情况不用改变链表位置
            if (head.next == null) {
                // 没有下一节点
                continue;
            }
            if (compare(head, head.next)) {
                // 新节点小于下一节点
                continue;
            }
            // 将新节点插入到合适位置
            Node newNode = head;    // 新节点
            head = head.next;       // 新头结点
            Node temp = head;
            while (true) {
                // 如果没有下一节点，或者插入节点小于下一节点，则将节点插入到当前节点后面
                if (temp.next == null || compare(newNode, temp.next)) {
                    insert(temp, newNode);
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }

        return res;
    }

    class Node {
        int index1;     // nums1 数组索引
        int index2;     // nums2 数组索引
        Node next;      // 下一节点

        public Node(int index1, int index2, Node next) {
            this.index1 = index1;
            this.index2 = index2;
            this.next = next;
        }
    }

    /**
     * 初始化链表
     */
    private Node initLinkList() {
        Node last = null;
        for (int i = nums1.length-1; i >= 0; i--) {
            last = new Node(i, 0, last);
        }

        return last;
    }

    /**
     * 向结果集添加新的值
     */
    private void add(List<List<Integer>> res, Node curr) {
        List<Integer> newPair = new ArrayList<>();
        newPair.add(nums1[curr.index1]);
        newPair.add(nums2[curr.index2]);
        res.add(newPair);
    }

    /**
     * 在节点 n1 后面插入节点 n2
     */
    private void insert(Node n1, Node n2) {
        n2.next = n1.next;
        n1.next = n2;
    }

    /**
     * 如果节点 n1 小于节点 n2，则返回 true
     */
    private boolean compare(Node n1, Node n2) {
        int num1 = nums1[n1.index1] + nums2[n1.index2];
        int num2 = nums1[n2.index1] + nums2[n2.index2];
        if (num1 < num2) {
            return true;
        } else if (num1 > num2) {
            return false;
        } else {
            return n1.index1+n1.index2 < n2.index1+n2.index2;
        }
    }
}
