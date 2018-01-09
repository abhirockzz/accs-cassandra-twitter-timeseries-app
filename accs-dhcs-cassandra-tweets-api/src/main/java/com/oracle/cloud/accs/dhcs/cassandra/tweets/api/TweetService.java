package com.oracle.cloud.accs.dhcs.cassandra.tweets.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Configuration
public class TweetService {

    public static void main(String[] args) {
        SpringApplication.run(TweetService.class, args);
    }

    @Autowired
    private TweetRepository tweetRepo;

    @RequestMapping(method = RequestMethod.GET, value = "/tweets/date/{date}", produces = "application/json")
    @ResponseBody
    public Iterable<TweetInfo> tweetsByDate(@PathVariable("date") String date) {
        System.out.println("Fetching tweets on "+ date);
        Iterable<TweetInfo> tweets = tweetRepo.findAll(Arrays.asList(date));
        System.out.println("found tweet "+ tweets);
        return tweets;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/tweets/{date}/{tweeter}", produces = "application/json")
    @ResponseBody
    public Iterable<TweetInfo> tweetsOnDateByTweeter(@PathVariable("date") String date,
                                                     @PathVariable("tweeter") String tweeter) {
        System.out.println("Fetching tweets on "+ date + " by tweeter "+ tweeter);
        Iterable<TweetInfo> tweets = tweetRepo.findTweetsOnDateByTweeter(date, tweeter);
        System.out.println("found tweet "+ tweets);
        return tweets;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/tweets/tweeter/{tweeter}", produces = "application/json")
    @ResponseBody
    public Iterable<TweetInfo> tweetsByTweeter(@PathVariable("tweeter") String tweeter) {
        System.out.println("Fetching tweets by "+ tweeter);
        
        Iterable<TweetInfo> tweets = tweetRepo.findTweetsByTweeter(tweeter);
        System.out.println("found tweet "+ tweets);
        return tweets;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/tweets", produces = "application/json")
    @ResponseBody
    public Iterable<TweetInfo> all() {
        System.out.println("Fetching ALL tweets");
       
        Iterable<TweetInfo> tweets = tweetRepo.findAll();
        return tweets;
    }

}
