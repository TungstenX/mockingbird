package za.co.andre.assignment.parser;


import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.TestUtil;
import za.co.andre.assignment.model.User;

import java.io.File;
import java.util.Map;

public class UserFileParserTest {

    /**
     * Basic functionality test
     */
    @Test
    public void read() {
        File file = TestUtil.SetupFile(TestUtil.FileType.USER);
        Map<String, User> map = UserFileParser.Read(file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, map.size());
    }

    /**
     * Negative test: no file
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadNoFile() {
        Map<String, User> map = UserFileParser.Read(null);
    }

    /**
     * Negative test: wrong file
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadWrongFile() {
        File file = TestUtil.SetupFile(TestUtil.FileType.TWEET);
        Map<String, User> map = UserFileParser.Read(file.getAbsolutePath());
        file.delete();
        Assert.assertEquals(3, map.size());
    }
}