import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class 天际线 {


    public static void main(String[] args) {

        //    int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        int[][] buildings = {{1, 38, 219}, {2, 19, 228}, {2, 64, 106}, {3, 80, 65}, {3, 84, 8}, {4, 12, 8}, {4, 25, 14}, {4, 46, 225}, {4, 67, 187}, {5, 36, 118}, {5, 48, 211}, {5, 55, 97}, {6, 42, 92}, {6, 56, 188}, {7, 37, 42}, {7, 49, 78}, {7, 84, 163}, {8, 44, 212}, {9, 42, 125}, {9, 85, 200}, {9, 100, 74}, {10, 13, 58}, {11, 30, 179}, {12, 32, 215}, {12, 33, 161}, {12, 61, 198}, {13, 38, 48}, {13, 65, 222}, {14, 22, 1}, {15, 70, 222}, {16, 19, 196}, {16, 24, 142}, {16, 25, 176}, {16, 57, 114}, {18, 45, 1}, {19, 79, 149}, {20, 33, 53}, {21, 29, 41}, {23, 77, 43}, {24, 41, 75}, {24, 94, 20}, {27, 63, 2}, {31, 69, 58}, {31, 88, 123}, {31, 88, 146}, {33, 61, 27}, {35, 62, 190}, {35, 81, 116}, {37, 97, 81}, {38, 78, 99}, {39, 51, 125}, {39, 98, 144}, {40, 95, 4}, {45, 89, 229}, {47, 49, 10}, {47, 99, 152}, {48, 67, 69}, {48, 72, 1}, {49, 73, 204}, {49, 77, 117}, {50, 61, 174}, {50, 76, 147}, {52, 64, 4}, {52, 89, 84}, {54, 70, 201}, {57, 76, 47}, {58, 61, 215}, {58, 98, 57}, {61, 95, 190}, {66, 71, 34}, {66, 99, 53}, {67, 74, 9}, {68, 97, 175}, {70, 88, 131}, {74, 77, 155}, {74, 99, 145}, {76, 88, 26}, {82, 87, 40}, {83, 84, 132}, {88, 99, 99}};

        天际线 a = new 天际线();
        List<List<Integer>> list = a.getSkyline(buildings);
        System.out.println(list);
    }

    //solution1 on2

    public List<List<Integer>> getSkyline(int[][] buildings) {

        // 寻找所有关键点。遍历这些关键点。
        //关键点是指建筑的边 右边也需要分析。
        List<Integer> edgeList = new ArrayList<>();
        for (int[] building : buildings) {
            edgeList.add(building[0]);
            edgeList.add(building[1]);
        }


        edgeList = edgeList.stream().distinct().sorted().collect(Collectors.toList());


        List<List<Integer>> ans = new ArrayList<>();
        for (Integer i : edgeList) {
            List<Integer> ansi = new ArrayList<>();

            ansi.add(i);
            ansi.add(calHignFori(i, buildings));
            ans.add(ansi);
        }
        refresh(ans);
        return ans;
    }

    private void refresh(List<List<Integer>> ans) {
        if (ans.size() == 0) {
            return;
        }
        for (int i = ans.size() - 1; i > 0; i--) {
            if (ans.get(i).get(1).equals(ans.get(i - 1).get(1))) {
                ans.remove(i);
            }
        }
    }

    private int calHignFori(int i, int[][] buildings) {
        int max = 0;
        for (int[] building : buildings) {
            if (i >= building[0] && i < building[1]) {
                if (building[2] > max)
                    max = building[2];
            }
        }
        return max;
    }

}
