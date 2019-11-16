package easy;

import java.util.HashMap;
import java.util.List;

public class Question690 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。
     *
     * 比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。
     * 那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，员工3的数据结构是[3, 5, []]。
     * 注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。
     *
     * 现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
     *
     * 注意:
     * 一个员工最多有一个直系领导，但是可以有多个直系下属
     * 员工数量不超过2000。
     *
     * hash + dfs
     *
     * @param employees
     * @param id
     * @return
     */
    public int getImportance(List<Employee> employees, int id) {
        for (Employee employee : employees) {
            importanceMap.put(employee.id, employee.importance);
            subordinatesMap.put(employee.id, employee.subordinates);
        }

        return cal(id);
    }

    // 记录员工的重要程度，key 为员工 id，value 为重要程度
    HashMap<Integer, Integer> importanceMap = new HashMap<>();
    // 记录员工的直系下属，key 为员工 id，value 为直系下属的 id
    HashMap<Integer, List<Integer>> subordinatesMap = new HashMap<>();

    /**
     * 计算员工 id 和它所有下属的重要度之和
     */
    private int cal(int id) {
        int res = importanceMap.get(id);
        for (int i : subordinatesMap.get(id)) {
            res += cal(i);
        }

        return res;
    }

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    };
}
