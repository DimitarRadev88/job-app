package com.dimitarrradev.jobservice.job.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${company.service.uri}")
    private String companyServiceURI;

    @Bean
    @Qualifier("companyClient")
    @Scope(value = "singleton")
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(String.format(companyServiceURI))
                .build();
    }

}
