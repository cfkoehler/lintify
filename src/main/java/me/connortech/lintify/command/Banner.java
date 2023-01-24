package me.connortech.lintify.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Banner {

    static String DEFAULT_TEXT = "LINITFY";
    private final String bannerText;

    public Banner() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("banner.txt");
        if (in == null) {
            //TODO: Log error here. Or print boring text :(
            bannerText = DEFAULT_TEXT;
        } else {
            String bText = DEFAULT_TEXT;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) !=null) {
                    result.append(System.getProperty("line.separator")).append(line);
                }
                bText = System.getProperty("line.separator") + result.toString();
            } catch (IOException e) {
                // TODO: Handle Exception
            }
            bannerText = bText;
        }
    }

    public void dump() {
        System.out.println(bannerText);
    }

}
