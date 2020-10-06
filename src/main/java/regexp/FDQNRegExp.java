package regexp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class FDQNRegExp {


    public static void main(String[] args) {
        String uiReg = "^(?=.{1,253}$)((?=[a-z0-9-]{1,63}\\.)(xn--)?[a-z0-9]+(-[a-z0-9]+)*\\.)+[a-z]{2,63}$/i";
        String javaReg = "^(?=.{1,253}$)((?=[A-Za-z0-9-\\*]{1,63}\\.)(xn--)?[A-Za-z0-9\\*]+(-[A-Za-z0-9\\*]+)*\\.)+([A-Za-z\\*]{2,63}|[\\*])";
        String javaReg2 = "^(?=.{1,253}$)((?=[A-Za-z0-9-\\*]{1,63}\\.)(xn--)?[A-Za-z0-9\\*]+(-[A-Za-z0-9\\*]+)*\\.)+([A-Za-z\\*]{2,63}|[\\*])";

        List<String> inputs= new ArrayList<>();
        inputs.add("10.2.6.109");
        inputs.add("www.google.com");
        inputs.add("*.google.com");
        inputs.add("*.google.com.*");
        inputs.add("12server.google.com");
        inputs.add("12server*.google.com");
        inputs.add("129.google.com");
        inputs.add("server.google.com*");

        java.util.regex.Pattern p = java.util.regex.Pattern
                .compile(javaReg2);
        for (String input : inputs) {
            Matcher m = p.matcher(input);
            System.out.println("- is " + input + " matched? " + m.find());
        }

    }
}
