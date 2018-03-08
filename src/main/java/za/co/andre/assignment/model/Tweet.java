package za.co.andre.assignment.model;

import za.co.andre.assignment.util.Util;

/**
 * The data class to hold the tweet data
 *
 * @author Andre Labuschagne
 */
public class Tweet {
    private static final int TWEET_LENGTH = 140;
    private User author;
    private String data;

    /**
     * Get author
     * @return author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Set author
     * @param author the author or Thor ¯\_(ツ)_/¯
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Get the tweet data
     * @return the tweet
     */
    public String getData() {
        return data;
    }

    /**
     * Set the tweet data, the string will be chopped if it is too long
     * @param data the tweet
     */
    public void setData(String data) {
        if (Util.isBlank(data)) {
            this.data = "";
        } else if (data.length() > TWEET_LENGTH) {
            this.data = data.substring(0, TWEET_LENGTH);
        } else {
            this.data = data;
        }
    }

    /**
     * To print out the class
     * @return
     */
    @Override
    public String toString() {
        return "\t@" + author.getName() + ": " + data + "\n";
    }
}
