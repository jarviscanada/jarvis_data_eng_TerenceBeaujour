package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.JsonUtil;
import com.google.gdata.util.common.base.PercentEscaper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterDao implements CrDao<Tweet, String>{

    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy/";

    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";

    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;

    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    /**
     * Create an entity (Tweet) to the underlying storage
     *
     * @param entity entity that to be created
     * @return created entity
     */
    @Override
    public Tweet create(Tweet entity) {
        // Construct URI
        URI uri;
        try {
            uri = getPostURI(entity);
        } catch (URISyntaxException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Invalid tweet input", e);
        }

        // http request
        HttpResponse response = httpHelper.httpPost(uri);

        return parseResponseBody(response, HTTP_OK);
    }

    /**
     * Find an entity(Tweet) by its id
     *
     * @param s entity id
     * @return Tweet entity
     */
    @Override
    public Tweet findById(String s) {
        // Construct URI
        URI uri;
        try {
            uri = getShowURI(s);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid tweet input ", e);
        }

        // HTTP request
        HttpResponse httpResponse = httpHelper.httpGet(uri);

        return parseResponseBody(httpResponse, HTTP_OK);
    }

    /**
     * Delete an entity(Tweet) by its ID
     *
     * @param s of the entity to be deleted
     * @return deleted entity
     */
    @Override
    public Tweet deleteById(String s) {
        URI uri;

        try {
            uri = getDeleteURI(s);
        } catch (URISyntaxException e) {
            throw  new IllegalArgumentException("Invalid tweet input ", e);
        }

        // HTTP request
        HttpResponse httpResponse = httpHelper.httpPost(uri);

        return parseResponseBody(httpResponse, HTTP_OK);
    }

    private URI getPostURI(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
        URI uri;
        String text = tweet.getText();
        PercentEscaper percentEscaper = new PercentEscaper("", false);
        String path = API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + percentEscaper.escape(text);

        if (tweet.getCoordinates().getCoordinates().length == 2) {
            float lat = tweet.getCoordinates().getCoordinates()[0];
            float lon = tweet.getCoordinates().getCoordinates()[1];
            path += AMPERSAND + "lat" + EQUAL + lat + AMPERSAND + "lon" + EQUAL + lon;
        }

        uri = new URI(path);
        return uri;
    }

    private URI getShowURI(String id) throws URISyntaxException {
        String path = API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id;
        return new URI(path);
    }

    private URI getDeleteURI(String id) throws URISyntaxException {
        String path = API_BASE_URI + DELETE_PATH + id + ".json";
        return new URI(path);
    }

    private Tweet parseResponseBody(HttpResponse httpResponse, int expectedStatusCode) {
        Tweet tweet;

        // Check response status
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != expectedStatusCode) {
            try {
                System.out.println(EntityUtils.toString(httpResponse.getEntity()));
            } catch (IOException e) {
                System.out.println("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: " + statusCode);
        }

        if (httpResponse.getEntity() == null) {
            throw new RuntimeException("Empty response body");
        }

        // Convert Response Entity to str
        String jsonStr;
        try {
            jsonStr = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert JSON str to Object ", e);
        }

        try {
            tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert JSON str to Object", e);
        }
        return tweet;
    }
}
