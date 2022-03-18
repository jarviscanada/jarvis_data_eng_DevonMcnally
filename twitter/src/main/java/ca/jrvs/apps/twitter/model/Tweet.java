package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;

public class Tweet {

    @JsonProperty("created_at")
    private String createdTimestamp;
    private long id;
    private String id_str;
    private String text;
    private Entities[] entities;
    private Coordinates coordinates;
    private Integer retweet_count;
    private Integer favorite_count;
    private boolean favorited;
    private boolean retweeted;


    public Tweet(String createdTimestamp, long id, String id_str, String text, Entities[] entities, Coordinates coordinates, Integer retweet_count, Integer favorite_count, boolean favorited, boolean retweeted) {
        this.createdTimestamp = createdTimestamp;
        this.id = id;
        this.id_str = id_str;
        this.text = text;
        this.entities = entities;
        this.coordinates = coordinates;
        this.retweet_count = retweet_count;
        this.favorite_count = favorite_count;
        this.favorited = favorited;
        this.retweeted = retweeted;
    }

    public Tweet(){

    }

    @Override
    public String toString() {
        return "Tweet{" +
                "createdTimestamp='" + createdTimestamp + '\'' +
                ", id=" + id +
                ", id_str='" + id_str + '\'' +
                ", text='" + text + '\'' +
                ", entities=" + Arrays.toString(entities) +
                ", coordinates=" + coordinates +
                ", retweet_count=" + retweet_count +
                ", favorite_count=" + favorite_count +
                ", favorited=" + favorited +
                ", retweeted=" + retweeted +
                '}';
    }


    public String getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(String createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public long getId() {
        return id;
    }



    public String getId_str() {
        return id_str;
    }



    public String getText() {
        return text;
    }



    public Entities[] getEntities() {
        return entities;
    }



    public Coordinates getCoordinates() {
        return coordinates;
    }



    public Integer getRetweet_count() {
        return retweet_count;
    }



    public Integer getFavorite_count() {
        return favorite_count;
    }



    public boolean isFavorited() {
        return favorited;
    }



    public boolean isRetweeted() {
        return retweeted;
    }


}
