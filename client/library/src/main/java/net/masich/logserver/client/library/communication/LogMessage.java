package net.masich.logserver.client.library.communication;

import org.joda.time.LocalDateTime;

public class LogMessage {

    private LocalDateTime timestamp;
    private String threadName;
    private String level;
    private String logger;
    private String message;
    private String filename;
    private String classname;
    private String method;
    private Integer line;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "timestamp=" + timestamp +
                ", threadName='" + threadName + '\'' +
                ", level='" + level + '\'' +
                ", logger='" + logger + '\'' +
                ", message='" + message + '\'' +
                ", classname='" + classname + '\'' +
                ", method='" + method + '\'' +
                ", line=" + line +
                '}';
    }

}
