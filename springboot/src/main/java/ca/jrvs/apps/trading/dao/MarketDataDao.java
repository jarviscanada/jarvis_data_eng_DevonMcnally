package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.IexQuote;
import ca.jrvs.apps.trading.model.MarketDataConfig;
import ca.jrvs.apps.trading.util.JsonToObject;
import com.google.common.collect.Iterables;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MarketDataDao implements CrudRepository<IexQuote, String> {

    //https://cloud.iexapis.com/stable/stock/market/batch?symbols=AMD, AAPL&types=quote&token=pk_c4f43cd649364fc1b285855f13ce85fb
    private String IEX_BATCH_PATH = "stable/stock/market/batch?symbols=%s&types=quote&token=";
    private String IEX_BATCH_URL = "IEX_BATCH_URL";

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    private JsonToObject jsonToObject = new JsonToObject();

    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig){
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH +marketDataConfig.getToken();

    }

    @Override
    public <S extends IexQuote> S save(S s) {



        return null;
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;

        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if(quotes.size() == 0){
            return Optional.empty();
        }
        else if (quotes.size() == 1){
            iexQuote = Optional.of(quotes.get(0));
        }
        else{
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }


        return iexQuote;
    }


    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {

        StringBuilder formattedTickers = new StringBuilder("");

        System.out.println(Iterables.size(tickers));

        for (String ticker : tickers) {
            tickers.iterator().next();
            if (!tickers.iterator().hasNext()) {
                formattedTickers.append(ticker);
            } else {
                formattedTickers.append(ticker + ",");
            }

        }
        formattedTickers.deleteCharAt(formattedTickers.length() - 1);

        URI uri = URI.create(String.format(IEX_BATCH_URL, formattedTickers));

        CloseableHttpClient client =
                HttpClients.custom().setConnectionManager(httpClientConnectionManager).build();

        HttpResponse response = null;
        try {
            response = client.execute(new HttpGet(uri));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String reposeBody = null;
        try {
            reposeBody = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(reposeBody);
        try {
            List<IexQuote> quotes = jsonToObject.jsonToQuote(reposeBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(quotes);


        //return jsonToObject.jsonToQuote(reposeBody);
        return null;
    }


    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public Iterable<IexQuote> findAll() {
        return null;
    }



    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(IexQuote iexQuote) {

    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
