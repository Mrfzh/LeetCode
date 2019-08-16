package design;

import java.util.Iterator;

/**
 * LeetCode 284 题
 *
 * 给定一个迭代器类的接口，接口包含两个方法： next() 和 hasNext()。
 * 设计并实现一个支持 peek() 操作，返回当前迭代的元素
 */
class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;  // 用于辅助迭代元素
    private Integer cache = null;       // 保存下一个元素

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (cache == null) {
            cache = iterator.next();
        }
        return cache;
    }

    @Override
    public Integer next() {
        if (cache == null) {
            return iterator.next();
        }
        Integer next = cache;
        cache = null;
        return next;
    }

    @Override
    public boolean hasNext() {
        return cache != null || iterator.hasNext();
    }
}
