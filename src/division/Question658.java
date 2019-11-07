package division;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question658 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println(new Question658().findClosestElements(arr, 4, -1));
    }

    /**
     * 给定一个排序好的数组，两个整数 k 和 x，从数组中找到最靠近 x（两数之差最小）的 k 个数。
     * 返回的结果必须要是按升序排好的。如果有两个数与 x 的差值一样，优先选择数值较小的那个数。
     *
     * 说明:
     * k 的值为正数，且总是小于给定排序数组的长度。
     * 数组不为空，且长度不超过 10^4
     * 数组里的每个元素与 x 的绝对值不超过 10^4
     *
     * 二分法
     *
     * @param arr
     * @param k 数量
     * @param x 最靠近的数
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 先二分找到 x 或最接近 x 的数的位置
        int pos = -1;
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == x) {
                pos = mid;
                break;
            } else if (arr[mid] < x) {
                if (mid == arr.length - 1 || arr[mid + 1] >= x) {
                    pos = mid == arr.length - 1? mid :
                            Math.abs(arr[mid] - x) <= Math.abs(arr[mid + 1] - x)? mid : mid + 1;
                    break;
                } else {
                    start = mid + 1;
                }
            } else {
                if (mid == 0 || arr[mid - 1] <= x) {
                    pos = mid == 0? mid :
                            Math.abs(arr[mid] - x) <= Math.abs(arr[mid - 1] - x)? mid : mid - 1;
                    break;
                } else {
                    end = mid - 1;
                }
            }
        }

        // 根据 pos 向两边扩散，找到 k 个最接近 x 的数
        List<Integer> res = new ArrayList<>();
        res.add(arr[pos]);
        int left = pos - 1;
        int right = pos + 1;
        for (int i = 0; i < k - 1; i++) {
            if (left < 0) {
                res.add(arr[right]);
                right++;
                continue;
            }
            if (right >= arr.length) {
                res.add(arr[left]);
                left--;
                continue;
            }
            int leftDis = Math.abs(arr[left] - x);
            int rightDis = Math.abs(arr[right] - x);
            if (leftDis <= rightDis) {
                res.add(arr[left]);
                left--;
            } else {
                res.add(arr[right]);
                right++;
            }
        }

        Collections.sort(res);
        return res;
    }
}
