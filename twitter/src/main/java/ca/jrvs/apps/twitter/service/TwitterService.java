package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TwitterService implements Service{

    Tweet createdTweet = null;
    CrdDao dao = new TwitterDao();


    @Override
    public Tweet postTweet(Tweet tweet) throws URISyntaxException, IOException {

        if(tweet.getText().length() > 140){
            throw new IllegalArgumentException("Text exceeds maximum of 140 characters");
        }
        if(tweet.getCoordinates().getCoordinates()[0] < -90 || tweet.getCoordinates().getCoordinates()[0] > 90){

            throw new IllegalArgumentException("Latitude is out of bounds");
        }
        if(tweet.getCoordinates().getCoordinates()[1] < -180 || tweet.getCoordinates().getCoordinates()[1] > 180){

            throw new IllegalArgumentException("Longitude is out of bounds");
        }

        return (Tweet) dao.create(tweet);
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {

        validateId(id);

        try {
            return (Tweet) dao.findById(id);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Tweet> deleteTweets(String[] ids) {

        List<Tweet> tweetList = new ArrayList<>();

        Arrays.stream(ids).forEach(x -> validateId(x));

        Arrays.stream(ids).forEach(x -> {
            try {
                tweetList.add((Tweet) dao.deleteById(x));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        return tweetList;
    }


    private void validateId(String id){


        //Will throw NumberFormatException if string is not parsable
        System.out.println(id);
        Float.parseFloat(id);

        if(id == null){
            throw new IllegalArgumentException("id cannot be null");
        }
        for (int i = 0; i < id.length();i++){
            if (!Character.isDigit(id.charAt(i))){
                throw new IllegalArgumentException("id must be a positive number compatible with type long");
            }
        }

    }

}
