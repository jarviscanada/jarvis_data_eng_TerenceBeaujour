package ca.jrvs.apps.twitter.model;

import java.util.Arrays;

public class UserMention {

    private int id;
    private String id_str;
    private String name;
    private float[] indices;
    private String screen_name;

    @Override
    public String toString() {
        return "UserMention{" +
                "id=" + id +
                ", id_str='" + id_str + '\'' +
                ", name='" + name + '\'' +
                ", indices=" + Arrays.toString(indices) +
                ", screen_name='" + screen_name + '\'' +
                '}';
    }
}
