package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetBuilder;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.databind.*;
import org.json.JSONException;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.net.URISyntaxException;
import java.util.List;

public class TwitterController implements Controller{

    TwitterService service = new TwitterService();
    Tweet tweet;

    @Override
    public Tweet postTweet(String[] args) throws URISyntaxException, IOException {

        if(args.length < 3){
            throw new IllegalArgumentException("Required arguments for Tweet creation not met");
        }

        String text = args[1];
        String coordinates = args[2];

        String[] coordArray = coordinates.split(":");

        if (text.isEmpty()){
            throw new IllegalArgumentException("Text field cannot be empty");
        }
        if (coordinates.length() < 2){
            throw new IllegalArgumentException("Tweet requires BOTH latitude and longitude");
        }

        float lat = Float.parseFloat(coordArray[0]);
        float lon = Float.parseFloat(coordArray[1]);

        tweet = new TweetBuilder().text(text).coordinates(lat, lon).build();

        return service.postTweet(tweet);
    }

    @Override
    public Tweet showTweet(String[] args) {

        if (args.length < 2){
            throw new IllegalArgumentException("Arguments must contain id to show Tweet");
        }


        return service.showTweet(args[1], new String[]{});

    }

    @Override
    public List<Tweet> deleteTweet(String[] args) {


        if (args.length < 2){
            throw new IllegalArgumentException("Arguments must contain id to delete Tweet");
        }

        String[] idArray = new String[args.length - 1];


        //i = 1 to ignore first value which will be the command
        for (int i = 1; i < args.length; i++){
            idArray[i] = args[i];
        }

        return service.deleteTweets(idArray);


    }
}
