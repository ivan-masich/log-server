package net.masich.logserver.server.app;

import net.masich.logserver.server.app.cli.Config;
import net.masich.logserver.server.app.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class Application {

    private Config config;

    public Application(Config config) {
        this.config = config;
    }

    public void start() {
        AnnotationConfigApplicationContext ctx  = new AnnotationConfigApplicationContext();

        MapPropertySource configSource = new MapPropertySource("CliConfig", configAsMap());
        ctx.getEnvironment().getPropertySources().addFirst(configSource);
        ctx.register(AppConfig.class);
        ctx.refresh();
    }

    private Map<String, Object> configAsMap() {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put("config.port", config.getPort());
        configMap.put("config.name", config.getName());
        configMap.put("config.maxConnections", config.getMaxConnections());
        return configMap;
    }

}
