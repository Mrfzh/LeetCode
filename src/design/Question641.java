package design;

/**
 * 设计实现双端队列。
 * 你的实现需要支持以下操作：
 * MyCircularDeque(k)：构造函数,双端队列的大小为k。
 * insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true。
 * insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true。
 * deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true。
 * deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true。
 * getFront()：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * getRear()：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1。
 * isEmpty()：检查双端队列是否为空。
 * isFull()：检查双端队列是否满了。处。
 */
public class Question641 {

    public static void main(String[] args) {
        Question641 q = new Question641(3);
        System.out.println(q.insertLast(1));
        System.out.println(q.insertLast(2));
        System.out.println(q.insertFront(3));
    }

    private int[] values;
    private int head = -1;
    private int tail = -1;
    private int n;

    public Question641(int k) {
        values = new int[k];
        n = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = tail = 0;
            values[head] = value;
        } else {
            int index = (head - 1 + n) % n;
            values[index] = value;
            head = index;
        }
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        if (isEmpty()) {
            head = tail = 0;
            values[head] = value;
        } else {
            int index = (tail + 1) % n;
            values[index] = value;
            tail = index;
        }
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = tail = -1;
        } else {
            head = (head + 1) % n;
        }
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        if (head == tail) {
            head = tail = -1;
        } else {
            tail = (tail - 1 + n) % n;
        }
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty()? -1 : values[head];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty()? -1 : values[tail];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return head == -1;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return (tail + 1) % n == head;
    }
}
