package ca.jrvs.apps.twitter.model;

public class Entities {

    private Hashtag[] hashtags;
    private UserMention[] user_mentions;

    public Entities(Hashtag[] hashtags, UserMention[] user_mentions) {
        this.hashtags = hashtags;
        this.user_mentions = user_mentions;
    }

    public Entities(){

    }




}

