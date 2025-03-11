import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QS {

    void quickSort(List<Integer> list, int start, int end) {
        if (start < end) {
            int pivot = partition(list, start, end);
            quickSort(list, start, pivot - 1);
            quickSort(list, pivot + 1, end);
            System.out.println(list);
        }
    }

    int partition(List<Integer> list, int start, int end) {
        int pivot = start;
        while (start < end) {
            while (start < end && list.get(pivot) <= list.get(end)) {
                end--;
            }
            while (start < end && list.get(pivot) >= list.get(start)) {
                start++;
            }
            Collections.swap(list, start, end);
        }
        Collections.swap(list, start, pivot);

        return start;
    }

    public static void main(String[] args) {
        int[] input1 = {-2222, 2000000, 3, 9, 2, 4, 6, 7, 3, 1, 9, 3, 22, 33, 111, 33333, 22, 1, 4, 2, 3, -2, 0, 0, 0};

        List list = Arrays.stream(input1).boxed().collect(Collectors.toList());
        new QS().quickSort(list, 0, list.size() - 1);
    }

}
