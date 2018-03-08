package za.co.andre.assignment.model;

import java.util.LinkedList;
import java.util.List;

/**
 * The data class to hold the user data
 *
 * @author Andre Labuschagne
 */
public class User implements Comparable {
    private String name;
    private List<User> following;
    private List<User> followedBy;

    /**
     * Easy constructor
     *
     * @param name the user name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Get the name
     *
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the user name
     *
     * @param name user name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the following list
     *
     * @return the following list never null
     */
    public List<User> getFollowing() {
        if (following == null) {
            following = new LinkedList<>();
        }
        return following;
    }

    /**
     * Set the following list
     *
     * @param following the following
     */
    public void setFollowing(List<User> following) {
        this.following = following;
    }

    /**
     * Use the name to generate the has code
     *
     * @return
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Use the name to evaluate
     *
     * @param obj
     * @return true if the name is the same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return name.equals(((User) obj).name);
        }
        return false;
    }

    /**
     * Use the name to compare
     *
     * @param obj
     * @return
     */
    @Override
    public int compareTo(Object obj) {
        if (obj instanceof User) {
            return name.toLowerCase().compareTo(((User) obj).name.toLowerCase());
        }
        return -1;
    }

    /**
     * Simple toString
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Get the list of ppl this user is followed by
     *
     * @return followed by list never null
     */
    public List<User> getFollowedBy() {
        if (followedBy == null) {
            followedBy = new LinkedList<>();
        }
        return followedBy;
    }

    /**
     * Set the followed by list
     *
     * @param followedBy
     */
    public void setFollowedBy(List<User> followedBy) {
        this.followedBy = followedBy;
    }
}
