package com.ronfleming.affirmationbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

        readInComponents();

    }

    private void readInComponents () {
        File openingFile = new File ("opening");
        File negMiddleFile = new File ("negMiddle");
        File posMiddleFile = new File ("posMiddle");
        File closingFile = new File ("closing");

        try (Scanner openingStream = new Scanner(openingFile)) {
            while (openingStream.hasNext()) {
                String line = openingStream.nextLine();
                String[] openingMapLine = line.split("\\| ");
                String key = openingMapLine[0];
                String value = openingMapLine[1];
                opening.put(key, value);
           }
        } catch (FileNotFoundException e) {
            System.out.println("Opening file not found");
        }

        try (Scanner negMiddleStream = new Scanner(negMiddleFile)) {
            while (negMiddleStream.hasNext()) {
                String line = negMiddleStream.nextLine();
                negMiddle.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("negMiddle file not found");
        }

        try (Scanner posMiddleStream = new Scanner(posMiddleFile)) {
            while (posMiddleStream.hasNext()) {
                String line = posMiddleStream.nextLine();
                posMiddle.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("posMiddle file not found");
        }

        try (Scanner closingStream = new Scanner(closingFile)) {
            while (closingStream.hasNext()) {
                String line = closingStream.nextLine();
                closing.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("posMiddle file not found");
        }
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
        if(oPosNeg.equals("pos")) {
            randMiddleIndex = (int) (Math.random() * (posMiddle.size()));
            randMiddle = posMiddle.get(randMiddleIndex);
        } else if (oPosNeg.equals("neg")) {
            randMiddleIndex = (int) (Math.random() * (negMiddle.size()));
            randMiddle = negMiddle.get(randMiddleIndex);
        }

        String randClosing = "";
        if(!oPosNeg.equals("standalone")) {
            int randClosingIndex = (int) Math.random() * (closing.size());
            randClosing = closing.get(randClosingIndex);
        }

        affirmation = randOpening + " " + randMiddle + " " + randClosing;

        return affirmation;
    }

}