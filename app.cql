CREATE KEYSPACE tweetspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

use tweetspace;

CREATE TABLE tweets (
   tweeter text,
   tweet_id text,
   tweet text,
   created timestamp,   
   created_date text,
   PRIMARY KEY ((created_date), created, tweeter)
) WITH CLUSTERING ORDER BY (created DESC);
