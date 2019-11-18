package design;

/**
 * 705. 设计哈希集合
 *
 * 不使用任何内建的哈希表库设计一个哈希集合，具体地说，你的设计应该包含以下的功能
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * 注意：
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 *
 */
class MyHashSet {

    Node[] bucket;
    int n = 100000;

    /** Initialize your data structure here. */
    public MyHashSet() {
        bucket = new Node[n];
    }

    public void add(int key) {
        if (contains(key)) {
            return;
        }
        int index = key % n;
        Node head = bucket[index];
        if (head == null) {
            Node node = new Node(key);
            bucket[index] = node;
        } else {
            Node node = new Node(key);
            node.next = head;
            bucket[index] = node;
        }
    }

    public void remove(int key) {
        if (!contains(key)) {
            return;
        }
        int index = key % n;
        Node head = bucket[index];
        if (head.val == key) {
            bucket[index] = head.next;
        } else {
            Node last = head;
            Node temp = head.next;
            while (temp != null) {
                if (temp.val == key) {
                    last.next = temp.next;
                    break;
                }
                last = temp;
                temp = temp.next;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = key % n;
        Node temp = bucket[index];
        if (temp == null) {
            return false;
        } else {
            while (temp != null) {
                if (temp.val == key) {
                    return true;
                }
                temp = temp.next;
            }
            return false;
        }
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
