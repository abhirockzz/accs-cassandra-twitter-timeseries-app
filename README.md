## Sample time series app based on Twitter data with Cassandra on [Oracle Data Hub Cloud](https://cloud.oracle.com/datahub)

![](https://cdn-images-1.medium.com/max/1000/1*cnWcLELXqYk9itGwsOz06Q.jpeg)

The overall solution is pretty simple

**Tweet Producer** is a Java app which uses the Twitter streaming API to consume tweets and push them to Cassandra cluster on Data Hub

- It’s a Java app and uses **twitter4j** library to consume the tweet stream
- Applies user defined filter criteria/terms to filter relevant tweets from the stream
- Pushes the tweet data to Cassandra asynchronously
- It provides a REST API to start/stop the app on demand e.g. /tweets/producer

The **Tweet Query service** defines a REST API and interacts with Cassandra to fetch tweet data

- Its a basic **Spring Boot** app which leverages **Spring Data** and Spring Web
- The Cassandra module in Spring Data is used to interact with Cassandra
- `spring-boot-starter-web module` is used to expose a REST API to query tweet related info

For more info, check out the [complete blog](https://medium.com/oracledevs/getting-started-with-cassandra-using-oracle-data-hub-cloud-550889f4126e)