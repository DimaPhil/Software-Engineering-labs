package ru.ctddev.ifmo.filippov.twitter.twitter;

import ru.ctddev.ifmo.filippov.twitter.http.URLReader;

import java.util.List;

/**
 * Created by dmitry on 08.10.16.
 */
public class TwitterClient {
    private String host;
    private String apiVersion;
    private URLReader reader;
    private ResponseParser parser;

    public TwitterClient(String host, String apiVersion) {
        this.host = host;
        this.apiVersion = apiVersion;
        this.reader = new URLReader();
        this.parser = new ResponseParser();
    }

    public String createUrl(String hashtag, List<String> options) {
        hashtag = hashtag.replace("#", "%23");
        String symbols = String.join("&", options);
        return "https://" + host + "/" + apiVersion + "/search/tweets.json?q=" + hashtag + "&" + symbols;
    }

    public List<Tweet> getInfo(String hashtag, List<String> options) {
        String response = reader.readUrl(createUrl(hashtag, options));
        return parser.parseResponse(response);
    }
}
