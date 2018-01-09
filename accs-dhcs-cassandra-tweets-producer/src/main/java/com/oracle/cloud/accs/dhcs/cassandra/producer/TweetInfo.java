package com.oracle.cloud.accs.dhcs.cassandra.producer;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Table(keyspace = "tweetspace", name = "tweets",
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM",
        caseSensitiveKeyspace = false,
        caseSensitiveTable = false)

public class TweetInfo {

    @Column
    private String tweeter;
    private String tweet;

    @Column
    private Date created;
    
    @PartitionKey
    private String created_date;
    private String tweet_id;

    public TweetInfo() {
    }

    public TweetInfo(String tweeter, String tweet, Date created, String tweet_id) {
        this.tweeter = tweeter;
        this.tweet = tweet;
        this.created = created;
        this.created_date = new SimpleDateFormat("yyyy-MM-dd").format(created);
        this.tweet_id = tweet_id;
    }

    public String getTweeter() {
        return tweeter;
    }

    public String getTweet() {
        return tweet;
    }

    public Date getCreated() {
        return created;
    }

    public String getTweet_id() {
        return tweet_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    @Override
    public String toString() {
        return "TweetInfo{" + "tweeter=" + tweeter + ", tweet=" + tweet + ", created=" + created + ", created_date=" + created_date + ", tweet_id=" + tweet_id + '}';
    }

}
