package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static ca.jrvs.apps.twitter.utils.TweetUtil.buildTweet;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller {

    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";

    private Service service;

    @Autowired
    public TwitterController(Service service) {
        this.service = service;
    }
    /**
     * Parse user argument and post a tweet by calling service classes
     *
     * @param args
     * @return a posted tweet
     * @throws IllegalArgumentException if args are invalid
     */
    @Override
    public Tweet postTweet(String[] args) {

        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: TwitterCLIApp post \"tweet_text\" latitude:longitude\"");
        }

        String tweet_text = args[1];
        String coord = args[2];
        String[] coordArray = coord.split(COORD_SEP);
        if (coordArray.length != 2 || tweet_text.equals(null) || tweet_text.equals("")) {
            throw new IllegalArgumentException("Invalid location format\nUsage: TwitterCLIApp post \"tweet_text\" latitude:longitude\"");
        }

        Float lat;
        Float lon;
        try {
            lat = Float.parseFloat(coordArray[0]);
            lon = Float.parseFloat(coordArray[1]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid location format\nUsage: TwitterCLIApp post \"tweet_text\" latitude:longitude\"", e);
        }

        Tweet postTweet = buildTweet(tweet_text, lat, lon);
        return service.postTweet(postTweet);
    }

    /**
     * Parse user argument and search a tweet by calling service classes
     *
     * @param args
     * @return a tweet
     * @throws IllegalArgumentException if args are invalid
     */
    @Override
    public Tweet showTweet(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("\"Usage: TwitterCLIApp show \"id\"");
        }

        String id = args[1];
        return this.service.showTweet(id);
    }

    /**
     * Parse user argument and delete tweets by calling service classes
     *
     * @param args
     * @return a list of deleted tweets
     * @throws IllegalArgumentException if args are invalid
     * @throws InterruptedException If there is an issue with the Thread method
     */
    @Override
    public List<Tweet> deleteTweet(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: TwitterCLIApp delete \"[id1, id2, ...]\"");
        }

        String[] ids = args[1].split(COMMA);

        if (ids.length < 1) {
            throw new IllegalArgumentException("Ids to delete invalid Usage: TwitterCLIApp delete \"[id1, id2, ...]\"");
        }

        return this.service.deleteTweets(ids);
    }
}
