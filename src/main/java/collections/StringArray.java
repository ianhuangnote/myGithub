package collections;

import java.util.Arrays;

public class StringArray {

    public static void main(String[] args) {
        String[] arrays = {"tac", "zzz", "abc", "z", "a"};
        String toBeCheck = "z";
        System.out.println(new StringArray().isConfiguredRealmsIncluded(arrays, toBeCheck));
    }

    private boolean isConfiguredRealmsIncluded(String[] configuredRealms, String toBeCheckRealm) {
        return Arrays.stream(configuredRealms).anyMatch(toBeCheckRealm::equals);
//        for(String realm: configuredRealms) {
//            if(realm.equals(toBeCheckRealm)) {
//                return true;
//            }
//        }
//        return false;
    }
}
