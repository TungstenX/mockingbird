package za.co.andre.assignment.parser;


import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.TestUtil;
import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;

import java.io.File;
import java.util.List;
import java.util.Map;

public class TweetFileParserTest {

    /**
     * Basic functionality test
     */
    @Test
    public void read() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = UserFileParser.Read(file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, map.size());

        file = TestUtil.SetupFile(TestUtil.FileType.TWEET);
        List<Tweet> list = TweetFileParser.Read(map, file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, list.size());
    }

    /**
     * Negative test: no File
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadNoFile() {
        List<Tweet> list = TweetFileParser.Read(null, null);
    }

    /**
     * Negative test: wrong file
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadWrongFile() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = UserFileParser.Read(file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, map.size());

        file = TestUtil.SetupFile(TestUtil.FileType.USER);
        List<Tweet> list = TweetFileParser.Read(map, file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, list.size());
    }
}