package com.ronfleming.affirmationbot;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BotMain {

    private String subreddit = "DailyAffirmationsBot";
    private String title = "";
    private String text = "";


    public static void main(String[] args) {

        Affirmations newAffirmation = new Affirmations();
        String randomAffirmation = newAffirmation.getAffirmation();


        try {
            authenticate()
                    .subreddit("DailyAffirmationsBot")
                    .submit(SubmissionKind.SELF,randomAffirmation,"", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);

    }
    public static RedditClient authenticate() {
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
        return reddit;
    }

}
