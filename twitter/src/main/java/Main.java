import ru.ctddev.ifmo.filippov.twitter.twitter.*;

/**
 * Created by dmitry on 07.10.16.
 */
public class Main {
    public static void main(String[] args) {
        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.out.println("Usage: java Main <hashtag> <number of hours>");
            return;
        }
        String hashtag = args[0];
        int hours = Integer.parseInt(args[1]);
        TwitterClient client = new TwitterClient("api.twitter.com", "1.1");
        StatisticsManager manager = new StatisticsManager(client);
        int[] result = manager.getCommentsStatistics(hashtag, hours);
        assert (result != null && result.length == hours) : String.format("We should find comments statistics for each of %d hours", hours);
        for (int i = 0; i < result.length; i++) {
            System.out.println(String.format("Number of tweets between %d and %d hours ago: %d", i, i + 1, result[i]));
        }
    }
}
