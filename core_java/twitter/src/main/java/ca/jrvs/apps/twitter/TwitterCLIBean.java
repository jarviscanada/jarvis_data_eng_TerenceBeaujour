package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TwitterCLIBean {

    private final String CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    private final String CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    private final String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    private final String TOKEN_SECRET = System.getenv("TOKEN_SECRET");

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(TwitterCLIBean.class);
        TwitterCLIApp app = context.getBean(TwitterCLIApp.class);
        app.run(args);
    }

    @Bean
    public TwitterCLIApp twitterCLIApp(Controller controller) {
        return new TwitterCLIApp(controller);
    }

    @Bean
    public Controller controller(Service service) {
        return new TwitterController(service);
    }

    @Bean
    public Service service(CrDao crDao) {
        return new TwitterService(crDao);
    }

    @Bean
    public CrDao crDao(HttpHelper httpHelper) {
        return new TwitterDao(httpHelper);
    }

    @Bean
    HttpHelper helper() {
        return new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);
    }
}
