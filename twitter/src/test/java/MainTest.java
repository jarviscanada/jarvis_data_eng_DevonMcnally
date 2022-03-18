import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.TweetBuilder;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainTest {

    private static String   CONSUMER_KEY  = "BTThQmTPeTLbDqmwAUv8keYw6";
    private static String CONSUMER_SECRET =  "V0pDtE8sVBcUCGeoTyflzmCk8FiCwtI7kGs6MK6gIAepp9S5ZX";
    private static String ACCESS_TOKEN = "998592048474226689-GWShYN3PgBOnaebxfd6IEK7LjbfceFI";
    private static String TOKEN_SECRET = "q0zsUiFB7X9tZuCZVaxOzwJuUkZVuu3eBHufAiILq6UCs";




    public static void main(String[] args) throws URISyntaxException, IOException, JSONException {

        Tweet t = new TweetBuilder().text("Making tweets the hard way").coordinates(23, 87).build();
        TwitterDao dao = new TwitterDao();
        //dao.create(t);
        //dao.findById("20");
        //dao.deleteById("1504191964765466628");

        Controller controller = new TwitterController();
        String[] strings = new String[]{"post", "Test of apps controller model", "75:89"};
        controller.postTweet(strings);


    }
}
