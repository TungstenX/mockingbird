package za.co.andre.assignment;

import za.co.andre.assignment.model.Tweet;
import za.co.andre.assignment.model.User;
import za.co.andre.assignment.view.View;
import za.co.andre.assignment.parser.TweetFileParser;
import za.co.andre.assignment.parser.UserFileParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This is the assignment. It will read in two files and produce the desired output
 *
 * @author Andre Labuschagne
 */
public class MockingBird {

    public static Logger LOG;
    static {
        InputStream stream = MockingBird.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            LOG = Logger.getLogger(MockingBird.class.getName());
        } catch (IOException e) {
            System.err.println("Error while setting up logger: " + e.toString());
        }
    }
    /**
     * Entry point
     * @param args
     */
    public static void main(String[] args) {
        if (args.length <= 1) {
            PrintUsage();
            System.exit(-1);
        }
        try {
            Map<String, User> map = UserFileParser.Read(args[0]);
            List<Tweet> list = TweetFileParser.Read(map, args[1]);
            PrintResult(View.BuildOutput(map, list));
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Error reading file(s): {0}", e.toString());
        }
    }

    /**
     * Prints out the result of the view
     * @param str the formatted data from the view
     */
    private static void PrintResult(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("\u2523");
        String title = "\u252B Tweets \u2523";
        for (int i = 0; i < (78 - title.length()) / 2; i++) {
            sb.append("\u2501");
        }
        sb.append(title);
        for (int i = 0; i < (78 - title.length()) / 2; i++) {
            sb.append("\u2501");
        }
        sb.append("\u252B\n");
        sb.append(str);
        System.out.println(sb);
    }

    /**
     * Prints out the usage for this application
     */
    private static void PrintUsage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\u2552");
        for (int i = 0; i < 78; i++) {
            sb.append("\u2550");
        }
        sb.append("\u2555\n");
        AddLine(sb, "Usage:");
        AddLine(sb, "======");
        AddLine(sb, "    java -jar mockingbird </path/to/user/file.txt> </path/to/tweet/file.txt>");

        sb.append("\u2558");
        for (int i = 0; i < 78; i++) {
            sb.append("\u2550");
        }
        sb.append("\u255B\n");
        System.out.println(sb.toString());
    }

    /**
     * Helper: Makes output line for usage pretty
     * @param sb the created string builder (IN/OUT)
     * @param str the new text to add to {@code sb}
     */
    private static void AddLine(StringBuilder sb, String str) {
        sb.append("\u2502 ").append(str);
        for (int i = 0; i < (76 - str.length()); i++) {
            sb.append(" ");
        }
        sb.append(" \u2502\n");
    }
}
