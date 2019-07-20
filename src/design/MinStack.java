package design;

/**
 * LeetCode 第 155 题
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 */
public class MinStack {

    private ListNode head;  // 链表头指针，使用头插法，最后加入的排前面
    private int min = Integer.MAX_VALUE;    // 记录当前最小元素

    public MinStack() {

    }

    public void push(int x) {
        ListNode node = new ListNode(x);
        node.next = head;
        head = node;
        // 更新最小元素
        if (x < min) {
            min = x;
        }
    }

    public void pop() {
        if (head != null) {
            int deleteVal = head.val;
            head = head.next;
            if (deleteVal == min) {
                // 更新最小元素
                min = Integer.MAX_VALUE;
                ListNode temp = head;
                while (temp != null) {
                    if (temp.val < min) {
                        min = temp.val;
                    }
                    temp = temp.next;
                }
            }
        }
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return min;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


