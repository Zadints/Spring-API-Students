package org.zadints.zadintsapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GithubConfig {
    @Bean
    @Qualifier("githubRestClient")
    public RestClient githubRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://api.github.com")
                .defaultHeader("Accept", "application/vnd.github+json")
                .build();
    }
}
