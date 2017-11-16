package collections;

/**
 * References:
 * https://stackoverflow.com/questions/3283234/http-basic-authentication-in-java-using-httpclient
 * https://www.mkyong.com/java/apache-httpclient-examples/
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class GetOpenCollectionIndex {

    public static void main(String[] args) {

        HttpClient httpclient = HttpClientBuilder.create().build();

        Encoder encoder = Base64.getEncoder();
        String encoding = encoder.encodeToString(("4e49de80-1670-4606-84f8-2f1d33a38670:").getBytes());

        HttpGet httpget = new HttpGet("https://billplz-staging.herokuapp.com/api/v4/open_collections");
        httpget.setHeader("Authorization", "Basic " + encoding);

        System.out.println("executing request " + httpget.getRequestLine());
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException ex) {
            Logger.getLogger(GetOpenCollectionIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpEntity entity = response.getEntity();
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));

            String line = null;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.out.println("IOException Happen");
        } catch (UnsupportedOperationException ex) {
            System.out.println("UnsupportedOperationException Happen");
        }

    }

}