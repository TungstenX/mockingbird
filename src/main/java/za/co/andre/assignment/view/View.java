package za.co.andre.assignment.view;

import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Creates the output
 *
 * @author Andre Labuschagne
 */
public class View {

    public static String BuildOutput(Map<String, User> users, List<Tweet> tweets) {
        Map<User, List<Tweet>> output = new TreeMap<>();
        for (String name : users.keySet()) {
            output.put(users.get(name), new LinkedList<>());
        }
        for (Tweet tweet : tweets) {
            User author = tweet.getAuthor();
            AddToUser(output, author, tweet);
            List<User> followers = author.getFollowedBy();
            for (User fUser : followers) {
                AddToUser(output, fUser, tweet);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (User user : output.keySet()) {
            sb.append(user.getName()).append("\n");
            for (Tweet tweet : output.get(user)) {
                sb.append(tweet);
            }
        }
        return sb.toString();
    }

    private static void AddToUser(Map<User, List<Tweet>> output, User user, Tweet tweet) {
        List<Tweet> tweetPerUser = new LinkedList<>();
        if (output.containsKey(user)) {
            tweetPerUser = output.get(user);
        } else {
            //Double check, maybe the user is only in the tweet list
            output.put(user, tweetPerUser);
        }
        tweetPerUser.add(tweet);
    }
}
