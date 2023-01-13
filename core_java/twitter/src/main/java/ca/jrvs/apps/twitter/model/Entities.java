package ca.jrvs.apps.twitter.model;

import java.util.Arrays;

public class Entities {

    private Hashtag[] hashtags;
    private UserMention[] userMentions;

    public Hashtag[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(Hashtag[] hashtags) {
        this.hashtags = hashtags;
    }

    public UserMention[] getUserMentions() {
        return userMentions;
    }

    public void setUserMentions(UserMention[] userMentions) {
        this.userMentions = userMentions;
    }

    @Override
    public String toString() {
        return "Entities{" +
                "hashtags=" + Arrays.toString(hashtags) +
                ", userMentions=" + Arrays.toString(userMentions) +
                '}';
    }
}
