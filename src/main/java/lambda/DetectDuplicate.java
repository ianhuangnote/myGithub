package lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DetectDuplicate {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("*.apple.com");
        list.add("*.apple.com");
        list.add("123.apple.com");
        list.add("1234.apple.com");
        list.add("123.apple1.com");
        list.add("123.apple2.com1");
        list.add("123.apple2.com");
        list.add("123.apple3.com");

        System.out.println(list.stream().filter(name -> Collections.frequency(list, name) >1).findFirst());


    }
}

