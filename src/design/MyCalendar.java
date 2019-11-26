package design;

/**
 * 729 我的日程安排表 I
 *
 * 实现一个 design.MyCalendar 类来存放你的日程安排。
 * 如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。
 *
 * design.MyCalendar 有一个 book(int start, int end)方法。
 * 它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，
 * 即 [start, end), 实数 x 的范围为，  start <= x < end。
 *
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。
 * 每次调用 design.MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，
 * 返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 */
public class MyCalendar {

    Calender head;  // 链表记录各日程，按照时间先后排序

    public MyCalendar() {
        head = null;
    }

    public boolean book(int start, int end) {
        // 如果日程没有冲突，就把新日程插入到链表合适位置
        Calender newCal = new Calender(start, end);
        // 成为头结点
        if (head == null) {
            head = newCal;
            return true;
        } else if (head.start >= end) {
            newCal.next = head;
            head = newCal;
            return true;
        }
        Calender temp = head;
        while (true) {
            if (temp.next == null) {
                if (temp.end <= start) {
                    // 成为尾节点
                    temp.next = newCal;
                    return true;
                } else {
                    return false;
                }
            }
            Calender next = temp.next;
            if (temp.end <= start && next.start >= end) {
                // 插入到 temp 和 next 中间
                temp.next = newCal;
                newCal.next = next;
                return true;
            }
            if (temp.end >= end) {
                // 提取结束，再遍历下去也没有意义
                return false;
            }
            temp = temp.next;
        }
    }

    class Calender {
        int start;
        int end;
        Calender next;

        public Calender(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
