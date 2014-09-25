package net.masich.logserver.client.library.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import net.masich.logserver.client.library.communication.LogMessage;
import net.masich.logserver.client.library.communication.socket.Client;
import net.masich.logserver.client.library.logback.communication.LogMessageFactory;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LogServerAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private String host;
    private Integer port;
    private String applicationCode;

    private String clientInstanceId = UUID.randomUUID().toString();
    private AtomicInteger currentMessageId = new AtomicInteger(0);

    private Client socketClient;

    @Override
    public void start() {
        socketClient = new Client(host, port);
    }

    protected void append(ILoggingEvent event) {
        int messageId = currentMessageId.incrementAndGet();

        LogMessage logMessage = LogMessageFactory.fromEvent(event);

        //TODO Add usage of applicationCode and messageId with previousMessageId.
        socketClient.sendMessage(logMessage);
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }

}
