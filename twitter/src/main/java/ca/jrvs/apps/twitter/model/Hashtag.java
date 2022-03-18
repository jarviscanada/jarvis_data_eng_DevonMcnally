package ca.jrvs.apps.twitter.model;

import java.lang.reflect.Array;

public class Hashtag {

    private int[] indices;
    private String text;

    public Hashtag(int[] indices, String text) {
        this.indices = indices;
    }

    public Hashtag(){

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }








}
