package com.oracle.cloud.accs.dhcs.cassandra.tweets.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        String keyspace = System.getenv().getOrDefault("CASSANDRA_TWEETS_KEYSPACE", "tweetspace");
        return keyspace;

    }

    @Bean
    public CassandraClusterFactoryBean cluster() {

        String username = System.getenv().getOrDefault("DHCS_USER_NAME", "admin");
        String password = System.getenv().getOrDefault("DHCS_USER_PASSWORD", "topsecret");
        String cassandraHost = System.getenv().getOrDefault("DHCS_NODE_LIST", "localhost");
        String cassandraPort = System.getenv().getOrDefault("DHCS_CLIENT_PORT", "9042");

        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();

        cluster.setContactPoints(cassandraHost);
        cluster.setPort(Integer.valueOf(cassandraPort));
        cluster.setUsername(username);
        cluster.setPassword(password);

        return cluster;
    }

    @Bean
    public CassandraMappingContext cassandraMapping()
            throws ClassNotFoundException {

        return new BasicCassandraMappingContext();
    }
}
