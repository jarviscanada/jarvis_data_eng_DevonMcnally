package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public class TwitterCLIApp {

    Controller controller = new TwitterController();
    public static final String EXCEPTION_STR = "Arguments required: command(post|show|delete), options";

    public static void main(String[] args) {

        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String AccessToken = System.getenv("AccessToken");
        String tokenSecret = System.getenv("tokenSecret");

        TwitterCLIApp cliApp = new TwitterCLIApp();
        try {
            cliApp.start(args);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(String[] args) throws JSONException, URISyntaxException, IOException {

        if( args.length < 1){
            throw new IllegalArgumentException(EXCEPTION_STR);
        }

        switch (args[0].toLowerCase()){

            case "post":
                printTweet(controller.postTweet(args));
                break;
            case "show":
                printTweet(controller.showTweet(args));
                break;
            case "delete":
                controller.deleteTweet(args).forEach(this::printTweet);
                break;
            default:
                throw new IllegalArgumentException(EXCEPTION_STR);

        }
    }

    private void printTweet(Tweet tweet) {

        ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();

        String tweetString = null;
        try {
            tweetString = writer.writeValueAsString(tweet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(tweetString);

    }
}
