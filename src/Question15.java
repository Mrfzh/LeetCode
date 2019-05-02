import java.util.*;

public class Question15 {
    public static void main(String[] args) {
        int [] nums = {-1, -1, -1, -1, 1, 2};
        System.out.println(threeSum2(nums));
    }

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。注意：答案中不可以包含重复的三元组。
     *
     * 该方法超时
     *
     * @param nums 给定的int数组
     * @return
     */
    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;
        if (n < 3) {
            return result;
        }

        //利用HashMap分别把正负数存起来，并判断有没有0
        HashMap<Integer, Integer> posHashMap = new HashMap<>();
        HashMap<Integer, Integer> negHashMap = new HashMap<>();
        boolean hasZero = false;    //判断是否有0
        int zeroNum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                if (negHashMap.containsKey(nums[i])) {
                    int pre = negHashMap.get(nums[i]);
                    negHashMap.put(nums[i], ++pre);
                } else {
                    negHashMap.put(nums[i], 1);
                }
            } else if (nums[i] > 0) {
                if (posHashMap.containsKey(nums[i])) {
                    int pre = posHashMap.get(nums[i]);
                    posHashMap.put(nums[i], ++pre);
                } else {
                    posHashMap.put(nums[i], 1);
                }
            } else {
                hasZero = true;
                zeroNum++;
            }
        }

        //开始处理
        if (hasZero) {
            if (zeroNum >= 3) {     //添加全0组合
                List<Integer> zeroList = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    zeroList.add(0);
                }
                result.add(zeroList);
            }
            //一正一负
            for (Object op : posHashMap.keySet()) {
                int keyPos = (int) op;
                if (keyPos != 0) {  //0不用比较
                    for (Object on : negHashMap.keySet()) {
                        int keyNeg = (int) on;
                        if (keyNeg == -keyPos) {
                            List<Integer> list = new ArrayList<>();
                            list.add(keyNeg);
                            list.add(keyPos);
                            list.add(0);
                            result.add(list);
                        }
                    }
                }
            }
        }

        //两正一负
        HashMap<Integer, Integer> posHashMap2 = new HashMap<>();    //先备份posHashMap
        for (Object op : posHashMap.keySet()) {
            int keyPos = (int) op;
            posHashMap2.put(keyPos, posHashMap.get(keyPos));
        }

        boolean isFirstValue = true;    //判断是否为第一个元素
        int firstValue = 0;     //保存第一个正数
        int secondValue = 0;    //第二个正数
        while (posHashMap2.size() >= 1) {
            for (Object op : posHashMap2.keySet()) {
                int keyPos = (int) op;
                if (isFirstValue && posHashMap2.get(keyPos) > 1) {  //如果是第一个元素并且有几个相同的数
                    firstValue = keyPos;
                    secondValue = keyPos;
                    isFirstValue = false;
                }
                if (isFirstValue && posHashMap2.get(keyPos) == 1) {  //如果是第一个元素并且该数只有一个
                    firstValue = keyPos;
                    isFirstValue = false;
                    continue;   //退出此次循环
                }
                if (!isFirstValue) {    //如果不是第一个元素
                    secondValue = keyPos;
                }

                //开始跟前两个正数比较
                for (Object on : negHashMap.keySet()) {
                    int keyNeg = (int) on;
                    if (keyNeg == -(firstValue + secondValue)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(keyNeg);
                        list.add(firstValue);
                        list.add(secondValue);
                        result.add(list);
                    }
                }
            }
            posHashMap2.remove(firstValue);     //删除第一个元素
            isFirstValue = true;
        }

        //两负一正
        while (negHashMap.size() >= 1) {

            for (Object op : negHashMap.keySet()) {
                int negPos = (int) op;
                if (isFirstValue && negHashMap.get(negPos) > 1) {  //如果是第一个元素并且有几个相同的数
                    firstValue = negPos;
                    secondValue = negPos;
                    isFirstValue = false;
                }
                if (isFirstValue && negHashMap.get(negPos) == 1) {  //如果是第一个元素并且该数只有一个
                    firstValue = negPos;
                    isFirstValue = false;
                    continue;   //退出此次循环
                }
                if (!isFirstValue) {    //如果不是第一个元素
                    secondValue = negPos;
                }

                //开始跟前两个正数比较
                for (Object on : posHashMap.keySet()) {
                    int keyNeg = (int) on;
                    if (keyNeg == -(firstValue + secondValue)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(keyNeg);
                        list.add(firstValue);
                        list.add(secondValue);
                        result.add(list);
                    }
                }
            }
            negHashMap.remove(firstValue);     //删除第一个元素
            isFirstValue = true;
        }

        return result;
    }

    /**
     *  该方法可以通过
     *
     * @param nums
     * @return
     */
    private static List<List<Integer>> threeSum2(int[] nums) {
        if(nums == null){
            return null;
        }
        //如果数组长度小于3，返回一个空集合
        if(nums.length < 3){
            return new ArrayList<>();
        }
        //对数组nums进行排序
        Arrays.sort(nums);
        HashSet<List<Integer>> set = new HashSet<>();   //例如HashSet防止放入重复元素
        //让i从数组下标为0开始跑
        for (int i = 0; i < nums.length; i++) {
            //j从i的后一个数开始
            int j = i + 1;
            //k从数组最后一个数（最大的数）往前跑
            int k = nums.length - 1;
            while(j < k){
                if(nums[i] + nums[j] + nums[k] == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    set.add(list);
                    //当有重复的数字出现时，j往后跑，k往前跑，防止结果有重复
                    while(j < k && nums[j] == nums[j + 1]){
                        j++;
                    }
                    while(j < k && nums[k] == nums[k - 1]){
                        k--;
                    }
                    j++;
                    k--;
                }else if(nums[i] + nums[j] + nums[k] < 0){
                    j++;
                }else{
                    k--;
                }
            }
        }
        return new ArrayList<>(set);
    }
}
