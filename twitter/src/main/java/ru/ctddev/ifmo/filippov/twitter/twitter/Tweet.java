package ru.ctddev.ifmo.filippov.twitter.twitter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dmitry on 08.10.16.
 */
public class Tweet {
    private String text;
    private Date createdAt;

    private Date parseDate(String date) {
        DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss ZZZ yyyy");
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Tweet(String text, String createdAt) {
        this.text = text;
        this.createdAt = parseDate(createdAt);
    }

    public String getText() {
        return text;
    }

    Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Tweet && ((Tweet)o).text.equals(text) && ((Tweet)o).createdAt.equals(createdAt);
    }
}
