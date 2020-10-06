package String;

import org.apache.commons.lang.StringUtils;

public class RemoveReplaceIgnoreCase {

    public static void main(String[] args) {
        String userName = "ian";
        String windowsDomainName = "DC=MTB-RKSWIRELESS,DC=COM";

        windowsDomainName = StringUtils.removeStartIgnoreCase(windowsDomainName, "dc=");
        //remove any whitespace between comma and equal sign.
        windowsDomainName = windowsDomainName.replaceAll("\\s+","").replaceAll( "(?i),dc=", ".");
        userName = String.format("%s@%s", userName, windowsDomainName);

        System.out.println("0:" + userName);
        System.out.println("1:" + windowsDomainName);

        String userName2 = "ian";
        String windowsDomainName2 = "dc=MTB-RKSWIRELESS,dc=COM";
        windowsDomainName2 = StringUtils.removeStartIgnoreCase(windowsDomainName2, "dc=");
        //remove any whitespace between comma and equal sign.
        windowsDomainName2 = windowsDomainName2.replaceAll("\\s+","");
        windowsDomainName2 = StringUtils.replace(windowsDomainName2, ",dc=", ".");
        userName2 = String.format("%s@%s", userName2, windowsDomainName2);
        System.out.println("--------------------------------------------");
        System.out.println("2:" + userName2);
        System.out.println("3:" + windowsDomainName2);
    }
}
