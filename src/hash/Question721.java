package hash;

import java.util.*;

public class Question721 {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));

        System.out.println(new Question721().accountsMerge(accounts));
    }

    /**
     * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，
     * 其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该帐户的邮箱地址。
     *
     * 现在，我们想合并这些帐户。如果两个帐户都有一些共同的邮件地址，则两个帐户必定属于同一个人。
     * 请注意，即使两个帐户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
     * 一个人最初可以拥有任意数量的帐户，但其所有帐户都具有相同的名称。
     *
     * 合并帐户后，按以下格式返回帐户：每个帐户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。
     * accounts 本身可以以任意顺序返回。
     *
     * 注意：
     * accounts的长度将在[1，1000]的范围内。
     * accounts[i]的长度将在[1，10]的范围内。
     * accounts[i][j]的长度将在[1，30]的范围内。
     *
     * hash（可以使用并查集优化）
     *
     * @param accounts
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // HashMap 的 key 存储账户名，value 存储邮箱地址（可能有多个同名的人）
        HashMap<String, List<HashSet<String>>> hashMap = new HashMap<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            HashSet<String> mails = new HashSet<>();
            for (int i = 1; i < account.size(); i++) {
                mails.add(account.get(i));
            }
            if (!hashMap.containsKey(name)) {
                List<HashSet<String>> list = new ArrayList<>();
                list.add(mails);
                hashMap.put(name, list);
            } else {
                List<HashSet<String>> list = hashMap.get(name);
                // 查看哪几个 list 含有 name 的邮箱地址
                HashSet<Integer> hashSet = new HashSet<>();
                for (int i = 0; i < list.size(); i++) {
                    HashSet<String> curr = list.get(i);
                    for (int j = 1; j < account.size(); j++) {
                        if (curr.contains(account.get(j))) {
                            hashSet.add(i);
                            break;
                        }
                    }
                }
                if (hashSet.size() != 0) {
                    // 合并几个 Set
                    for (int i : hashSet) {
                        mails.addAll(list.get(i));
                    }
                    for (int i = list.size()-1; i >= 0; i--) {
                        if (hashSet.contains(i)) {
                            list.remove(i);
                        }
                    }
                }
                list.add(mails);
            }
        }

        // 得到结果
        List<List<String>> res = new ArrayList<>();
        for (String name : hashMap.keySet()) {
            List<HashSet<String>> list = hashMap.get(name);
            for (HashSet<String> set : list) {
                List<String> curr = new ArrayList<>(set);
                Collections.sort(curr);
                curr.add(0, name);
                res.add(curr);
            }
        }

        return res;
    }
}
