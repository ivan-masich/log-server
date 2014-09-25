package net.masich.logserver.server.app.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SocketConnection {

    private static final Logger LOG = LoggerFactory.getLogger(SocketConnection.class);

    public void init(int port) throws IOException {
        LOG.debug("Server socket was opened on port {}", port);

        /*ServerSocket serverSocket = new ServerSocket(port);

        try (
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        }*/
    }

}
