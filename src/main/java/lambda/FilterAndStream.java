package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 2/9/18.
 */
public class FilterAndStream {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("5");
        list.add("4");
        list.stream().filter(s -> Integer.valueOf(s) < 3).forEach(System.out::print);
        
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("5");
        list2.add("4");
        List<String> list3 = list2.stream().filter(s -> Integer.valueOf(s) < 3).collect(Collectors.toList());
        list3.add("7");
        list3.forEach(System.out::print);
        
        List<String> list4 = new ArrayList<>();
        list4.add("1");
        list4.add("2");
        list4.add("3");
        list4.add("5");
        list4.add("4");
        System.out.println(list4.stream().mapToInt(Integer::valueOf).sum());
        System.out.println(list4.stream().filter(s -> Integer.valueOf(s) < 3).mapToInt(Integer::valueOf).average().getAsDouble());
    }
}
