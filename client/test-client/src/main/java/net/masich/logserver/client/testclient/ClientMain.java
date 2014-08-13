package net.masich.logserver.client.testclient;

import java.io.IOException;

import net.masich.logserver.client.library.Logger;

public class ClientMain {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2) {
            System.err.println("Use arguments: <host name> <port number>");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        Logger logger = new Logger(host, port);

        while (true) {
            logger.logMessage("Test message!");
            Thread.sleep(1000);
        }
    }

}
