import java.util.ArrayList;
import java.util.List;

// 二进制手表
public class L401 {

    public static void main(String[] args) {
        L401 l = new L401();
        System.out.println(l.readBinaryWatch(1));
    }

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    result.add(i + ":" + ((j < 10) ? "0" : "") + j);
                }
            }
        }
        return result;
    }
}
