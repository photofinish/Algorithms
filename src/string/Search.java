package string;

/**
 * 暴力子字符串查找
 */
public class Search {

    public static int search(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != pat.charAt(j)) break;
            if (j == M)
                return i;
        }
        return N;   // 无匹配
    }

    //显式回退
    public static int search2(String pat, String txt) {
        int j,M = pat.length();
        int i,N = txt.length();

        for (i = 0, j = 0; i < N && j < M;i++) {
            if (pat.charAt(j) == txt.charAt(i)) j++;
            else { j = 0; i -= j; }
        }

        if (j == M) return i - M;
        else return N;
    }
}
