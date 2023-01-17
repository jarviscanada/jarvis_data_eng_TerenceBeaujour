package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpResponse;

public class TwitterCLIApp {

    public static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";

    private Controller controller;

    public TwitterCLIApp(Controller controller) {
        this.controller = controller;
    }

    // We don't need the main method anymore, we could delete it since we are using @Beans in TwitterCLIBean
    public static void main(String[] args) throws InterruptedException {
        // Get env variables
        String consumerKey = System.getenv("CONSUMER_KEY");
        String consumerSecret = System.getenv("CONSUMER_SECRET");
        String accessToken = System.getenv("ACCESS_TOKEN");
        String tokenSecret = System.getenv("TOKEN_SECRET");

        // Set up and pass dependencies
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        CrDao dao = new TwitterDao(httpHelper);
        Service service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp app = new TwitterCLIApp(controller);

        // Launch app
        app.run(args);
    }

    public void run(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new IllegalArgumentException(USAGE);
        }

        switch (args[0].toLowerCase()) {
            case "post":
                printTweet(this.controller.postTweet(args));
                break;
            case "show":
                printTweet(this.controller.showTweet(args));
                break;
            case "delete":
                this.controller.deleteTweet(args)
                        .forEach(this::printTweet);
                break;
            default:
                throw new IllegalArgumentException(USAGE);
        }
    }

    private void printTweet(Tweet tweet) {
        try {
            System.out.println(JsonUtil.toJson(tweet, true, false));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to convert tweet object to string: ", e);
        }
    }
}
