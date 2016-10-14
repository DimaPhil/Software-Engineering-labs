package ru.ctddev.ifmo.filippov.twitter.twitter;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import java.util.*;

/**
 * Created by dmitry on 08.10.16.
 */
public class StatisticsManager {
    private final static String consumerKey = "mcKritFcVcwIzbwGWge1a283r";
    private final static String consumerKeySecret = "iJzp04D0fxtXASgNk1Bpd3TUCDcpvydpRsplDlkfslZvJgIHPY";
    private final static String accessToken = "2299088680-sRja6YC64dFqFU6Y6XomM4FHM2437jcj74QKrED";
    private final static String accessTokenSecret = "4VmVpvcdeDdndEPGeIt58Zk0khthGKxg7sXEe6P2pPmQe";
    private final static int HOUR_IN_MILLISECONDS = 60 * 60 * 1000;
    private static OAuthConsumer consumer;
    private TwitterClient client;

    private static void authenticate() {
        if (consumer == null) {
            consumer = new DefaultOAuthConsumer(consumerKey, consumerKeySecret);
            consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        }
    }

    public static OAuthConsumer getConsumer() {
        authenticate();
        return consumer;
    }

    public StatisticsManager(TwitterClient client) {
        authenticate();
        this.client = client;
    }

    public int[] getCommentsStatistics(String hashtag, int hours) {
        int[] hoursStatistics = new int[hours];
        long currentTime = System.currentTimeMillis();
        int count = 100;
        List<Tweet> tweetsInfo = client.getInfo(hashtag, Collections.singletonList(String.format("count=%d", count)));
        for (Tweet tweet : tweetsInfo) {
            long createdAt = tweet.getCreatedAt().getTime();
            long hoursSinceCreated = (currentTime - createdAt) / HOUR_IN_MILLISECONDS;
            if (hoursSinceCreated >= hours) {
                continue;
            }
            hoursStatistics[(int) hoursSinceCreated]++;
        }
        return hoursStatistics;
    }
}
