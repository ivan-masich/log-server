package net.masich.logserver.client.library.communication.socket;

import net.masich.logserver.client.library.communication.LogMessage;
import net.masich.logserver.client.library.communication.LogMessageJsonMarshaller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private static final LogMessageJsonMarshaller MARSHALLER = new LogMessageJsonMarshaller();

    private String host;
    private Integer port;

    private Socket socket;
    private PrintWriter out;

    public Client(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void sendMessage(LogMessage logMessage) {
        try {
            getOutputWriter().write(MARSHALLER.marshal(logMessage));
        } catch (IOException e) {
            //TODO Add usage of application exception.
            throw new RuntimeException(e);
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
