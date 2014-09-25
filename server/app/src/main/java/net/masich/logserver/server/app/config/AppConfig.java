package net.masich.logserver.server.app.config;

import net.masich.logserver.server.app.connection.SocketConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean
    public SocketConnection beanStub() throws IOException {
        SocketConnection socketConnection = new SocketConnection();
        socketConnection.init(env.getRequiredProperty("config.port", Integer.class));

        return socketConnection;
    }

}
