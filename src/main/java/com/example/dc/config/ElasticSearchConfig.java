package com.example.dc.config;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @ Author     ：duanchao
 * @ Date       ： 9:41 2021/1/27
 * @ Description：
 */
//@Configuration
public class ElasticSearchConfig  extends AbstractElasticsearchConfiguration {

    @Value("${es.url}")
    String esUrl;


    @Override
    @Bean("restHighLevelClient")
    public RestHighLevelClient elasticsearchClient() {
        String[] split = esUrl.split(",");
        ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(split).build();
        RestHighLevelClient rest = RestClients.create(clientConfiguration).rest();
        return rest;
    }


//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(
//                        new HttpHost("127.0.0.1", 9200, "http")
//                )
//        );
//        return client;
//    }
}