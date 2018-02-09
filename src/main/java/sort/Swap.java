package sort;

/**
 * Created by user on 2/9/18.
 */
public class Swap {
    public static int[] swap(int[] array, int indexA, int indexB) {
        int tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
        return array;
    }
}
