
package ca.jrvs.apps.trading.model;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class IexQuote {
    private Map<String, Stock> stocks = new LinkedHashMap<>();


    public IexQuote() {
    }

    @JsonAnyGetter
    public Map<String, Stock> getStocks() {
        return this.stocks;
    }

    @JsonAnySetter
    public void addStock(String name, Stock value) {
        this.stocks.put(name, value);
    }

    @Override
    public String toString() {
        return "IexQuote{" +
                "stocks=" + stocks +
                '}';
    }
}


