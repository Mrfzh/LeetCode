package design;

import java.util.LinkedList;

/**
 * LeetCode 225 题
 *
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 *
 */
public class MyStack {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        System.out.println(myStack.pop());
        myStack.push(2);
        System.out.println(myStack.pop());
    }

    // 用两个队列实现栈
    private LinkedList<Integer> queueA;
    private LinkedList<Integer> queueB;

    private static final int QUEUE_A = 0;
    private static final int QUEUE_B = 1;
    // 记录当前入栈和出栈时操作的队列，另一个队列在出栈时存储另一队列的出队元素
    private int workQueue = QUEUE_A;

    public MyStack() {
        queueA = new LinkedList<>();
        queueB = new LinkedList<>();
    }

    public void push(int x) {
        switch (workQueue) {
            case QUEUE_A:
                queueA.add(x);
                break;
            case QUEUE_B:
                queueB.add(x);
                break;
        }
    }

    public int pop() {
        int res = 0;
        switch (workQueue) {
            case QUEUE_A:
                while (queueA.size() > 1) {
                    queueB.add(queueA.remove());
                }
                res = queueA.remove();
                workQueue = QUEUE_B;
                break;
            case QUEUE_B:
                while (queueB.size() > 1) {
                    queueA.add(queueB.remove());
                }
                res = queueB.remove();
                workQueue = QUEUE_A;
                break;
        }
        return res;
    }

    public int top() {
        int res = 0;
        switch (workQueue) {
            case QUEUE_A:
                while (queueA.size() > 1) {
                    queueB.add(queueA.remove());
                }
                res = queueA.remove();
                queueB.add(res);
                workQueue = QUEUE_B;
                break;
            case QUEUE_B:
                while (queueB.size() > 1) {
                    queueA.add(queueB.remove());
                }
                res = queueB.remove();
                queueA.add(res);
                workQueue = QUEUE_A;
                break;
        }
        return res;
    }

    public boolean empty() {
        return queueA.isEmpty() && queueB.isEmpty();
    }
}
