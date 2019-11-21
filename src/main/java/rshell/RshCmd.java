package rshell;

import org.apache.commons.net.bsd.RCommandClient;

import java.io.IOException;
import java.net.InetAddress;

public class RshCmd {

    public static void main(String[] args) {
        String localuser, remoteuser, command;
        RCommandClient client;

        client = new RCommandClient();
        localuser = "root";
        remoteuser = "root";
        command = "ls -al .; echo $?";

        try {
            client.connect(InetAddress.getByName("10.206.10.109"), 5144);
        } catch (IOException e) {
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            client.rcommand(localuser, remoteuser, command);

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

        IOUtil.readWrite(client.getInputStream(), client.getOutputStream(), System.in, System.out);

        try {
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.exit(0);
    }

}