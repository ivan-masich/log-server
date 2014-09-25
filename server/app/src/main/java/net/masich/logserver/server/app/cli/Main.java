package net.masich.logserver.server.app.cli;

import net.masich.logserver.server.app.Application;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {

    public static void main(String[] args) {
        Config config = new Config();
        CmdLineParser parser = new CmdLineParser(config);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        new Application(config).start();
    }

}
