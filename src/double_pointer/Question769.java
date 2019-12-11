package double_pointer;

public class Question769 {
    public static void main(String[] args) {
        int[] arr = {1,0,2,3,4};
        System.out.println(new Question769().maxChunksToSorted(arr));
    }

    /**
     * 数组arr是[0, 1, ..., arr.length - 1]的一种排列，我们将这个数组分割成几个“块”，
     * 并将这些块分别进行排序。之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
     *
     * 我们最多能将数组分成多少块？
     *
     * 双指针
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int res = 0;
        int start = 0;
        int end = -1;
        while (start < arr.length) {
            int pos = arr[start];
            if (pos > end) {
                end = pos;
            }
            if (start == end) {
                res++;
            }
            start++;
        }

        return res;
    }
}
