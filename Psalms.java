/*
 * Terry Feng
 * Psalms.java
 * Daily tracker for Psalms Bible Reading plan for Dship
 * */

import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Psalms {
    /**
     * TODAY'S CHAPTER
     * This method will build an array of 150 days and then find today's date
     * to get today's chapter of psalms
     * @return today's psalmNumber
     * */
    public static int date() {

        // Dates as strings
        String startDate = "2020-06-17"; // Day 1
        String endDate = "2020-11-13"; // Day 150
        // Parse Dates
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        // Build array of 150 dates, 150 chapters
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }

        // Get today's date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        //System.out.println(dtf.format(localDate));

        // Find today's date in list
        int psalmNumber = totalDates.indexOf(LocalDate.parse(dtf.format(localDate))) + 1;

        // Output today's Psalm
        System.out.println("Today is " + dtf.format(localDate) + 
                ". Today's Psalm is Psalm " + psalmNumber + ".");

        return psalmNumber;
    }

    /**
     * GET TODAY'S CHAPTER JSONObject
     * This method will take today's chapter and then get today's passage
     * in KJV
     * Gets and parses a JSON object
     * @param chapter today's psalm chapter
     * @return chapter passage
     * */
    public static JSONObject getBibleObj(int chapter) {
        // URL, bible-api
        String bibleURL = "https://bible-api.com/psalms%20" + chapter + "?translation=kjv";
        StringBuilder sb = new StringBuilder();
        JSONObject response = null;

        try {
            // Get connection
            URL url = new URL(bibleURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            //System.out.println(url.toString());
            //System.out.println(connection.getResponseCode());

            // Build JSONText
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            //System.out.println(sb.toString());
            br.close(); // close reader

            // JSONText to JSONOBject
            JSONParser parser = new JSONParser();
            response = (JSONObject) parser.parse(sb.toString());

            //System.out.println(response.toString()); // Print JSON object
        } catch (Exception e) {
            // To debug
            //e.printStackTrace();
            System.out.println("Unfortunately, getting today's chapter of Psalms " 
                    + "failed. Please check your internet connection and then "
                    + " try again.");
        }
        return response;
    }


    /** 
     * PRINT TODAY'S CHAPTER
     * Takes a JSON object and outputs the verses, the chapter, and 
     * translation
     * @param json object of today's chapter
     * */
    public static void printPassage(JSONObject json) {
        // Just for good measure
        System.out.println("------------------------------");
        System.out.println(json.get("reference") + " KJV");

        // Go through all the verses
        JSONArray objs = (JSONArray) json.get("verses");
        for (int i = 0; i < objs.size(); i++) {
            JSONObject curr = (JSONObject) objs.get(i);

            System.out.println(curr.get("verse"));
            System.out.println(curr.get("text").toString().replaceAll("[\n]", " "));
            System.out.println("");
        }
    }

    /** Main
     * */
    public static void main(String args[]) {
        printPassage(getBibleObj(date())); // Get today's date and return today's psalm
    }
}
