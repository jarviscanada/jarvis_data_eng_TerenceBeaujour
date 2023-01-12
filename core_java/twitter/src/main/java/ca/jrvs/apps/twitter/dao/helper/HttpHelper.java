package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;

import oauth.signpost.OAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;

public interface HttpHelper {

    /**
     * Execute a HTTP Post call
     * @param uri
     * @return
     */
    public HttpResponse httpPost(URI uri);

    /**
     * Execute a HTTP Get call
     * @param uri
     * @return
     */
    public HttpResponse httpGet(URI uri);
}
