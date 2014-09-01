package net.masich.logserver.client.library.logback;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import net.masich.logserver.client.library.communication.LogMessage;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LogServerAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private String host;
    private String port;
    private String applicationCode;

    private String clientInstanceId = UUID.randomUUID().toString();
    private AtomicInteger currentMessageId = new AtomicInteger(0);

    static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    protected void append(ILoggingEvent event) {
        int messageId = currentMessageId.incrementAndGet();
        int previousMessageId = messageId - 1;

        StackTraceElement caller = extractFirstCaller(event.getCallerData());

        LogMessage logMessage = new LogMessage();
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setThreadName(event.getThreadName());
        logMessage.setLevel(event.getLevel().toString());
        logMessage.setLogger(event.getLoggerName());
        logMessage.setMessage(event.getFormattedMessage());
        logMessage.setFilename(caller.getFileName());
        logMessage.setClassname(caller.getClassName());
        logMessage.setMethod(caller.getMethodName());
        logMessage.setLine(caller.getLineNumber());

        System.out.println(logMessage);

        //TODO Complete other steps like marshalling object to json, sending message to server and so on.
    }

    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
        StackTraceElement caller = EMPTY_CALLER_DATA;
        if(hasAtLeastOneNonNullElement(callerDataArray))
            caller = callerDataArray[0];
        return caller;
    }

    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
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
