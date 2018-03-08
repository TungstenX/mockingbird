package za.co.andre.assignment.parser;


import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.TestUtil;
import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Andre Labuschagne
 */
public class TweetFileParserTest {

    /**
     * Basic functionality test
     */
    @Test
    public void read() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = null;
        try {
            map = UserFileParser.Read(file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_USERS, map.size());

        file = TestUtil.SetupFile(TestUtil.FileType.TWEET);
        List<Tweet> list = null;
        try {
            list = TweetFileParser.Read(map, file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_TWEET, list.size());
    }

    /**
     * Negative test: Map but no file
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadMapNoFile() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = null;
        try {
            map = UserFileParser.Read(file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_USERS, map.size());

        try {
            List<Tweet> list = TweetFileParser.Read(map, null);
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
    }


    /**
     * Negative test: File but no map
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadFileNoMap() {
        File file = TestUtil.SetupFile(TestUtil.FileType.TWEET);
        try {
            List<Tweet> list = TweetFileParser.Read(null, file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
    }

    /**
     * Negative test: wrong file data
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadWrongFileData() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = null;
        try {
            map = UserFileParser.Read(file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_USERS, map.size());

        file = TestUtil.SetupFile(TestUtil.FileType.USER);
        List<Tweet> list = null;
        try {
            list = TweetFileParser.Read(map, file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_TWEET, list.size());
    }

    /**
     * Negative test: wrong file
     */
    @Test(expected = IOException.class)
    public void negReadWrongFile() throws IOException {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = null;
        try {
            map = UserFileParser.Read(file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_USERS, map.size());

        List<Tweet> list = TweetFileParser.Read(map, "/tmp/abc.txt");
        Assert.assertEquals(0, list.size());
    }

    /**
     * Negative test: wrong data no user
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadWrongData1() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = null;
        try {
            map = UserFileParser.Read(file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_USERS, map.size());

        file = TestUtil.SetupFile(TestUtil.FileType.WRONG_TWEET1);
        List<Tweet> list = null;
        try {
            list = TweetFileParser.Read(map, file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_TWEET, list.size());
    }

    /**
     * Negative test: wrong data, unknown user
     */
    @Test
    public void negReadWrongData2() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = null;
        try {
            map = UserFileParser.Read(file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_USERS, map.size());

        file = TestUtil.SetupFile(TestUtil.FileType.WRONG_TWEET2);
        List<Tweet> list = null;
        try {
            list = TweetFileParser.Read(map, file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(0, list.size());
    }
}