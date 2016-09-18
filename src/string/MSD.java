package string;

/**
 *  字符串排序--基数排序高位优先
 */
public class MSD {

    private static int R = 256;
    private static final int M = 15;
    private static String[] aux;

    private static int CharAt(String s, int d) {
        if (d < s.length())
            return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // 以第d个字符为键, 将子数组a[lo..hi]排序

        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++)
            count[CharAt(a[i], d) + 2]++;

        for (int i = 0; i < R + 1; i++)
            count[i + 1] += count[i];

        for (int i = lo; i <= hi; i++)
            aux[count[CharAt(a[i], d) + 1]++] = a[i]; // 如果a[d]是结尾,CharAt返回-1.那这个字符串的在aux的索引为count[0]=0.排前面

        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        for (int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r + 1] -1, d+1);      //递归的以每个字符作为键进行排序

//      对于小数组换成插入排序
//        for (int i = lo; i <= hi ; i ++)
//            for (int j = i; j > lo && less(a[j], a[j -1], d); i++)
//                exch(a, j - 1, j);

    }
}
