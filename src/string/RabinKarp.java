package string;

import java.math.BigInteger;
import java.util.Random;

/**
 * RabinKarp: 基于散列的字符串查找
 */
public class RabinKarp {

    private String pat;// 拉斯维加斯算法需要, 蒙特卡洛算法不需要
    private long patHash;   // pat的散列值
    private int M;
    private long Q;      // 很大的素数
    private int R = 256;
    private long RM;    // R^(M-1)%Q : 用于减去第一个数字时的计算

    public RabinKarp(String pat) {
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M - 1; i++)
            RM = (R * RM) % Q;
        patHash = hash(pat, M);
    }

    public boolean check(String txt, int i) {
        for (int j = 0; j < M; j++)
            if (pat.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
    }

    public boolean check(int i) {
        return true;
    }

    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    private long hash(String key, int M) {
        long h = 0;
        for (int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    private int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);    // 计算前M位的hash
        if (patHash == txtHash && check(txt, 0)) return 0;
        for (int i = M; i < N; i++) {
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if (patHash == txtHash)
                if (check(txt ,i - M + 1)) return i - M + 1;
        }
        return N;
    }

    public static void main(String[] a) {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        long Q = prime.longValue();
        System.out.print(Q);
    }
}
