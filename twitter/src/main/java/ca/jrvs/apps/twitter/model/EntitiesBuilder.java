package ca.jrvs.apps.twitter.model;

public class EntitiesBuilder {

    private Hashtag[] hashtags;
    private UserMention[] userMentions;

    public EntitiesBuilder Hashtags(Hashtag[] hashtags) {
        this.hashtags = hashtags;
        return this;
    }

    public EntitiesBuilder UserMentions(UserMention[] userMentions) {
        this.userMentions = userMentions;
        return this;
    }

    public Entities build(){

        Entities entities = new Entities(hashtags, userMentions);
        return entities;

    }

}
