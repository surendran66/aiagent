package com.suren.jsontojsontaransformsuren.config;

import com.azure.cosmos.ConsistencyLevel;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.CosmosFactory;
import com.azure.spring.data.cosmos.config.CosmosConfig as AzureCosmosConfig;

@Configuration
@EnableCosmosRepositories(basePackages = "com.suren.jsontojsontaransformsuren.repository")
public class CosmosConfig {

    @Value("${azure.cosmos.uri}")
    private String uri;

    @Value("${azure.cosmos.key}")
    private String key;

    @Value("${azure.cosmos.database}")
    private String database;

    @Bean
    public CosmosTemplate cosmosTemplate() {
        CosmosFactory factory = new CosmosFactory(uri, key);
        AzureCosmosConfig config = AzureCosmosConfig.builder()
                .responseDiagnosticsProcessor(new com.azure.spring.data.cosmos.core.ResponseDiagnosticsProcessor() {
                    @Override
                    public void processResponseDiagnostics(com.azure.spring.data.cosmos.core.ResponseDiagnostics diagnostics) {
                    }
                })
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .build();
        return new CosmosTemplate(factory, database, config);
    }
}
