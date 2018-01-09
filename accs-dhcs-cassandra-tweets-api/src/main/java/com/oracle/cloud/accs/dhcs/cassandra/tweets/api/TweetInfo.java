package com.oracle.cloud.accs.dhcs.cassandra.tweets.api;

import java.util.Date;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table(value = "tweets")

public class TweetInfo {

    @Column
    private String tweeter;
    private String tweet;

    @Column
    private Date created;

    @PrimaryKey
    private String created_date;
    private String tweet_id;

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
