package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class TwitterDao implements CrdDao<Tweet, String>{
    //Constraints
    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/1.1/statuses/update.json";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/1.1/statuses/destroy/";
    //Symbols
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String equal = "=";
    //Response
    private static final int HTTP_OK = 200;

    private HttpHelper httpHelper;


    public TwitterDao(HttpHelper httpHelper){this.httpHelper = httpHelper;}
    @Override
    public Tweet create(Tweet tweet) throws URISyntaxException, IOException {

        String jsonString;

        String uri_str = API_BASE_URI + POST_PATH + QUERY_SYM + "lat="+tweet.getCoordinates().getCoordinates()[0]+AMPERSAND+"long="
                +tweet.getCoordinates().getCoordinates()[1]+AMPERSAND+"status="+tweet.getText();
        URI uri = new URI(uri_str.replace(" ", "%20"));

        HttpResponse response = httpHelper.httpPost(uri);
        jsonString = EntityUtils.toString(response.getEntity());

        return jsonStringToTweetObject(jsonString);
    }

    @Override
    public Tweet findById(String s) throws URISyntaxException, IOException {

        String jsonString;

        String uri_str = API_BASE_URI + SHOW_PATH + QUERY_SYM + "id="+s;
        URI uri = new URI(uri_str);

        HttpResponse response = httpHelper.httpGet(uri);
        jsonString = EntityUtils.toString(response.getEntity());

        return jsonStringToTweetObject(jsonString);
    }

    @Override
    public Tweet deleteById(String s) throws URISyntaxException, IOException {

        String jsonString;

        String uri_str = API_BASE_URI + DELETE_PATH + s +".json";
        URI uri = new URI(uri_str);

        HttpResponse response = httpHelper.httpPost(uri);
        jsonString = EntityUtils.toString(response.getEntity());

        return jsonStringToTweetObject(jsonString);

    }


    private Tweet jsonStringToTweetObject(String jsonString){

        Tweet tweet = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            tweet = mapper.readValue(jsonString, Tweet.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tweet;
    }

}
