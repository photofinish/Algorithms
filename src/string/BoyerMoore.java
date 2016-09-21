package string;

/**
 * BoyerMoore算法：启发式处理不匹配的字符
 */
public class BoyerMoore {
    private int[] right;
    private String pat;

    public BoyerMoore(String pat) {
        this.pat = pat;
        right = new int[256];
        for (int i = 0; i < 256; i++)
            right[i] = -1;

        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    public int search(String txt) {
        int N = txt.length(), M = pat.length();
        int skip;
        for (int i = 0; i < N - M;i += skip) {
            skip = 0;
            for (int j = M - 1; j >= 0; j--) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    skip = j - right[pat.charAt(j)];
                    if (skip <= 0) skip = 1;
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return N;
    }
}
