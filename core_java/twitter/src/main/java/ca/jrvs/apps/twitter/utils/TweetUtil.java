package ca.jrvs.apps.twitter.utils;

import ca.jrvs.apps.twitter.model.Tweet;

public class TweetUtil {

    public static Tweet buildTweet(String text, float lat, float lon) {
        return new Tweet(text, lat, lon);
    }
}
