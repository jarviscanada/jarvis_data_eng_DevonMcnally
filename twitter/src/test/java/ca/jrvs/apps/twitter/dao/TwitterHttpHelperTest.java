package ca.jrvs.apps.twitter.dao;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

import static org.junit.Assert.*;

public class TwitterHttpHelperTest {

    private String consumerKey = System.getenv("consumerKey");
    private String consumerSecret = System.getenv("consumerSecret");
    private String accessToken = System.getenv("accessToken");
    private String tokenSecret = System.getenv("tokenSecret");


    public HttpResponse httpPost(URI uri) throws IOException {


        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        HttpResponse response = httpHelper.httpPost(URI.create("https://api.twitter.com/1.1/statuses/update.json?lat=45.0&long=78.0&status=HttpHelper Junit Test"));
        System.out.println(EntityUtils.toString(response.getEntity()));

        return response;
    }


}