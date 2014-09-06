package net.masich.logserver.client.library.logback.communication;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;
import net.masich.logserver.client.library.communication.LogMessage;
import org.joda.time.LocalDateTime;

public class LogMessageFactory {

    private static final StackTraceElement EMPTY_CALLER_DATA = CallerData.naInstance();

    public static LogMessage fromEvent(ILoggingEvent event) {
        StackTraceElement caller = extractFirstCaller(event.getCallerData());

        LogMessage logMessage = new LogMessage();
        logMessage.setTimestamp(new LocalDateTime(event.getTimeStamp()));
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

    private static StackTraceElement extractFirstCaller(StackTraceElement[] callerDataArray) {
        StackTraceElement caller = EMPTY_CALLER_DATA;

        if (hasAtLeastOneNonNullElement(callerDataArray)) {
            caller = callerDataArray[0];
        }

        return caller;
    }

    private static boolean hasAtLeastOneNonNullElement(StackTraceElement[] callerDataArray) {
        return callerDataArray != null && callerDataArray.length > 0 && callerDataArray[0] != null;
    }

}
