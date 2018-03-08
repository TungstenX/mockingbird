package za.co.andre.assignment.parser;

import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;
import za.co.andre.assignment.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Parse the tweet data. These are all static methods as there is no state to keep.
 *
 * @author Andre Labuschagne
 */
public class TweetFileParser {

    private static final Logger LOG = Logger.getLogger(TweetFileParser.class.getName());

    /**
     * Read the data from the file
     * @param map The map of users
     * @param fileName The file name to read
     * @return A list of tweets
     */
    public static List<Tweet> Read(Map<String, User> map, String fileName) {
        if(map == null) {
            throw new IllegalArgumentException("A map is required");
        }
        if(Util.isBlank(fileName)) {
            throw new IllegalArgumentException("File name is required");
        }
        final List<Tweet> list = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> ProcessLine(map, list, line));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error while reading file {0}: {1}", new Object[]{fileName, e.toString()});
        }
        return list;
    }

    private static void ProcessLine(Map<String, User> map, List<Tweet> list, String in) {
        if(Util.isBlank(in)) {
            LOG.log(Level.WARNING, "Input line not cool: {0}", in);
            return; //skipping empty lines
        }
        String[] parts = in.split(">");
        if(parts.length != 2) {
            throw new IllegalArgumentException("The following input line is not correct: " + in);
        } else if(Util.isBlank(parts[0])) {
            throw new IllegalArgumentException("The following input line is not correct: " + in);
        }
        User user = map.get(parts[0].trim().toLowerCase());
        if(user == null) {
            LOG.log(Level.WARNING, "User not found for line: {0}", in);
            return; //skip if user was not found
        }
        Tweet tweet = new Tweet();
        tweet.setAuthor(user);
        tweet.setData(parts[1]);
        list.add(tweet);
    }
}
