package com.gkoo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@ConfigurationProperties(prefix = "gkoo")
public class AppConfig {

    public AppConfig() {}
}
