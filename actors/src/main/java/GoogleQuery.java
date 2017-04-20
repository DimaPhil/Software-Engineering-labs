import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitry on 25.03.17.
 */
class GoogleQuery implements APIQuery {
    private static final String google = "http://www.google.com/search?q=";
    private static final int numResults = 10;
    private static final String userAgent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
    private String query;

    GoogleQuery(String query) {
        this.query = query;
    }

    private String getSearchUrl(String query) {
        return google + query + String.format("&num=%d", numResults);
    }

    @Override
    public List<Result> search() {
        List<Result> result = new ArrayList<>();
        try {
            Elements links = Jsoup.connect(google + URLEncoder.encode(getSearchUrl(this.query), "UTF-8"))
                                  .userAgent(userAgent).get().select(".g>.r>a");
            for (Element link : links) {
                String title = link.text();
                String url = link.absUrl("href");
                url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");

                if (!url.startsWith("http")) {
                    continue;
                }
                result.add(new Result(url, title));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.shuffle(result);
        return result;
    }

    @Override
    public String getAPIName() {
        return "Google";
    }
}
