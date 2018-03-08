package za.co.andre.assignment.data;

import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;

public class UserTest {
    /**
     * Basic functionality test
     */
    @Test
    public void constructor() {
        User user = new User("Koosie");
        Assert.assertEquals("Koosie", user.getName());
    }

    /**
     * Basic functionality test
     */
    @Test
    public void listFollowing() {
        User user = new User();
        Assert.assertNotNull(user.getFollowing());
    }

    /**
     * Basic functionality test
     */
    @Test
    public void listFollowedBy() {
        User user = new User();
        Assert.assertNotNull(user.getFollowedBy());
    }

    /**
     * Basic functionality test
     */
    @Test
    public void hash() {
        String name = "Alan";
        User user = new User(name);
        Assert.assertEquals(name.hashCode(), user.hashCode());
    }

    /**
     * Basic functionality test
     */
    @Test
    public void eq() {
        String name = "Alan";
        User user1 = new User(name);
        User user2 = new User(name);
        Assert.assertTrue(user1.equals(user2));
    }

    /**
     * Negative test: Not equals
     */
    @Test
    public void notEq() {
        String name1 = "Alan";
        String name2 = "Koosie";
        User user1 = new User(name1);
        User user2 = new User(name2);
        Assert.assertFalse(user1.equals(user2));
    }

    /**
     * Basic functionality test
     */
    @Test
    public void compareSame() {
        String name = "Alan";
        User user1 = new User(name);
        User user2 = new User(name);
        Assert.assertEquals(0, user1.compareTo(user2));
    }

    /**
     * Basic functionality test
     */
    @Test
    public void compareLess() {
        String name1 = "Alan";
        String name2 = "Aaron";
        User user1 = new User(name1);
        User user2 = new User(name2);
        Assert.assertTrue(user1.compareTo(user2) > 0);
    }

    /**
     * Basic functionality test
     */
    @Test
    public void compareGreater() {
        String name1 = "Alan";
        String name2 = "Wally";
        User user1 = new User(name1);
        User user2 = new User(name2);
        Assert.assertTrue(user1.compareTo(user2) < 0);
    }

    /**
     * Negative test: wrong object
     */
    @Test(expected = IllegalArgumentException.class)
    public void compareWrongObject() {
        String name1 = "Alan";
        User user1 = new User(name1);
        Tweet user2 = new Tweet();
        Assert.assertTrue(user1.compareTo(user2) < 0);
    }

    /**
     * Negative test: null object
     */
    @Test(expected = IllegalArgumentException.class)
    public void compareNullObject() {
        String name1 = "Alan";
        User user1 = new User(name1);
        Assert.assertTrue(user1.compareTo(null) < 0);
    }
}
