package ca.jrvs.apps.trading.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Map;

class Root {
    public Map<String, Stock> stocks = new LinkedHashMap<>();

    public Root() {
        super();
    }


    public Root(Map<String, Stock> stocks) {
        this.stocks = stocks;
    }

    @JsonAnyGetter
    public Map<String, Stock> getStocks() {
        return this.stocks;
    }

    @JsonAnySetter
    public void addStock(String name, Stock value) {
        this.stocks.put(name, value);
    }
}
