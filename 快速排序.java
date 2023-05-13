import java.util.Arrays;

public class 快速排序 {

    public static void main(String[] args) {
        int[] input1 = {-2222, 2000000, 3, 9, 2, 4, 6, 7, 3, 1, 9, 3, 22, 33, 111, 33333, 22, 1, 4, 2, 3, -2, 0, 0, 0};
        //    int[] input1 = {1,2};
        quickSort(input1, 0, input1.length - 1);

        System.out.println();
        print(input1);

    }


    public static void quickSort(int[] arr, int start, int end) {

        //灵魂出口，start end 只有1个元素，   (arr,0,-1)  /    (arr,0,0)   return...here
        if (start < end) {
            int pivot = partition(arr, start, end);

            //pivot的选择导致数组访问越界？？?
//            quickSort(arr, start, Math.max(pivot - 1, start));
//            quickSort(arr, Math.min(pivot + 1, end), end);


            //不会越，因入口judge
            quickSort(arr, start, pivot - 1);
            quickSort(arr, pivot + 1, end);
        }
    }


    public static int partition(int[] arr, int start, int end) {
        // 划分。。。

        int pivot = start;

        while (start < end) {

            //先left++ ，如果到end也没发现比 pivot大的，那就把end和pivot换了。

            // 上面是错误的，如果先start++ 会有问题。  在移动之前，left一定是可以交换的。但是一旦移动，可能出现left找到大的但是右边没找到小的这不允许。
            // 如果先动右边，假设两边都找到，完事大吉，交换，如果右边没找到，一直是大的。但是到了left这个位置，一定是找到了。left是保底。
            // left保底，最后实在不济还能pivot 和pivot换。所以 初始循环方向是在pivot other ..
            while (arr[end] >= arr[pivot] && start < end) {
                end--;
            }

            while (arr[start] <= arr[pivot] && start < end) {
                start++;
            }


            swap(arr, start, end);

        }

        swap(arr, pivot, start);

        print(arr);
        System.out.println(start);

        return start;
    }


    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void print(int[] arr) {
        System.out.print("[");
        for (int i : arr) {

            System.out.print(i + ",");
        }


        System.out.print("]");

        System.out.println();

    }

}
