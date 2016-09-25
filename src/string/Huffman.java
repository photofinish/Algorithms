package string;

import base.BinaryStdIn;
import base.BinaryStdOut;
import base.MinPQ;

/**
 * 哈夫曼压缩
 *
 * 压缩:
 * 读取输入
 * 将输入的每个char出现频率制成表格
 * 根据频率构造相应的霍夫曼编码树
 * 构造编译表,将输入中的每个char值和一个比特字符串关联
 * 将单词查找树编码为比特字符串并写入输出流
 * 将单词总数编码为比特字符串写入输出流
 * 使用编译表翻译每个输入字符写入输出流
 *
 * 展开:
 * 读取单词查找树
 * 读取需要解码的字符数量
 * 使用单词查找树将比特流解码
 */
public class Huffman {
    private static int R = 256; // ASCII字母表

    private static class Node implements Comparable<Node> {
        private char ch;    // 需要被编码的字符
        private int freq;
        private final Node left,right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeft() {
            return left == null && right == null;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * 压缩
     */
    public static void compress() {
        String s = BinaryStdIn.readString(); //读取输入
        char[] input = s.toCharArray();
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;   //将输入的每个char出现频率制成表格
        Node root = buildTrie(freq);    //根据频率构造相应的霍夫曼编码树
        String[] st = new String[R];
        buildCode(st, root, "");    //构造编译表,将输入中的每个char值和一个比特字符串关联
        writeTrie(root);    //将单词查找树编码为比特字符串并写入输出流
        BinaryStdOut.write(input.length);   //将单词总数编码为比特字符串写入输出流
        for (int i = 0; i < input.length; i++) {    //使用编译表翻译每个输入字符写入输出流
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '1')
                    BinaryStdOut.write(true);
                else BinaryStdOut.write(false);
            }
            BinaryStdOut.close();
        }
    }

    /**
     * 展开
     */
    public static void expand() {
        Node root = readTrie();     //读取单词查找树
        int N = BinaryStdIn.readInt();  //读取需要解码的字符数量
        for (int i = 0; i < N; i++) {   //使用单词查找树将比特流解码
            Node x = root;
            while (!x.isLeft()) // 循环到叶子结点为止
                if (BinaryStdIn.readBoolean())  // 读取比特为0向左结点移动,为1向右结点移动
                    x = x.right;
                else x = x.left;
            BinaryStdOut.write(x.ch);   //找到对应字符
        }
        BinaryStdOut.close();
    }

    private static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }

    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeft()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<>(R);
        for (char c = 0; c < R; c++)
            if (freq[c] > 0)    // 此字符有使用
                pq.insert(new Node(c, freq[c], null, null));
        while (pq.size() > 1) {
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    /**
     * 前序遍历写入单词查找树
     * @param x
     */
    private static void writeTrie(Node x) {
        if (x.isLeft()) {
            BinaryStdOut.write(true); // 叶子结点前面是1
            BinaryStdOut.write(x.ch);
            return;
        }
        BinaryStdOut.write(false);  // 内部结点前面是0
        writeTrie(x.left);
        writeTrie(x.right);
    }

    /**
     * 从比特流的前序表示重建单词查找树
     * @return
     */
    private static Node readTrie() {
        if (BinaryStdIn.readBoolean())
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        return new Node('\0', 0, readTrie(), readTrie());
    }
}
