package net.masich.logserver.client.library.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;

public class LogMessageJsonMarshaller {

    private ObjectMapper objectMapper = new ObjectMapper();

    public LogMessageJsonMarshaller() {
        objectMapper.registerModule(new JodaModule());
    }

    public String marshal(LogMessage logMessage) {
        try {
            return objectMapper.writeValueAsString(logMessage);
        } catch (JsonProcessingException e) {
            //TODO Add usage of application exception.
            throw new RuntimeException(e);
        }
    }

    public LogMessage unmarshal(String logMessageAsString) {
        try {
            return objectMapper.readValue(logMessageAsString, LogMessage.class);
        } catch (IOException e) {
            //TODO Add usage of application exception.
            throw new RuntimeException(e);
        }
    }

}
