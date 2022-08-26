package com.example.rewardpointsdemo.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "application")
@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationProperties {

    private String title;

    private String version;

    private Amounts amounts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Amounts {
        public int amountOne;

        public int amountTwo;
    }
}
