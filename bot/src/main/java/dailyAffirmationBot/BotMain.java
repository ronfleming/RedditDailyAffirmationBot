package dailyAffirmationBot;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.ApiException;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.models.*;
import net.dean.jraw.references.SubmissionReference;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class BotMain {

    private String subreddit = "DailyAffirmationsBot";
    private String title = "";
    private String text = "";


    public static void main(String[] args) {

        // Set up authentication and client object with JRAW
        File configFile = new File(".config");

        List<String> config = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(configFile)) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                config.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        UserAgent userAgent = new UserAgent("bot", "RedditDailyAffirmationBot",
                "v0.1", "DailyAffirmationBot");
        Credentials credentials = Credentials.script("DailyAffirmationsBot",
                config.get(0), config.get(1), config.get(2));
        NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);
        RedditClient reddit = OAuthHelper.automatic(adapter, credentials);

        Affirmations newAffirmation = new Affirmations();
        String randomAffirmation = newAffirmation.getAffirmation();


        try {
            reddit.subreddit("DailyAffirmationsBot").submit(SubmissionKind.SELF,randomAffirmation,"", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);

        /*Affirmations affirmation = new Affirmations();
        System.out.println(affirmation.getAffirmation());*/


/*        DefaultPaginator<Submission> frontPage = reddit.frontPage()
                .sorting(SubredditSort.TOP)
                .timePeriod(TimePeriod.DAY)
                .limit(30)
                .build();

        Listing<Submission> submissions = frontPage.next();
        for (Submission s : submissions) {
            System.out.println(s.getTitle());
        }
        System.exit(0);*/


        // Grab some Imgur links from various subreddits
/*        DefaultPaginator<Submission> earthPorn = reddit.subreddits("EarthPorn",
                "spaceporn").posts().build();

        List<String> images = new ArrayList<String>();
        for (Submission s : earthPorn.next()) {
            if (!s.isSelfPost() && s.getUrl().contains("i.imgur.com")) {
                images.add(s.getUrl());
                System.out.println(s.getUrl());
            }
        }
        System.exit(0);*/

       // Query self
        /*AccountQuery me = reddit.me().query();
        System.out.println(me);*/




    }
}
