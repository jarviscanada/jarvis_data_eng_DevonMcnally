package ca.jrvs.apps.twitter.model;

public class TweetBuilder {

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



    public TweetBuilder createdTimestamp(final String createdTimestamp){
        this.createdTimestamp = createdTimestamp;
        return this;
    }

    public TweetBuilder id(final int id){
        this.id = id;
        return this;
    }

    public TweetBuilder id_str(final String id_str){

        this.id_str = id_str;
        return this;
    }

    public TweetBuilder text(final String text){
        this.text = text;
        return this;
    }

    public TweetBuilder entities(final Entities[] entities){
        this.entities = entities;
        return this;
    }

    public TweetBuilder coordinates(float lat, float lon){
        this.coordinates = new Coordinates(lat, lon);
        return this;
    }

    public TweetBuilder retweet_count(final Integer retweet_count){
        this.retweet_count = retweet_count;
        return this;
    }

    public TweetBuilder favorite_count(final Integer favorite_count){
        this.favorite_count = favorite_count;
        return this;
    }

    public TweetBuilder favorited(final boolean favorited){
        this.favorited = favorited;
        return this;
    }

    public  TweetBuilder retweeted(final boolean retweeted){
        this.retweeted = retweeted;
        return this;

    }

    public Tweet build(){
        Tweet tweet = new Tweet(createdTimestamp, id, id_str, text, entities, coordinates, retweet_count, favorite_count, favorited, retweeted);
        return tweet;
    }


}
