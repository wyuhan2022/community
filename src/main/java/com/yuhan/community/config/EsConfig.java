package com.yuhan.community.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;

/**
 * @author yuhan
 * @create 2022-08-01 15:25
 */
@Configuration
public class EsConfig {
    @Value("${elasticSearch.url}")
    private String esUrl;

    @Bean
    RestHighLevelClient client() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(esUrl)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
