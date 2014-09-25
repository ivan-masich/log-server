package net.masich.logserver.client.testclient;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientMain {
    private static final Logger LOG = LoggerFactory.getLogger(ClientMain.class);

    public static void main(String[] args) throws Exception {
        while (true) {
            LOG.debug("Some temporary message.");
            Thread.sleep(1000);
        }
    }

}
