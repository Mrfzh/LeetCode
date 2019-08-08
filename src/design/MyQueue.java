package design;

import java.util.Stack;

/**
 * LeetCode 232 题
 *
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 */
//public class design.MyQueue {
//
//    public static void main(String[] args) {
//        design.MyQueue myQueue = new design.MyQueue();
//        myQueue.push(1);
//        myQueue.push(2);
//        System.out.println(myQueue.peek());
//        System.out.println(myQueue.pop());
//        System.out.println(myQueue.empty());
//    }
//
//    // 利用两个栈实现队列
//    private Stack<Integer> stackA;
//    private Stack<Integer> stackB;
//    private static final int STACK_A = 0;
//    private static final int STACK_B = 1;
//    // 用于入队的栈
//    private int pushStack = STACK_A;
//    // 用于出队的栈
//    private int popStack = STACK_A;
//    // 出队是是否需要倒置元素（借助另一个栈）
//    private boolean needReverse = true;
//
//    public design.MyQueue() {
//        stackA = new Stack<>();
//        stackB = new Stack<>();
//    }
//
//    public void push(int x) {
//        if (pushStack == STACK_A) {
//            stackA.push(x);
//        } else {
//            stackB.push(x);
//        }
//    }
//
//    public int pop() {
//        return getValue(true);
//    }
//
//    public int peek() {
//        return getValue(false);
//    }
//
//    private int getValue(boolean delete) {
//        if (needReverse) {
//            if (popStack == STACK_A) {
//                if (stackA.size() == 1) {
//                    if (delete) {
//                        pushStack = STACK_A;
//                        return stackA.pop();
//                    } else {
//                        return stackA.peek();
//                    }
//                } else {
//                    // 借助另一个栈
//                    while (stackA.size() != 1) {
//                        stackB.push(stackA.pop());
//                    }
//                    needReverse = false;
//                    pushStack = STACK_A;
//                    popStack = STACK_B;
//                    if (delete) {
//                        return stackA.pop();
//                    } else {
//                        int res = stackA.pop();
//                        stackB.push(res);
//                        return res;
//                    }
//                }
//            } else {
//                if (stackB.size() == 1) {
//                    if (delete) {
//                        pushStack = STACK_A;
//                        popStack = STACK_A;
//                        return stackB.pop();
//                    } else {
//                        return stackB.peek();
//                    }
//                } else {
//                    // 借助另一个栈
//                    while (stackB.size() != 1) {
//                        stackA.push(stackB.pop());
//                    }
//                    needReverse = false;
//                    pushStack = STACK_B;
//                    popStack = STACK_A;
//                    if (delete) {
//                        return stackB.pop();
//                    } else {
//                        int res = stackB.pop();
//                        stackA.push(res);
//                        return res;
//                    }
//                }
//            }
//        } else {
//            // 不需要反转
//            if (popStack == STACK_A) {
//                if (stackA.size() == 1) {
//                    if (delete) {
//                        pushStack = STACK_A;
//                        needReverse = true;
//                        return stackA.pop();
//                    } else {
//                        return stackA.peek();
//                    }
//                } else {
//                    if (delete) {
//                        return stackA.pop();
//                    } else {
//                        return stackA.peek();
//                    }
//                }
//            } else {
//                if (stackB.size() == 1) {
//                    if (delete) {
//                        pushStack = STACK_A;
//                        popStack = STACK_A;
//                        needReverse = true;
//                        return stackB.pop();
//                    } else {
//                        return stackB.peek();
//                    }
//                } else {
//                    if (delete) {
//                        return stackB.pop();
//                    } else {
//                        return stackB.peek();
//                    }
//                }
//            }
//        }
//    }
//
//    public boolean empty() {
//        return stackA.isEmpty() && stackB.isEmpty();
//    }
//}

/**
 * 更简洁的写法，一个队列用于出队和入队，另一个队列用于暂存元素
 */
//class design.MyQueue {
//
//    private Stack<Integer> stackA;  // 用于出队与入队
//    private Stack<Integer> stackB;  // 入队时暂存元素
//
//    public design.MyQueue() {
//        stackA = new Stack<>();
//        stackB = new Stack<>();
//    }
//
//    public void push(int x) {
//        if (stackA.isEmpty()) {
//            stackA.push(x);
//            return;
//        }
//        while (!stackA.isEmpty()) {
//            stackB.push(stackA.pop());
//        }
//        stackA.push(x);
//        while (!stackB.isEmpty()) {
//            stackA.push(stackB.pop());
//        }
//    }
//
//    public int pop() {
//        return stackA.pop();
//    }
//
//    public int peek() {
//        return stackA.peek();
//    }
//
//    public boolean empty() {
//        return stackA.isEmpty();
//    }
//}

/**
 * 优化
 */
class MyQueue {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.pop());
        System.out.println(myQueue.empty());
    }

    private Stack<Integer> stackA;  // 用于入队
    private Stack<Integer> stackB;  // 用于出队
    private int front;  // 记录栈 A 中的队头（即栈底元素）

    public MyQueue() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int x) {
        if (stackA.isEmpty()) {
            front = x;
        }
        stackA.push(x);
    }

    public int pop() {
        if (stackB.isEmpty()) {
            // 将栈 A 中的元素倒置存入栈 B
            while (!stackA.isEmpty()) {
                stackB.push(stackA.pop());
            }
        }
        return stackB.pop();
    }

    public int peek() {
        if (stackB.isEmpty()) {
            return front;
        } else {
            return stackB.peek();
        }
    }

    public boolean empty() {
        return stackA.isEmpty() && stackB.isEmpty();
    }
}
