package string;

import base.BinaryStdIn;
import base.BinaryStdOut;

/**
 * LZW压缩算法
 */
public class LZW {
    private static final int R = 256;   // 字母表
    private static final int L = 4096;  // 编码总数
    private static final int W = 12;    // 编码宽度

    public static void compress() {
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();  // 三向单词查找树

        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R + 1;               // R为EOF

        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);   // 找到匹配的最长前缀
            BinaryStdOut.write(st.get(s), W);   //输出12位编码.
            int t = s.length();
            if (t < input.length() && code < L)
                st.put(input.substring(0, t + 1), code++);  // 添加前缀+前瞻进查找树
            input = input.substring(t);
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expand() {
        String[] st = new String[L];
        int i;
        for (i = 0; i < R; i++)     // 初始化编译表
            st[i] = "" + (char) i;
        st[i++] = " ";              // EOF

        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        while (true) {
            BinaryStdOut.write(val);    // 输出当前字符串
            codeword = BinaryStdIn.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];    // 获取下一编码
            if (i == codeword)          // 前瞻字符不可用的情况 --- 如AB?,新添了AB?(83)的条目.然后马上遇到83.ABAB?+ = ABABAB? =
                s = val + val.charAt(0);    // 根据上一个字符串首字母得到编码的字符串
            if (i < L)
                st[i++] = val + s.charAt(0);    // 为编译表添加新条目
            val = s;        //更新当前编码
        }
        BinaryStdOut.close();
    }
}
