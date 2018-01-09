package com.oracle.cloud.accs.dhcs.cassandra.tweets.api;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TweetRepository extends CrudRepository<TweetInfo, String> {
        @Query("Select * from tweets where created_date=?0 and tweeter=?1 allow filtering")
	public List<TweetInfo> findTweetsOnDateByTweeter(String created_date, String tweeter);
        
        @Query("Select * from tweets where tweeter=?0 allow filtering")
	public List<TweetInfo> findTweetsByTweeter(String tweeter);
        
}
