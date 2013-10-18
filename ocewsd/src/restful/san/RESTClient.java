package restful.san;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
public class RESTClient {
    public static void main(String[] args) {
        try {
            URL twitter = new URL("http://twitter.com/statuses/public_timeline.xml");
                    URLConnection tc = twitter.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    tc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
