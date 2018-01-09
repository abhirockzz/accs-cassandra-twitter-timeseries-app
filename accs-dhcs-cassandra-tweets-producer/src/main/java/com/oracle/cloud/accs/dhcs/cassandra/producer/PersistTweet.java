package com.oracle.cloud.accs.dhcs.cassandra.producer;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersistTweet {
    
    private static ExecutorService pool = Executors.newSingleThreadExecutor();

    //cassandra
    private static Session session = null;
    private static Cluster cluster = null;
    private static MappingManager manager = null;

    static {
        String username = System.getenv().getOrDefault("DHCS_USER_NAME", "admin");
        String password = System.getenv().getOrDefault("DHCS_USER_PASSWORD", "topsecret");
        String cassandraHost = System.getenv().getOrDefault("DHCS_NODE_LIST", "localhost");
        String cassandraPort = System.getenv().getOrDefault("DHCS_CLIENT_PORT", "9042");

        cluster = Cluster.builder()
                .addContactPoint(cassandraHost)
                .withPort(Integer.valueOf(cassandraPort))
                .withCredentials(username, password)
                .build();
        session = cluster.connect();

        manager = new MappingManager(session);
        System.out.println("Connected to Cassandra....");
    }

    static void persist(String tweeter, String tweet, Date created, String tweet_id) {
        Mapper<TweetInfo> mapper = manager.mapper(TweetInfo.class);
        TweetInfo info = new TweetInfo(tweeter, tweet, created, tweet_id);
        ListenableFuture<Void> saveFuture = mapper.saveAsync(info);
        saveFuture.addListener(() -> System.out.println("Persisted tweet ----- " + info), pool);
    }

}
