
package collections;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by user on 2/9/18.
 */
public class SetImpl {
    public static void main(String[] args) {
        Integer[] nums = {7, 1, 2, 5, 7, 7, 8, 10, 13, 2, 10};
        SetImpl si = new SetImpl();
        si.outputFromSet(nums);
        System.out.println("----------------------------");
        si.output(nums);
    }
    
    private void outputFromSet(Integer[] input) {
        Set<Integer> mySet = Sets.newHashSet(input);
        StringJoiner sj = new StringJoiner(", ");
        mySet.stream().map(Object::toString).forEach(sj::add);
        System.out.println(sj.toString());
    }
    
    private void output(Integer[] input) {
        Arrays.sort(input);
        StringJoiner sj = new StringJoiner(", ");
        for (int i =0; i < input.length; i++) {
            if (!(i > 0 && Objects.equals(input[i], input[i - 1]))) {
                sj.add(input[i].toString());
            }
        }
        System.out.println(sj.toString());
    }
}
