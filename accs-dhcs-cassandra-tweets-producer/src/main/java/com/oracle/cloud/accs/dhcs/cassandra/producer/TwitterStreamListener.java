package com.oracle.cloud.accs.dhcs.cassandra.producer;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TwitterStreamListener implements StatusListener {
    @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText() + " - "
                        + status.getLang() + " - " + status.isPossiblySensitive());
                //if (status.getLang().equals("en") && !status.isPossiblySensitive()) {
                if (!status.isPossiblySensitive()) {
                    PersistTweet.persist(status.getUser().getScreenName(),
                            status.getText(),
                            //format.format(status.getCreatedAt()),
                            status.getCreatedAt(),
                            String.valueOf(status.getId()));
                }else{
                    System.out.println("This tweet will NOT be persisted......");
                }

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
            }

            @Override
            public void onStallWarning(StallWarning warning) {
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
}
