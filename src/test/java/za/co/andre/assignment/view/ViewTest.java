package za.co.andre.assignment.view;


import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.TestUtil;
import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;
import za.co.andre.assignment.parser.TweetFileParser;
import za.co.andre.assignment.parser.UserFileParser;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ViewTest {

    @Test
    public void buildOutput() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = UserFileParser.Read(file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, map.size());

        file = TestUtil.SetupFile(TestUtil.FileType.TWEET);
        List<Tweet> list = TweetFileParser.Read(map, file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, list.size());
        String str = View.BuildOutput(map, list);
        Assert.assertEquals(446, str.length());
    }
}
