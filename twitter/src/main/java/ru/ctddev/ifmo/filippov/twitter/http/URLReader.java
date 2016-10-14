package ru.ctddev.ifmo.filippov.twitter.http;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import ru.ctddev.ifmo.filippov.twitter.twitter.StatisticsManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by dmitry on 08.10.16.
 */
public class URLReader {
    public String readUrl(String urlString) {
        Optional<URL> url = toUrl(urlString);
        OAuthConsumer consumer = StatisticsManager.getConsumer();
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) url.orElseThrow(() -> new AssertionError("url is null")).openConnection();
            consumer.sign(connection);
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = String.join("", in.lines().collect(Collectors.toList()));
            in.close();
            return response;
        } catch (IOException e) {
            throw new RuntimeException("IOException: an error occurred while building the trying to build connection with url " + url + ": " + e.getMessage());
        } catch (OAuthExpectationFailedException | OAuthMessageSignerException | OAuthCommunicationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<URL> toUrl(String url) {
        try {
            return Optional.of(new URL(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Malformed url: " + url);
        }
    }
}
