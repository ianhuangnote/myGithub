package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by user on 2/9/18.
 */
public class QuickSort {
    static Random random = new Random();
    
    public static void sort(int[] array) {
        List<Integer> list = new ArrayList<Integer>();
        for (int n : array) {
            list.add(n);
        }
        list = sort(list);
        for (int i = 0; i < array.length; ++i) {
            array[i] = list.get(i);
        }
    }
    public static void main(String[] args) {
        Integer[] nums = {7, 1, 2, 5, 7, 7, 8, 10, 13, 2, 10};
        System.out.println(QuickSort.sort(new LinkedList<Integer>(Arrays.asList(nums))));
    }
    
    public static List<Integer> sort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        }
        // random pivot
        // int pivot = list.get(random.nextInt(list.size() - 1));
        
        // middle pivot
        int pivot = list.get(list.size() / 2);
        list.remove(list.size() / 2);
        List<Integer> less = new ArrayList<Integer>();
        List<Integer> greater = new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        for (Integer n : list) {
            if (n > pivot) {
                greater.add(n);
            } else {
                less.add(n);
            }
        }
        result.addAll(sort(less));
        result.add(pivot);
        result.addAll(sort(greater));
        return result;
    }
}
