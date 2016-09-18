package string;

/**
 * 三向字符串快速排序
 * 根据首字母三向切分, 然后递归将得到的三个字数组继续切分排序
 */
public class Quick3string {

    private static int CharAt(String s, int d) {
        if (d < s.length()) return s.charAt(d);
        else return -1;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length -1, 0);
    }

    private static void exch(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        int v = CharAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = CharAt(a[i], d);
            if (t < v) exch(a, lt++, i++);
            else if (t > v) exch(a, gt-- ,i);
            else i++;
        }

        // a[lo..lt - 1] < v
        // a[lt..gt] = v
        // a[gt+1..hi] > v
        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1); // v = -1 时到字符串结尾
        sort(a, gt + 1, hi, d);
    }

}
