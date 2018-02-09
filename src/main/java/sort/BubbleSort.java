package sort;

/**
 * Created by user on 2/9/18.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {7, 1, 2, 5, 7, 7, 8, 10, 13, 2, 10};
        BubbleSort.sort(nums);
    }
    
    public static void sort(int[] array) {
        //每跑一大輪，會固定最大值在右邊
        for (int i = array.length - 1; i > 0; --i) {
            boolean swapped = false;
            //從最左跟右邊一位相比
            for (int j = 0; j < i; ++j) {
                if (array[j] > array[j + 1]) {
                    Swap.swap(array, j, j + 1);
                    swapped = true;
                }
                
            }
            //假設剩下的都沒有交換位置的話，表示剩下數列已經排序好了 (提升效能用)
            if (!swapped) {
                break;
            }
        }
    }
}
