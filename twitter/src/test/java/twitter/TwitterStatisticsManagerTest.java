package twitter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ctddev.ifmo.filippov.twitter.twitter.StatisticsManager;
import ru.ctddev.ifmo.filippov.twitter.twitter.Tweet;
import ru.ctddev.ifmo.filippov.twitter.twitter.TwitterClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by dmitry on 14.10.16.
 */
public class TwitterStatisticsManagerTest {
    private StatisticsManager manager;

    @Mock
    private TwitterClient client;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        manager = new StatisticsManager(client);
    }

    @Test
    public void getCommentsStatistics() {
        String hashtag = "#freebandnames";
        List<String> symbols = Collections.singletonList("count=100");
        when(client.getInfo(hashtag, symbols)).thenReturn(createAnswer());

        int[] info = manager.getCommentsStatistics(hashtag, 5);
        int[] expectedResult = new int[]{1, 0, 1, 0, 0};
        Assert.assertEquals(expectedResult.length, info.length);
        for (int i = 0; i < info.length; i++) {
            Assert.assertEquals(info[i], expectedResult[i]);
        }
    }

    private List<Tweet> createAnswer() {
        long currentTime = System.currentTimeMillis();
        /*
        new Tweet("Aggressive Ponytail #freebandnames", "Fri Oct 15 10:35:40 MSK 2016"),
        new Tweet("Thee Namaste Nerdz. #FreeBandNames", "Fri Oct 15 8:35:40 MSK 2016"),
        new Tweet("Mexican Heaven, Mexican Hell #freebandnames", "Fri Oct 15 03:35:40 MSK 2016")
        */
        int HOUR_IN_MILLISECONDS = 60 * 60 * 1000;
        return Arrays.asList(
                new Tweet("Aggressive Ponytail #freebandnames", new Date(currentTime - HOUR_IN_MILLISECONDS + 500).toString()),
                new Tweet("Thee Namaste Nerdz. #FreeBandNames", new Date(currentTime - HOUR_IN_MILLISECONDS * 2).toString()),
                new Tweet("Mexican Heaven, Mexican Hell #freebandnames", new Date(currentTime - HOUR_IN_MILLISECONDS * 5 - 1).toString())
        );
    }
}
