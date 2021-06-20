package tree;

// 皇位继承顺序
//一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
//
//这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。我们定义递归函数Successor(x, curOrder)，
// 给定一个人x和当前的继承顺序，该函数返回 x的下一继承人。
//
//Successor(x, curOrder):
//    如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：
//        如果 x 是国王，那么返回 null
//        否则，返回 Successor(x 的父亲, curOrder)
//    否则，返回 x 不在 curOrder 中最年长的孩子
//比方说，假设王国由国王，他的孩子Alice 和 Bob （Alice 比 Bob年长）和 Alice 的孩子Jack 组成。
//
//一开始，curOrder为["king"].
//调用Successor(king, curOrder)，返回 Alice ，所以我们将 Alice 放入 curOrder中，得到["king", "Alice"]。
//调用Successor(Alice, curOrder)，返回 Jack ，所以我们将 Jack 放入curOrder中，得到["king", "Alice", "Jack"]。
//调用Successor(Jack, curOrder)，返回 Bob ，所以我们将 Bob 放入curOrder中，得到["king", "Alice", "Jack", "Bob"]。
//调用Successor(Bob, curOrder)，返回null。最终得到继承顺序为["king", "Alice", "Jack", "Bob"]。
//通过以上的函数，我们总是能得到一个唯一的继承顺序。
//
//请你实现ThroneInheritance类：
//
//ThroneInheritance(string kingName) 初始化一个ThroneInheritance类的对象。国王的名字作为构造函数的参数传入。
//void birth(string parentName, string childName)表示parentName新拥有了一个名为childName的孩子。
//void death(string name)表示名为name的人死亡。一个人的死亡不会影响Successor函数，
// 也不会影响当前的继承顺序。你可以只将这个人标记为死亡状态。
//string[] getInheritanceOrder()返回 除去死亡人员的当前继承顺序列表。

//
//示例：
//
//输入：
//["ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"]
//[["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]]
//输出：
//[null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]]
//
//解释：
//ThroneInheritance t= new ThroneInheritance("king"); // 继承顺序：king
//t.birth("king", "andy"); // 继承顺序：king > andy
//t.birth("king", "bob"); // 继承顺序：king > andy > bob
//t.birth("king", "catherine"); // 继承顺序：king > andy > bob > catherine
//t.birth("andy", "matthew"); // 继承顺序：king > andy > matthew > bob > catherine
//t.birth("bob", "alex"); // 继承顺序：king > andy > matthew > bob > alex > catherine
//t.birth("bob", "asha"); // 继承顺序：king > andy > matthew > bob > alex > asha > catherine
//t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
//t.death("bob"); // 继承顺序：king > andy > matthew > bob（已经去世）> alex > asha > catherine
//t.getInheritanceOrder(); // 返回 ["king", "andy", "matthew", "alex", "asha", "catherine"]

//提示：
//
//1 <= kingName.length, parentName.length, childName.length, name.length <= 15
//kingName，parentName，childName和name仅包含小写英文字母。
//所有的参数childName 和kingName互不相同。
//所有death函数中的死亡名字 name要么是国王，要么是已经出生了的人员名字。
//每次调用 birth(parentName, childName) 时，测试用例都保证 parentName 对应的人员是活着的。
//最多调用105次birth 和death。
//最多调用10次getInheritanceOrder。

import java.util.*;

public class L1600 {

    public static void main(String[] args) {
        ThroneInheritance t = new ThroneInheritance("king");
        t.birth("king", "andy");
        t.birth("king", "bob");
        t.birth("king", "catherine");
        t.birth("andy", "matthew");
        t.birth("bob", "alex");
        t.birth("bob", "asha");
        System.out.println(t.getInheritanceOrder());
        t.death("bob");
        System.out.println(t.getInheritanceOrder());
    }


}

class ThroneInheritance {
    private HashMap<String, List<String>> edges;
    private Set<String> deathSet;
    private String king;

    public ThroneInheritance(String kingName) {
        king = kingName;
        edges = new HashMap<>();
        deathSet = new HashSet<>();
    }

    public void birth(String parentName, String childName) {
        List<String> childList = edges.getOrDefault(parentName, new ArrayList<>());
        childList.add(childName);
        edges.put(parentName, childList);
    }

    public void death(String name) {
        deathSet.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> result = new ArrayList<>();
        preorder(result ,king);
        return result;
    }

    private void preorder(List<String> result, String name) {
        if (!deathSet.contains(name)) {
            result.add(name);
        }
        List<String> ans = edges.getOrDefault(name, new ArrayList<>());
        for (String s : ans) {
            preorder(result, s);
        }
    }
}

class ThroneInheritance2 {
    class Node {
        String name;
        Node next;
        Node last;
        boolean isDeleted = false;
        Node (String _name) {
            name = _name;
        }
    }
    Map<String, Node> map = new HashMap<>();
    Node head = new Node(""), tail = new Node("");
    public ThroneInheritance2(String kingName) {
        Node root = new Node(kingName);
        root.next = tail;
        head.next = root;
        map.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        Node node = new Node(childName);
        map.put(childName, node);
        Node p = map.get(parentName);
        Node tmp = p;
        while (tmp.last != null) tmp = tmp.last;
        node.next = tmp.next;
        tmp.next = node;
        p.last = node;
    }

    public void death(String name) {
        Node node = map.get(name);
        node.isDeleted = true;
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<>();
        Node tmp = head.next;
        while (tmp.next != null) {
            if (!tmp.isDeleted) ans.add(tmp.name);
            tmp = tmp.next;
        }
        return ans;
    }
}
