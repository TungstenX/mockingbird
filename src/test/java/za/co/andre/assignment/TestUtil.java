package za.co.andre.assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestUtil {
    private static final Logger LOG = Logger.getLogger(TestUtil.class.toString());

    private static String USER_LIST = "Ward follows Alan\n" +
            "Alan follows Martin\n" +
            "Ward follows Martin, Alan";

    private static String TWEETS = "Alan> If you have a procedure with 10 parameters, you probably missed some.\n" +
            "Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.\n" +
            "Alan> Random numbers should not be generated with a method chosen at random.\n";

    private static final String USER_FILE = "user.txt";
    private static final String TWEET_FILE = "tweet.txt";

    public enum FileType {
        USER(USER_FILE, USER_LIST),
        TWEET(TWEET_FILE, TWEETS);
        private String fileName;
        private String data;

        FileType(String fileName, String data) {
            this.fileName = fileName;
            this.data = data;
        }
    }

    public static File SetupFile(FileType fileType) {
        try {
            File file = File.createTempFile(fileType.fileName, "");
            file.delete();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()))) {
                bw.write(fileType.data);
                return file;
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Could not write to tmp file: {0}", e.toString());
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Could not create tmp file: {0}", e.toString());
        }
        return null;
    }
}