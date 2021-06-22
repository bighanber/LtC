package backtrack;

//剑指 Offer 38. 字符串的排列
//输入一个字符串，打印出该字符串中字符的所有排列。
//你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
//示例:
//输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//限制：
//1 <= s 的长度 <= 8
public class J38 {

    private List<String> result;
    private boolean[] visit;

    public String[] permutation(String s) {
        int length = s.length();
        result = new ArrayList<>();
        visit = new boolean[length];
        StringBuffer sb = new StringBuffer();
        char[] array = s.toCharArray();
        Arrays.sort(array);
        backtrack(array, 0, length, sb);
        int size = result.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = result.get(i);
        }
        return recArr;
    }

    private void backtrack(char[] array, int n, int m, StringBuffer sb) {
        if (n == m) {
            result.add(sb.toString());
            return;
        }
        for (int i = 0; i < m; i++) {
            if (visit[i] || (i > 0 && !visit[i - 1] && array[i - 1] == array[i])) {
                continue;
            }
            visit[i] = true;
            sb.append(array[i]);
            backtrack(array, n + 1, m, sb);
            sb.deleteCharAt(sb.length() - 1);
            visit[i] = false;
        }
    }
}
