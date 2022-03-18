import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.PercentEscaper;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.Arrays;

public class TwitterApiTest {

    private static String   CONSUMER_KEY  = "BTThQmTPeTLbDqmwAUv8keYw6";
    private static String CONSUMER_SECRET =  "V0pDtE8sVBcUCGeoTyflzmCk8FiCwtI7kGs6MK6gIAepp9S5ZX";
    private static String ACCESS_TOKEN = "998592048474226689-GWShYN3PgBOnaebxfd6IEK7LjbfceFI";
    private static String TOKEN_SECRET = "q0zsUiFB7X9tZuCZVaxOzwJuUkZVuu3eBHufAiILq6UCs";

    public static void main(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {


        OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY,
                CONSUMER_SECRET);
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        String status = "Using twitter the hard way";

        PercentEscaper percentEscaper = new PercentEscaper("", false);
        HttpPost request = new HttpPost("https://api.twitter.com/2/statuses/update.json?status=" +percentEscaper.escape(status));

        consumer.sign(request);

        System.out.println("Http Request Headers:");
        Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

}
