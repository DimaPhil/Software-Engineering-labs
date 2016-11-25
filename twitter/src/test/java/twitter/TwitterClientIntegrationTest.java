package twitter;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import ru.ctddev.ifmo.filippov.twitter.http.URLReader;
import ru.ctddev.ifmo.filippov.twitter.rule.HostReachableRule;
import ru.ctddev.ifmo.filippov.twitter.twitter.Tweet;
import ru.ctddev.ifmo.filippov.twitter.twitter.TwitterClient;
import ru.ctddev.ifmo.filippov.twitter.twitter.ResponseParser;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dmitry on 08.10.16.
 */
//@HostReachableRule.HostReachable(TwitterClientIntegrationTest.HOST)
public class TwitterClientIntegrationTest {
    static final String HOST = "api.twitter.com";
    private final ResponseParser parser = new ResponseParser();
    private final URLReader reader = new URLReader();

    @ClassRule
    public static final HostReachableRule rule = new HostReachableRule();

    @Test
    public void getInfo() {
        TwitterClient client = new TwitterClient(HOST, "1.1");
        List<Tweet> tweets = client.getInfo("#freebandnames",
                                      Arrays.asList("since_id=24012619984051000",
                                                    "max_id=250126199840518145",
                                                    "result_type=mixed",
                                                    "count=4"));
        Assert.assertTrue(tweets.size() <= 4);
    }
}