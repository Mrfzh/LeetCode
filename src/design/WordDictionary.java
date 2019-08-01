package design;

/**
 * LeetCode 211 题
 *
 * 设计一个支持以下两种操作的数据结构：
 * void addWord(word)
 * bool search(word)
 *
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。
 *  . 可以表示任何一个字母。
 *
 * Trie 树 + 递归
 *
 */
public class WordDictionary {

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("dad");
        wd.addWord("mad");
        System.out.println(wd.search("pad"));
        System.out.println(wd.search("bad"));
        System.out.println(wd.search(".ad"));
        System.out.println(wd.search("b.."));
    }

    /**
     * Trie 树
     */
    class TrieNode {
        TrieNode[] link;
        boolean isEnd;

        public TrieNode() {
            link = new TrieNode[26];
        }

        public TrieNode add(char ch) {
            if (link[ch - 'a'] == null) {
                link[ch - 'a'] = new TrieNode();
            }
            return link[ch - 'a'];
        }

        public TrieNode get(char ch) {
            return link[ch - 'a'];
        }

        public boolean contains(char ch) {
            return link[ch - 'a'] != null;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            node = node.add(curr);
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return find(word, 0, root);
    }

    /**
     * 查找是否存在 word
     *
     * @param word 查找单词
     * @param start 开始查找的位置
     * @param root 上一位置的节点（或根节点）
     * @return
     */
    private boolean find(String word, int start, TrieNode root) {
        if (start == word.length()) {
            return root.isEnd;
        }
        char curr = word.charAt(start);
        if (curr == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.contains((char) ('a' + i)) &&
                        find(word, start + 1, root.get((char) ('a' + i)))) {
                    return true;
                }
            }
        } else {
            return root.contains(curr) &&
                    find(word, start + 1, root.get(curr));
        }

        return false;
    }
}
