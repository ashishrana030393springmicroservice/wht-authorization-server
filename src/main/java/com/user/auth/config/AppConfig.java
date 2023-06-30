package com.user.auth.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
@ToString
public class AppConfig {
    private AuthServer authServer;
    private Ui1 ui1;

    @Data
    @ToString
    public static class AuthServer{
        private String host;
        private String port;
        private String url;
        private List<String> allowedOrigins;
    }

    @Data
    @ToString
    public static class Ui1 {
        private String host;
        private String port;
        private String url;
    }
}
