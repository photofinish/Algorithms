package string;

/**
 * 字符串排序--基数排序低位优先
 */
public class LSD {
    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for (int d = W - 1; d >= 0; d--) {

            // 键索引计数法:
            int[] count = new int[R + 1];
            for (int i = 0; i <= R; i++)
                count[a[i].charAt(d) + 1]++;    //  1.统计频率

            for (int i = 0; i < R; i++)
                count[i + 1] += count[i];       //  2.将频率转换为索引

            for (int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];    //  3.数据分类

            for (int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }
}
