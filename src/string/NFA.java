package string;

import base.Bag;
import base.Stack;
import digraph.Digraph;
import digraph.DirectedDFS;

/**
 * 不确定优先状态自动机--正则表达式
 *
 * TODO
 * 多向或运算
 * 处理通配符
 * '+'运算符
 * 指定重复次数
 * 指定重复范围
 * 补集
 */
public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    public NFA(String regexp) {
        Stack<Integer> ops = new Stack<>();
        re = regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 1); // 为每个字符构造1个顶点

        for (int i = 0; i < M; i++) {
            int lp = i;     // 用于'*'的处理
            if (re[i] == '(' || re[i] == '|')
                ops.push(i);                    // 将左括号和双元运算符入栈, 遇到右括号出栈
            else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();     // 读取'('
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                }
                else lp = or;
            }

            if (i < M - 1 && re[i + 1] == '*') {
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            if (re[i] == '(' || re[i] == '*' || re[i] == ')')
                G.addEdge(i, i + 1);
        }
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < M; v++)
            if (dfs.marked(v)) pc.add(v);

        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<>();
            for (int v : pc) {
                if (re[v] == txt.charAt(i) || re[v] == '.') match.add(v + 1);
            }
            pc = new Bag<>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < M; v++)
                if (dfs.marked(v)) pc.add(v);
        }
        for (int v : pc)
            if (v == M) return true;
        return false;
    }
}
