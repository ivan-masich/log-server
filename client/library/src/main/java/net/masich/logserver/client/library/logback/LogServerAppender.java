package net.masich.logserver.client.library.logback;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import net.masich.logserver.client.library.communication.LogMessage;
import org.joda.time.LocalDateTime;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LogServerAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    private String host;
    private Integer port;
    private String applicationCode;

    private String clientInstanceId = UUID.randomUUID().toString();
    private AtomicInteger currentMessageId = new AtomicInteger(0);

    private ObjectMapper objectMapper = new ObjectMapper();
    private Socket socket;
    private PrintWriter out;

    @Override
    public void start() {
        objectMapper.registerModule(new JodaModule());
    }

    protected void append(ILoggingEvent event) {
        int messageId = currentMessageId.incrementAndGet();
        int previousMessageId = messageId - 1;

        LogMessage logMessage = getLogMessage(event);

        try {
            //TODO Add usage of applicationCode and messageId with previousMessageId.
            getOutputWriter().write(objectMapper.writeValueAsString(logMessage));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LogMessage getLogMessage(ILoggingEvent event) {
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

        return logMessage;
    }

    private StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
        StackTraceElement caller = EMPTY_CALLER_DATA;

        if (hasAtLeastOneNonNullElement(callerDataArray)) {
            caller = callerDataArray[0];
        }

        return caller;
    }

    private boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }

    private PrintWriter getOutputWriter() throws IOException {
        if (out == null) {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
        }

        return out;
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
