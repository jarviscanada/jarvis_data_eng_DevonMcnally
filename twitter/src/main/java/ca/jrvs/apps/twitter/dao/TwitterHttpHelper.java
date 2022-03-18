package ca.jrvs.apps.twitter.dao;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;

public class TwitterHttpHelper implements HttpHelper {

    private OAuthConsumer consumer;
    private HttpClient httpClient;

    public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret){

        consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(accessToken, tokenSecret);

    }

    @Override
    public HttpResponse httpPost(URI uri) {

        HttpPost request = new HttpPost(uri);
        HttpResponse response = null;

        try {
            consumer.sign(request);
            HttpClient httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException | IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public HttpResponse httpGet(URI uri) {

        HttpGet request = new HttpGet(uri);
        HttpResponse response = null;

        try {
            consumer.sign(request);
            HttpClient httpClient = HttpClientBuilder.create().build();
            response = httpClient.execute(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException | IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
