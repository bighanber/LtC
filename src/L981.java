
//基于时间的键值存储

//创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
//
//1. set(string key, string value, int timestamp)
//
//存储键 key、值 value，以及给定的时间戳 timestamp。
//2. get(string key, int timestamp)
//
//返回先前调用 set(key, value, timestamp_prev) 所存储的值，其中 timestamp_prev <= timestamp。
//如果有多个这样的值，则返回对应最大的  timestamp_prev 的那个值。
//如果没有值，则返回空字符串（""）。
//
//
//示例 1：
//
//输入：inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
//输出：[null,null,"bar","bar",null,"bar2","bar2"]
//解释：
//TimeMap kv;
//kv.set("foo", "bar", 1); // 存储键 "foo" 和值 "bar" 以及时间戳 timestamp = 1
//kv.get("foo", 1);  // 输出 "bar"
//kv.get("foo", 3); // 输出 "bar" 因为在时间戳 3 和时间戳 2 处没有对应 "foo" 的值，所以唯一的值位于时间戳 1 处（即 "bar"）
//kv.set("foo", "bar2", 4);
//kv.get("foo", 4); // 输出 "bar2"
//kv.get("foo", 5); // 输出 "bar2"

import java.util.HashMap;
import java.util.TreeMap;

public class L981 {
}

class TimeMap {

    /** Initialize your data structure here. */
    HashMap<String, TreeMap<Integer, String>> map;
    public TimeMap() {
        map = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k-> new TreeMap()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if(!map.containsKey(key)) {
            return "";
        }
        Integer time = map.get(key).floorKey(timestamp);
        if(time == null) {
            return "";
        }
        return map.get(key).get(time);
    }
}
