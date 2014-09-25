package net.masich.logserver.server.app.cli;

import org.kohsuke.args4j.Option;

import java.io.File;

public class Config {

    @Option(name = "--port", aliases = "-p", usage = "Port number, default: 9090.")
    private int port = 9090;

    @Option(name = "--name", aliases = "-n", usage = "Server name, will be used in logs, statistics etc.")
    private String name;

    @Option(name = "--max-connections", aliases = "-m", usage = "Maximum possible connections.")
    private int maxConnections = 100;

    @Option(name = "--config", aliases = "-c", usage = "Path to config.properties file.")
    private File config;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public File getConfig() {
        return config;
    }

    public void setConfig(File config) {
        this.config = config;
    }

}
