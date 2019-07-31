package design;

/**
 * LeetCode 208 题
 * 实现一个 design.Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */
public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));// 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app"));// 返回 true
        trie.insert("app");
        System.out.println(trie.search("app") );    // 返回 true
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        // 从根开始，根据字符顺序查找对应链接
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            // 如果不能链接到下一字符，创建新的节点，并将它链接到父节点上
            if (!node.containsKey(currChar)) {
                node.put(currChar, new TrieNode());
            }
            // 根据链接获取到下一节点
            node = node.get(currChar);
        }
        // 标志链接结束
        node.setEnd();
    }

    /**
     * 根据前缀查找，返回最后查找到的 TrieNode
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currChar = prefix.charAt(i);
            if (node.containsKey(currChar)) {
                node = node.get(currChar);
            } else {
                return null;
            }
        }

        return node;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        // 如果可以得到 TrieNode，并且在该 TrieNode 处终止，说明存在该单词
        return node != null && node.isEnd();
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        // 如果可以得到 TrieNode，不管是否在此终止，都说明存在带有该前缀的单词
        return node != null;
    }

    class TrieNode {
        private TrieNode[] links;
        private final int R = 26;   // 限定为小写字母
        private boolean isEnd;

        public TrieNode() {
            this.links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
