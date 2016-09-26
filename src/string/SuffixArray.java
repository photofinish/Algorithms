package string;

import base.Bag;

import java.util.Iterator;

/**
 * 后缀数组(利用JAVA的String类的子字符串)
 */
public class SuffixArray {
    private final String[] suffixes;
    private final int N;

    public SuffixArray(String s) {
        N = s.length();
        suffixes = new String[N];
        for (int i = 0; i < N; i++)
            suffixes[i] = s.substring(i);
        Quick3string.sort(suffixes);    // 三向快排
    }

    public int length() {
        return N;
    }

    public String select(int i) {
        return suffixes[i];
    }

    public int index(int i) {
        return N - suffixes[i].length();    // index = s.substring(index) --> index = N - index
    }

    /*
     * 求两个字符串最长公共前缀的长度
     */
    private static int lcp(String s, String t) {
        int N = Math.min(s.length(), t.length());
        for (int i = 0; i < N; i++)
            if (s.charAt(i) != s.charAt(i)) return i;
        return N;
    }

    public int lcp(int i) {
        return lcp(suffixes[i], suffixes[i - 1]);
    }

    public int rank(String key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(suffixes[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
    }
}

class Example {
    /**
     * 求最长重复字符串
     */
    public static String lrs(String text) {
        int N = text.length();
        SuffixArray sa = new SuffixArray(text);
        String lrs = "";
        for (int i = 0; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length())
                lrs = sa.select(i).substring(0, length);
        }
        return lrs;
    }
    /**
     * 上下文中关键字查找
     */
    public static Iterable<String> kwic(String text, int context, Iterator<String> search) {
        int N = text.length();
        Bag<String> bag = new Bag<>();
        SuffixArray sa = new SuffixArray(text);
        while (search.hasNext()) {
            String q = search.next();
            for (int i = sa.rank(q); i < N && sa.select(i).startsWith(q); i++) {
                int from = Math.max(0, sa.index(i) - context);
                int to = Math.min(N - 1, from + q.length() + 2 * context);
                bag.add(text.substring(from, to));  // 查找关键字及前后context个字符
            }
        }
        return bag;
    }
}