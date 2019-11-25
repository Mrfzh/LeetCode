package link;

public class Question725 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
     *
     * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
     * 这k个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
     *
     * 返回一个符合上述规则的链表的列表。
     * 举例： 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
     *
     * 提示:
     * root 的长度范围： [0, 1000].
     * 输入的每个节点的大小范围：[0, 999].
     * k 的取值范围： [1, 50].
     *
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null) {
            return res;
        }
        if (k == 1) {
            res[0] = root;
            return res;
        }

        // 求出链表节点个数
        int n = 0;
        ListNode temp = root;
        while (temp != null) {
            n++;
            temp = temp.next;
        }
        // 分成 k 组，nums[a] 表示第 a 组的个数
        int[] nums = new int[k];
        int a = n / k;
        int b = n % k;
        for (int i = 0; i < k; i++) {
            nums[i] += a;
        }
        for (int i = 0; i < b; i++) {
            nums[i] += 1;
        }
        // 真正开始分组
        ListNode currHead = root;   // 保存当前头结点
        for (int i = 0; i < k; i++) {
            int num = nums[i];  // 当前组的节点数
            if (currHead == null || num == 0) {
                break;
            }
            res[i] = currHead;
            for (int j = 0; j < num; j++) {
                ListNode t = currHead;
                currHead = currHead.next;
                if (j == num - 1) {
                    t.next = null;
                }
            }
        }

        return res;
    }
}
