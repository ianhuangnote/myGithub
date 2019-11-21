

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LinuxUserId {

    public static void main(String[] args) {
//        System.out.println(new com.sun.security.auth.module.UnixSystem().getUid());
        try {
            String userName = System.getProperty("user.name");
            String command = "id -u scpuser";
            Process child = Runtime.getRuntime().exec(command);

            // Get the input stream and read from it
            InputStream in = child.getInputStream();
            String text = IOUtils.toString(in, StandardCharsets.UTF_8.name());
            in.close();
            System.out.println(text);

        } catch (IOException e) {
        }
    }

}
