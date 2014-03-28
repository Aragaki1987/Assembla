package com.san.calculator.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TeamClient {
    private static final String endpoint = "http://localhost:9999/team";
    public static void main(String[] args) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(endpoint);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String next = null;

            while((next = inputStream.readLine()) != null) {
                System.out.println(next);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
