package ca.jrvs.apps.twitter.model;

public class UserMention {

    private long id;
    private  String id_str;
    private int[] indices;
    private String name;
    private String screen_name;


    public UserMention(long id, String id_str, int[] indices, String name, String screen_name) {
        this.id = id;
        this.id_str = id_str;
        this.indices = indices;
        this.name = name;
        this.screen_name = screen_name;
    }

    public UserMention(){

    }
}
