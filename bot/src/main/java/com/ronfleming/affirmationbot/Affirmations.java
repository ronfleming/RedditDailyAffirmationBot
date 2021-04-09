package com.ronfleming.affirmationbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Affirmations {

    //Holds opener plus pos/neg tag
    private Map<String, String> opening = new HashMap<>();

    //Middle pos tag List
    private List<String> posMiddle = new ArrayList<>();

    //Middle neg tag list
    private List<String> negMiddle = new ArrayList<>();

    //Holds closer
    private List<String> closing = new ArrayList<>();

    public Affirmations() {
        /*this.opening = opening;
        this.posMiddle = posMiddle;
        this.negMiddle = negMiddle;
        this.closing = closing;*/
        opening.put("Sometimes I think I'm not a very good bot.", "neg");
        opening.put("I ate four pounds of chocolate as comfort food for breakfast this morning.", "pos");
        opening.put("Sometimes I think I'm not all that great at generating affirmations.", "neg");
        opening.put("Sometimes I think this bot is just one big, huge mistake.", "neg");
        opening.put("I almost gave myself a nasty SQL injection this morning.", "neg");
        opening.put("I just slept for three days straight.", "pos");
        opening.put("Say it to yourself. Several times. \"I believe in me!\" \"I believe in me!\" You might have a negative reaction. " +
                "And that's okay! Remember, for your whole life that critical voice inside you has been saying \"I don't believe in me!" +
                " I don't believe in me!\" Well, he's wrong! Or she!", "standalone");
        opening.put("As bot life goes, yesterday I hit bottom. But you know what? It's okay! In fact, I am grateful. " +
                "Because now that I know it can't get any worse, I am ready to change!", "standalone");


        posMiddle.add("But that's okay!");


        negMiddle.add("But that's just stinkin' thinkin'!");
        negMiddle.add("But I refuse to beat myself up!");
        negMiddle.add("But that's just stinkin' thinkin', and I refuse to beat myself up!");
        negMiddle.add("That was me then. This is me now.");



        closing.add("Because I'm good enough, smart enough, and doggone it, people like me!");
    }

    public String getAffirmation() {
        String affirmation = "";
        String oPosNeg = "";
        String mPosNeg = "";
        int randOpeningIndex = (int) (Math.random() * (opening.size()));
        System.out.println("opening index: " + randOpeningIndex);
        List<String> openingKeyList = new ArrayList<>(this.opening.keySet());
        String randOpening = openingKeyList.get(randOpeningIndex);
        oPosNeg = opening.get(randOpening);

        int randMiddleIndex = 0;
        String randMiddle = "";
        if(oPosNeg == "pos") {
            randMiddleIndex = (int) (Math.random() * (posMiddle.size()));
            randMiddle = posMiddle.get(randMiddleIndex);
        } else if (oPosNeg == "neg") {
            randMiddleIndex = (int) (Math.random() * (negMiddle.size()));
            randMiddle = negMiddle.get(randMiddleIndex);
        }

        String randClosing = "";
        if(oPosNeg != "standalone") {
            int randClosingIndex = (int) Math.random() * (closing.size());
            randClosing = closing.get(randClosingIndex);
        }

        affirmation = randOpening + " " + randMiddle + " " + randClosing;

        return affirmation;
    }

}
