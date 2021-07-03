
//根据字符出现频率排序

//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
//
//示例 1:
//
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
//示例 2:
//

import java.util.*;

public class L451 {

    public static void main(String[] args) {
        L451 l = new L451();
        System.out.println(l.frequencySort("tree"));
    }

    public String frequencySort(String s) {

        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2) - map.get(o1);
            }
        });
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            int freq = map.get(list.get(i));
            for (int j = 0; j < freq; j++) {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
}
