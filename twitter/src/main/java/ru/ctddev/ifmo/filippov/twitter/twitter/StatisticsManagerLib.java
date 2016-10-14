package ru.ctddev.ifmo.filippov.twitter.twitter;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

/**
 * Created by dmitry on 08.10.16.
 */
class StatisticsManagerLib {
    private final static String consumerKey = "mcKritFcVcwIzbwGWge1a283r";
    private final static String consumerKeySecret = "iJzp04D0fxtXASgNk1Bpd3TUCDcpvydpRsplDlkfslZvJgIHPY";
    private final static String accessToken = "2299088680-sRja6YC64dFqFU6Y6XomM4FHM2437jcj74QKrED";
    private final static String accessTokenSecret = "4VmVpvcdeDdndEPGeIt58Zk0khthGKxg7sXEe6P2pPmQe";
    private final static int HOUR_IN_MILLISECONDS = 60 * 60 * 1000;
    private static Twitter twitter;

    static void authenticate() {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setOAuthConsumerKey(consumerKey);
        configurationBuilder.setOAuthConsumerSecret(consumerKeySecret);
        configurationBuilder.setOAuthAccessToken(accessToken);
        configurationBuilder.setOAuthAccessTokenSecret(accessTokenSecret);
        Configuration configuration = configurationBuilder.build();
        twitter = new TwitterFactory(configuration).getInstance();
    }

    static int[] getCommentsStatistics(String hashtag, int hours) {
        int[] hoursStatistics = new int[hours];
        try {
            long currentTime = System.currentTimeMillis();
            Query query = new Query(hashtag.replace("#", "%23"));
            query.setCount(100);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    long createdAt = tweet.getCreatedAt().getTime();
                    long hoursSinceCreated = (currentTime - createdAt) / HOUR_IN_MILLISECONDS;
                    if (hoursSinceCreated >= hours) {
                        continue;
                    }
                    hoursStatistics[(int) hoursSinceCreated]++;
                    //System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
        return hoursStatistics;
    }
}
