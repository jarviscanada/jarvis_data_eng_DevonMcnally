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

    private HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, TOKEN_SECRET);

    private static String   CONSUMER_KEY  = "BTThQmTPeTLbDqmwAUv8keYw6";
    private static String CONSUMER_SECRET =  "V0pDtE8sVBcUCGeoTyflzmCk8FiCwtI7kGs6MK6gIAepp9S5ZX";
    private static String ACCESS_TOKEN = "998592048474226689-GWShYN3PgBOnaebxfd6IEK7LjbfceFI";
    private static String TOKEN_SECRET = "q0zsUiFB7X9tZuCZVaxOzwJuUkZVuu3eBHufAiILq6UCs";


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
