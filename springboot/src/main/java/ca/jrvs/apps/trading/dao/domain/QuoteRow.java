package ca.jrvs.apps.trading.dao.domain;

public class QuoteRow implements Entity<String>{

    private String ticker;
    private Double lastPrice;
    private Double bidPrice;
    private Integer bidSize;
    private Double askPrice;
    private Integer askSize;



    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String s) {

    }
}