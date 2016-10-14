package ru.ctddev.ifmo.filippov.twitter.twitter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 08.10.16.
 */
public class ResponseParser {
    public List<Tweet> parseResponse(String response) {
        JsonObject entries = (JsonObject) new JsonParser().parse(response);
        JsonArray tweets = (JsonArray) entries.get("statuses");
        List<Tweet> info = new ArrayList<>(tweets.size());
        for (JsonElement element : tweets) {
            JsonObject tweet = (JsonObject) element;
            info.add(new Tweet(tweet.get("text").getAsString(),
                               tweet.get("created_at").getAsString()));
        }

        return info;
    }
}
