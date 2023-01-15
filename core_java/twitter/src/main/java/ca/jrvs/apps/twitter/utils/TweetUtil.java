package ca.jrvs.apps.twitter.utils;

import ca.jrvs.apps.twitter.model.Coordinates;
import org.apache.commons.lang3.RandomStringUtils;

import ca.jrvs.apps.twitter.model.Tweet;

import java.util.Random;

public class TweetUtil {

    public static Tweet buildTweet(String text, float lat, float lon) {
        return new Tweet(text, lat, lon);
    }

    public static Tweet buildTestTweet() {
        Tweet tweet = new Tweet();
        tweet.setText(getRandomText(false));
        float lat = getRandomLat(false);
        float lon = getRandomLon(false);
        tweet.setCoordinates(new Coordinates(new float[]{lat, lon}));
        return tweet;
    }

    public static String getRandomText(boolean expectedToFail) {
        if (expectedToFail) return RandomStringUtils.randomAlphabetic(145);
        return RandomStringUtils.randomAlphabetic(40);
    }

    public static float getRandomLat(boolean expectedToFail) {
        Random rand = new Random();
        if (expectedToFail) return 500 * rand.nextFloat();
        return 50 * rand.nextFloat();
    }

    public static float getRandomLon(boolean expectedToFail) {
        Random rand = new Random();
        if (expectedToFail) return 500 * rand.nextFloat();
        return 50 * rand.nextFloat();
    }
}
