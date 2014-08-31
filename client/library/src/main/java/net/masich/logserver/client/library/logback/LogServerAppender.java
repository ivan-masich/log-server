package net.masich.logserver.client.library.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LogServerAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private String host;
    private String port;
    private String applicationCode;

    private String clientInstanceId = UUID.randomUUID().toString();
    private AtomicInteger currentMessageId = new AtomicInteger(0);

    protected void append(ILoggingEvent eventObject) {
        int messageId = currentMessageId.incrementAndGet();
        int previousMessageId = messageId - 1;

        //TODO Complete other steps like marshalling object to json, sending message to server and so on.
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setApplicationCode(String applicationCode) {
        this.applicationCode = applicationCode;
    }
}
