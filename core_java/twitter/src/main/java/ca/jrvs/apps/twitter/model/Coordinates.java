package ca.jrvs.apps.twitter.model;

import java.util.Arrays;

public class Coordinates {

    private float[] coordinates;
    private String type;

    public Coordinates() {

    }

    public Coordinates(float[] coord) {
        this.coordinates = coord;
        this.type = "Point";
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", type='" + type + '\'' +
                '}';
    }
}
