package string;

/**
 * 字母表
 */
public class Alphabet {
    private char[] string;
    private int R;
    private int[] indices;

    public Alphabet(String s) {
        string = s.toCharArray();
        R = s.length();
        indices = new int[Character.MAX_VALUE];
        for (int i = 0; i < s.length(); i++) {
            indices[i] = -1;
        }

        for (int i = 0; i < s.length(); i++) {
            indices[string[i]] = i;
        }
    }

    public char toChar(int index) {
        return string[index];
    }

    public int toIndex(char c) {
        return indices[c];
    }

    public boolean contains(char c) {
        return indices[c] != -1;
    }

    public int R() {
        return R;
    }

    public int lgR() {
        int lgR = 0;
        for (int i = R - 1; i >= 1 ; i /= 2)
            lgR++;
        return lgR;
    }

    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        for (int i = 0; i < s.length(); i++)
            indices[i] = this.indices[s.charAt(i)];
        return indices;
    }

    public String toChars(int[] indices) {
        char[] str = new char[indices.length];
        for (int i = 0; i < indices.length; i++)
            str[i] = string[indices[i]];
        return new String(str);
    }
}
