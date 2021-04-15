package com.ronfleming.affirmationbot;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.models.*;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.pagination.DefaultPaginator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BotMain {

    private String subreddit = "DailyAffirmationsBot";
    private String title = "";


    public static void main(String[] args) {

        String text = "";
        BotMain bot = new BotMain();
        //grabs an initial affirmation
        Affirmations newAffirmation = new Affirmations();
        String randomAffirmation = newAffirmation.getAffirmation();

        //keeps getting new affirmations until not repeated among latest 15 on subreddit
        while (newAffirmation.isAlreadySubmitted(bot.authenticate(), randomAffirmation)) {
            randomAffirmation = newAffirmation.getAffirmation();
        }

        //first implementation of logic for adding text field to self post
        if (!randomAffirmation.contains("should-ing") && randomAffirmation.contains("should")) {
            text = "Here I go again, should-ing all over myself!";
        }

        //posts affirmation
        try {
            bot.authenticate()
                    .subreddit("DailyAffirmationsBot")
                    .submit(SubmissionKind.SELF, randomAffirmation, text, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);

    }

    public static RedditClient authenticate() {
        // Set up authentication and client object with JRAW
        File configFile = new File("bot/.config");

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
