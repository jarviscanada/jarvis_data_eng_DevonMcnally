import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.MarketDataConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {


        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(50);
        MarketDataConfig marketDataConfig = new MarketDataConfig();
        marketDataConfig.setHost("https://cloud.iexapis.com/");
        marketDataConfig.setToken("pk_c4f43cd649364fc1b285855f13ce85fb");
        MarketDataDao dao = new MarketDataDao(cm, marketDataConfig);


        List<String> tickers = new ArrayList<String>();
        tickers.add("AMD");
        //tickers.add("AAPL");
        //tickers.add("COST");

        dao.findAllById(tickers);



    }
}
