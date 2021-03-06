package com.oracle.cloud.accs.dhcs.cassandra.producer.lcm;

import com.oracle.cloud.accs.dhcs.cassandra.producer.TwitterStreamListener;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public final class ProducerLifecycleManager {

    private static final Logger LOGGER = Logger.getLogger(ProducerLifecycleManager.class.getName());
    private static ProducerLifecycleManager INSTANCE = null;
    private final AtomicBoolean RUNNING = new AtomicBoolean(false);
    private final TwitterStream twitterStream;
    private final FilterQuery query;

    private ProducerLifecycleManager() {

        String _consumerKey = System.getenv().getOrDefault("TWITTER_CONSUMER_KEY", "junkkey");
        String _consumerSecret = System.getenv().getOrDefault("TWITTER_CONSUMER_SECRET", "topsecret");
        String _accessToken = System.getenv().getOrDefault("TWITTER_ACCESS_TOKEN", "007-token");
        String _accessTokenSecret = System.getenv().getOrDefault("TWITTER_ACCESS_TOKEN_SECRET", "topsecrettoken");

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(_consumerKey)
                .setOAuthConsumerSecret(_consumerSecret)
                .setOAuthAccessToken(_accessToken)
                .setOAuthAccessTokenSecret(_accessTokenSecret);

        twitterStream = new TwitterStreamFactory(configurationBuilder.build()).getInstance();
        twitterStream.addListener(new TwitterStreamListener());

        String tracked_terms = System.getenv().getOrDefault("TWITTER_TRACKED_TERMS", "java,nosql,cloud");
        query = new FilterQuery();
        query.track(tracked_terms.split(","));
    }

    public static ProducerLifecycleManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProducerLifecycleManager();
        }
        return INSTANCE;
    }

    public void start() throws Exception {
        if (RUNNING.get()) {
            throw new IllegalStateException("Service is already running");
        }
        twitterStream.filter(query);
        
        LOGGER.info("Started Tweets Producer thread");
        RUNNING.set(true);
    }

    public void stop() throws Exception {
        if (!RUNNING.get()) {
            throw new IllegalStateException("Service is NOT running. Cannot stop");
        }
        twitterStream.shutdown();
        LOGGER.info("Stopped Tweet Producer thread");
        RUNNING.set(false);
    }

}
