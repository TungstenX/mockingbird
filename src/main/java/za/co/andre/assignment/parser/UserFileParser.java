package za.co.andre.assignment.parser;

import za.co.andre.assignment.model.User;
import za.co.andre.assignment.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class UserFileParser {

    private static final Logger LOG = Logger.getLogger(UserFileParser.class.getName());

    public static Map<String, User> Read(String fileName) {
        if(Util.isBlank(fileName)) {
            throw new IllegalArgumentException("File name is required");
        }
        final Map<String, User> map = new TreeMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line -> ProcessLine(map, line));

        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error while reading file {0}: {1}", new Object[]{fileName, e.toString()});
        }
        return map;
    }

    private static void ProcessLine(Map<String, User> map, String in) {
        if(Util.isBlank(in)) {
            LOG.log(Level.WARNING, "Input line not cool: {0}", in);
            return; //skipping empty lines
        }
        String[] parts = in.split("follows");
        if(parts.length != 2) {
            throw new IllegalArgumentException("The following input line is not correct: " + in);
        } else if(Util.isBlank(parts[0])) {
            throw new IllegalArgumentException("The following input line is not correct: " + in);
        }
        String[] following = parts[1].split(",");

        if(following.length == 0) {
            throw new IllegalArgumentException("The following input line is not correct: " + in);
        }

        User user = map.get(parts[0].trim().toLowerCase());
        if(user == null) {
            user = new User(parts[0].trim());
            map.put(parts[0].trim().toLowerCase(), user);
        }
        for(String follower: following) {
            ProcessFollowing(map, user, follower);
        }
    }

    private static void ProcessFollowing(Map<String, User> map, User user, String in) {
        //check in map
        User follower = map.get(in.trim().toLowerCase());
        boolean skipUserCheck = false;
        if(follower == null) {
            follower = new User(in.trim());
            map.put(in.trim().toLowerCase(), follower);
            skipUserCheck = true;
        }
        if(!skipUserCheck) {
            //if in list then skip
            User uf = user.getFollowing().stream()
                    .filter(follow -> in.trim().equals(follow.getName()))
                    .findAny()
                    .orElse(null);
            if(uf != null) {
                LOG.log(Level.INFO, "{0} is already has {1} as a follower", new Object[]{user.getName(), in.trim()});
                return;
            }
        }
        user.getFollowing().add(follower);
        follower.getFollowedBy().add(user);
    }

}
