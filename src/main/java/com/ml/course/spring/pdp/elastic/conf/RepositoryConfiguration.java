package com.ml.course.spring.pdp.elastic.conf;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class RepositoryConfiguration {

    @Value("${config.elasticsearch.clustername}")
    private String clusterName;

    @Value("${config.elasticsearch.host}")
    private String host;

    @Value("${config.elasticsearch.port}")
    private int port;

    @Bean
    public Client client() {
        final Settings settings = Settings.builder()
                //.put("cluster.name", clusterName)
                .build();

        try {
            final TransportClient result = new PreBuiltTransportClient(settings);
            result.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));

            return result;
        } catch (final UnknownHostException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }

}
