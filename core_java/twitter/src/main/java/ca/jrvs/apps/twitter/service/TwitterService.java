package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.model.Tweet;

import java.util.ArrayList;
import java.util.List;

import ca.jrvs.apps.twitter.dao.CrDao;

public class TwitterService implements Service {

    private CrDao dao;

    public TwitterService(CrDao newDao) {
        this.dao = newDao;
    }

    /**
     * Validate and post a user input Tweet
     *
     * @param tweet tweet to be created
     * @return created tweet
     * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long out of range
     */
    @Override
    public Tweet postTweet(Tweet tweet) {
        validatePostTweet(tweet);
        return (Tweet) this.dao.create(tweet);
    }

    /**
     * Search a tweet by ID
     *
     * @param id tweet id
     * @return Tweet object which is returned by the Twitter API
     * @throws IllegalArgumentException if id or fields param is invalid
     */
    @Override
    public Tweet showTweet(String id) {
        validateId(id);
        return (Tweet) this.dao.findById(id);
    }

    /**
     * Delete Tweet(s) by id(s).
     *
     * @param ids tweet IDs which will be deleted
     * @return A list of Tweets
     * @throws IllegalArgumentException if one of the IDs is invalid.
     */
    @Override
    public List<Tweet> deleteTweets(String[] ids) throws InterruptedException, IllegalArgumentException{
        List<Tweet> tweets = new ArrayList<>();

        for (String id : ids) {
            validateId(id);
            tweets.add((Tweet) this.dao.deleteById(id));
            // Precaution against rate limit
            Thread.sleep(500);
        }
        return tweets;
    }

    /**
     *
     * @param tweet will be tested to validate text and coordinates
     */
    private static void validatePostTweet(Tweet tweet) {
        validateText(tweet);

        if (tweet.getCoordinates().getCoordinates().length == 2) {
            validateCoordinates(tweet);
        }
    }

    /**
     *
     * @param tweet will be tested to validate text
     * @throws IllegalArgumentException
     */
    private static void validateText(Tweet tweet) throws IllegalArgumentException {
        if (tweet.getText().length() > 140) {
            throw new IllegalArgumentException("Text to long: " + tweet.getText().length());
        }
    }

    /**
     *
     * @param tweet used to validate coordinates
     * @throws IllegalArgumentException
     */
    private static void validateCoordinates(Tweet tweet) throws IllegalArgumentException {
        float lat = tweet.getCoordinates().getCoordinates()[0];
        float lon = tweet.getCoordinates().getCoordinates()[1];
        boolean cond1 = lat < -90 || lat > 90;
        boolean cond2 = lon < -180 || lon > 180;
        if (cond1 || cond2) {
            throw new IllegalArgumentException("The coordinates are out of range: " + "lat = " + lat + ", lon = " + lon);
        }
    }

    /**
     *
     * @param id will be checked
     */
    private static void validateId(String id) {
        try {
            long numId = Long.parseLong(id);
        } catch (IllegalArgumentException nfe) {
            throw new IllegalArgumentException("Your id is incorrect: " + nfe.getMessage());
        }
    }
}