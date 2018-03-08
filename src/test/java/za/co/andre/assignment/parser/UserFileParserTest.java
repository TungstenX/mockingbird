package za.co.andre.assignment.parser;


import org.junit.Assert;
import org.junit.Test;
import za.co.andre.assignment.TestUtil;
import za.co.andre.assignment.model.User;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class UserFileParserTest {

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
    }

    /**
     * Negative test: no file
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadNoFile() {
        try {
            Map<String, User> map = UserFileParser.Read(null);
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
    }

    /**
     * Negative test: wrong file
     */
    @Test(expected = IllegalArgumentException.class)
    public void negReadWrongFile() {
        File file = TestUtil.SetupFile(TestUtil.FileType.TWEET);
        Map<String, User> map = null;
        try {
            map = UserFileParser.Read(file.getAbsolutePath());
        } catch (IOException e) {
            Assert.assertTrue(false);
        }
        file.delete();
        Assert.assertEquals(TestUtil.CORRECT_NUMBER_USERS, map.size());
    }
}