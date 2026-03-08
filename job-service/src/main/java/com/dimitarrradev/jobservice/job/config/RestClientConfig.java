//package com.dimitarrradev.jobservice.job.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.web.client.RestClient;
//
//@Configuration
//public class RestClientConfig {
//
//    @Value("${company.service.uri}")
//    private String companyServiceURI;
//
//    @Bean
//    @LoadBalanced
//    RestClient.Builder loadBalancedClientBuilder() {
//        return RestClient.builder();
//    }
//
//    @Bean
//    @Primary
//    RestClient.Builder baseClientBuilder() {
//        return RestClient.builder();
//    }
//
//    @Bean
//    @Primary
//    @Qualifier("companyClient")
//    RestClient companyClient(@Qualifier("loadBalancedClientBuilder") RestClient.Builder builder) {
//        return builder.baseUrl(companyServiceURI).build();
//    }
//
//}
