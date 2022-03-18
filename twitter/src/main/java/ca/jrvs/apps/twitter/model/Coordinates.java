package ca.jrvs.apps.twitter.model;

public class Coordinates {

    private float[] coordinates;
    private String type = "Point";

    public Coordinates(float lat, float lon) {
        coordinates = new float[]{lat, lon};
    }
    public Coordinates(){

    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public float getLat(){return coordinates[0];}
    public float getLon(){return coordinates[1];}

}
