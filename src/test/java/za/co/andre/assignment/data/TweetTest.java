package za.co.andre.assignment.data;

import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;

public class TweetTest {
    /**
     * Basic functionality test
     */
    @Test
    public void author() {
        User user = new User("Koosie");
        Tweet tweet = new Tweet();
        tweet.setAuthor(user);
        Assert.assertEquals("Koosie", tweet.getAuthor().getName());
    }

    /**
     * Basic functionality test
     */
    @Test
    public void setData() {
        User user = new User("Koosie");
        Tweet tweet = new Tweet();
        tweet.setAuthor(user);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 140; i++) {
            sb.append("A");
        }
        tweet.setData(sb.toString());
        Assert.assertEquals(140, tweet.getData().length());
    }

    /**
     * Basic functionality test
     */
    @Test
    public void setDataTooLong() {
        User user = new User("Koosie");
        Tweet tweet = new Tweet();
        tweet.setAuthor(user);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 141; i++) {
            sb.append("A");
        }
        tweet.setData(sb.toString());
        Assert.assertEquals(140, tweet.getData().length());
    }
}
