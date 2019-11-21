package rshell;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.net.bsd.RCommandClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

public class RcpCmd {

    public static void main(String[] args) throws IOException {
        String localuser, remoteuser, command;
        RCommandClient client;

        client = new RCommandClient();
        localuser = "root";
        remoteuser = "root";
        command = "cat /tmp/rsh_web.log; echo $?";

        try {
            client.connect(InetAddress.getByName("10.206.10.109"), 5144);
        } catch (IOException e) {
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            client.rcommand(localuser, remoteuser, command, true);

        } catch (IOException e) {
            try {
                client.disconnect();
            } catch (IOException f) {
                /* ignored */
            }
            e.printStackTrace();
            System.err.println("Could not execute command.");
            System.exit(1);
        }

        InputStream is = client.getInputStream();
        String nd5 = DigestUtils.md5Hex(is);
        System.out.println("Md5:" + nd5);

        IOUtil.readWrite(is, client.getOutputStream(), System.in, System.out);

        IOUtil.readWrite(client.getErrorStream(), client.getOutputStream(), System.in, System.out);

        try {
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }
}
