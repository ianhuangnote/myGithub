package regexp;

public class replacement {

    public static void main(String[] args) {
        String input1 = "admin";
        String input2 = "Adloin";
        String input3 = "jitclib-user-1234567";
        String input4 = "user-1234567";
        String input5 = "user-123456";
        String input6 = "jitclib/jitclib-user-1234567";

        replacement r = new replacement();
        System.out.println(replacement.parseStr(input1));
        System.out.println(replacement.parseStr(input2));
        System.out.println(replacement.parseStr(input3));
        System.out.println(replacement.parseStr(input4));
        System.out.println(replacement.parseStr(input5));
        System.out.println(replacement.parseStr(input6));

    }

    private static String parseStr(String s) {
        String pattern = ".*-user-[0-9]{7}";
        String[] ss = s.split("/");
        if(ss.length !=2) {
            return s;
        }
        if(ss[1].trim().matches(pattern)) {
            return ss[0].trim();
        } else {
            return s;
        }
    }
}
