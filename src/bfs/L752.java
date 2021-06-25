package bfs;

//打开转盘锁

//你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
// 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
//
//锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
//
//列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
//
//字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
//
//
//
//示例 1:
//
//输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
//输出：6
//解释：
//可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
//注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
//因为当拨动到 "0102" 时这个锁就会被锁定。

import com.sun.jmx.snmp.SnmpString;

import java.util.*;

public class L752 {

    public int openLock(String[] deadends, String target) {

        if ("0000".equals(target)) {
            return 0;
        }
        Set<String> death = new HashSet<>();
        for (String s : deadends) {
            death.add(s);
        }
        if (death.contains("0000")) {
            return -1;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String next : getNext(status)) {
                    if (!seen.contains(next) && !death.contains(next)) {
                        if (next.equals(target)) {
                            return step;
                        }
                        queue.offer(next);
                        seen.add(next);
                    }
                }
            }
        }
        return -1;
    }

    public List<String> getNext(String current) {
        List<String> ret = new ArrayList<>();
        char[] chars = current.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char num = chars[i];
            chars[i] = numPrev(num);
            ret.add(new String(chars));
            chars[i] = numSucc(num);
            ret.add(new String(chars));
            chars[i] = num;
        }
        return ret;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }
}