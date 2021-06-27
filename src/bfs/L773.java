package bfs;

import java.util.*;

//滑动谜题

//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
//
//一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
//
//最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
//
//给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
//
//示例：
//
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成

//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板

public class L773 {

    public static void main(String[] args) {
        L773 l = new L773();
        System.out.println(l.slidingPuzzle(new int[][]{{4,1,2}, {5,0,3}}));
    }

    int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        if ("123450".equals(start)) {
            return 0;
        }
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        Set<String> seen = new HashSet<>();
        seen.add(start);

        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String next : getNext(status)) {
                    if (!seen.contains(next)) {
                        if ("123450".equals(next)) {
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

    public List<String> getNext(String status) {
        char[] chars = status.toCharArray();
        List<String> ret = new ArrayList<>();
        int index = status.indexOf("0");
        int[] neighbor = neighbors[index];
        for (int num : neighbor) {
            swap(chars, num, index);
            ret.add(new String(chars));
            swap(chars, num, index);
        }
        return ret;
    }

    public void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
}
