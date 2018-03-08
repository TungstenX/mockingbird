package za.co.andre.assignment.util;

/**
 * A quick util class, we don't want to include the whole of commons-lang3 just for 1 or 2 functions
 * @author Andre Labuschagne
 */
public class Util {
    /**
     * Check if the string is null or empty
     * @param str the string to check
     * @return true is when the string is bull or empty
     */
    public static boolean isBlank(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    /**
     * Check if the sring is not null and not empty
     * @param str the string to check
     * @return true if the string is not null and not empty
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
