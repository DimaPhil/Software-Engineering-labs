package http;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import ru.ctddev.ifmo.filippov.twitter.http.URLReader;
import ru.ctddev.ifmo.filippov.twitter.rule.HostReachableRule;

/**
 * Created by dmitry on 08.10.16.
 */
//@HostReachableRule.HostReachable("api.twitter.com")
public class URLReaderTest {

    @ClassRule
    public static final HostReachableRule rule = new HostReachableRule();

    @Test
    public void read() {
        String result = new URLReader().readUrl("https://api.twitter.com/1.1/search/tweets.json?q=%23freebandnames&since_id=24012619984051000&max_id=250126199840518145&result_type=mixed&count=4");
        Assert.assertTrue(result.length() > 0);
    }
}