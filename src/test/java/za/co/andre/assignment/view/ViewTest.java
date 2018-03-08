package za.co.andre.assignment.view;


import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.TestUtil;
import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;
import za.co.andre.assignment.parser.TweetFileParser;
import za.co.andre.assignment.parser.UserFileParser;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ViewTest {

    @Test
    public void buildOutput() {
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
        String str = View.BuildOutput(map, list);
        Assert.assertEquals(459, str.length());
    }
}
