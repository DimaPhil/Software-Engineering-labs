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
        return Arrays.asList(
                new Tweet("Aggressive Ponytail #freebandnames", "Fri Oct 14 18:02:58 MSK 2016"),
                new Tweet("Thee Namaste Nerdz. #FreeBandNames", "Fri Oct 14 16:17:40 MSK 2016"),
                new Tweet("Mexican Heaven, Mexican Hell #freebandnames", "Fri Oct 14 10:30:20 MSK 2016")
        );
    }
}
