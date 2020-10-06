import java.util.Arrays;

public class StringSplit {

    public static void main(String[] args) {
        String s = "abc.com,def.com";
        String s1 = "abc.com,";
        String s2 = "abc.com";
        String s3 = "abc2.com,def2.com,xyz.com";

        Arrays.stream(s.split(",")).forEach(System.out::println);
        System.out.println("-----------------------");
        Arrays.stream(s1.split(",")).forEach(System.out::println);
        System.out.println("-----------------------");
        Arrays.stream(s2.split(",")).forEach(System.out::println);
        System.out.println("-----------------------");
        Arrays.stream(s3.split(",")).forEach(System.out::println);
    }
}
