package net.masich.logserver.clientlibrary;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Logger {

    private String host;
    private Integer port;

    private Socket socket;
    private PrintWriter out;

    public Logger(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void logMessage(String message) {
        try {
            getOutputWriter().println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PrintWriter getOutputWriter() throws IOException {
        if (out == null) {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
        }

        return out;
    }
}
