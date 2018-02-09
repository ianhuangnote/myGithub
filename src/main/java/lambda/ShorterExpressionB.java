package lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/9/18.
 */
public class ShorterExpressionB {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("5");
        list.add("4");
        list.forEach(System.out::println);
    }
}
