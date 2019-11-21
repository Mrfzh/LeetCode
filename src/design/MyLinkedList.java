package design;

/**
 * 707、设计链表
 *
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。
 * val 是当前节点的值，next 是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。
 * 假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。
 *  如果 index 等于链表的长度，则该节点将附加到链表的末尾。
 *  如果 index 大于链表长度，则不会插入节点。
 *  如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *
 * 提示：
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 *
 */
public class MyLinkedList {

    private Node mHead;
    private Node mTail;
    private int mSize;      // 节点个数

    /** Initialize your data structure here. */
    public MyLinkedList() {
        mHead = null;
        mTail = null;
        mSize = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= mSize) {
            return -1;
        }
        Node temp = mHead;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = mHead;
        if (mHead == null) {
            mHead = mTail = node;
        } else {
            mHead = node;
        }
        mSize++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if (mTail == null) {
            mHead = mTail = node;
        } else {
            mTail.next = node;
            mTail = node;
        }
        mSize++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == mSize) {
            addAtTail(val);
        } else if (index <= 0) {
            addAtHead(val);
        } else if (index < mSize) {
            // 在原链表的第 index-1 个节点和第 index 个节点之间插入新节点
            Node node = new Node(val);
            Node lastNode = null;
            Node nextNode = null;
            Node temp = null;
            // 一般情况
            for (int i = 0; i <= index; i++) {
                if (i == 0) {
                    temp = mHead;
                } else {
                    temp = temp.next;
                }
                if (i == index - 1) {
                    lastNode = temp;
                }
                if (i == index) {
                    nextNode = temp;
                }
            }
            if (lastNode != null) {
                lastNode.next = node;
                node.next = nextNode;
                mSize++;
            }
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= mSize) {
            return;
        }
        if (index == 0) {   // 删除头结点
            if (mSize == 1) {
                mHead = mTail = null;
            } else {
                mHead = mHead.next;
            }
        } else if (index == mSize-1) {  // 删除尾节点
            // 能执行到这里，肯定不只一个节点
            Node temp = mHead;
            for (int i = 1; i <= index - 1; i++) {
                temp = temp.next;
            }
            temp.next = null;
            mTail = temp;
        } else {    // 删除中间节点
            // 找到 index-1 位置的节点
            Node temp = mHead;
            for (int i = 1; i <= index-1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        mSize--;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }
}
