package design;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * LeetCode 146题
 */
public class LRUCache {

    //HashMap的key为数据的key，value为数据
    private HashMap<Integer, Integer> mMap = new HashMap<>();
    //距今使用时间最长的在队头
    private LinkedList<Integer> queue = new LinkedList<>();

    //缓存容量
    private int mCapacity;

    /**
     * 设计和实现一个  LRU (最近最少使用) 缓存机制。
     *
     * 该算法赋予每个页面一个访问字段，用来记录一个页面自上次被访问以来所经历的时间 t，
     * 当须淘汰一个页面时，选择现有页面中其 t 值最大的，即最近最久未使用的页面予以淘汰。
     */
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }

    public LRUCache(int capacity) {
        mCapacity = capacity;
    }

    public int get(int key) {
        if (mMap.containsKey(key)) {
            //更新使用情况
            queue.remove((Object)key);
            queue.addLast(key);

            //返回数据
            return mMap.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        //如果key存在，更新value
        if (mMap.containsKey(key)) {
            mMap.put(key, value);
            //更新使用情况
            queue.remove((Object)key);
            queue.addLast(key);

            return;
        }
        //如果key不存在，先判断缓存容量是否到达上限
        if (mMap.size() == mCapacity) {
            //删除自上次访问以来所经历的时间最长的数据
            int removeKey = queue.remove();
            mMap.remove(removeKey);
        }
        //添加数据
        mMap.put(key, value);
        //更新使用情况
        queue.addLast(key);
    }

    /**
     * 优化：可以使用双向链表
     */
}
