package ca.jrvs.apps.trading.model;

public class Stock {


    private Quote quote;

    public Stock() {
    }

    public Quote getQuote() {
        return this.quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;

    }

    @Override
    public String toString() {
        return "Stock{" +
                "quote=" + quote +
                '}';
    }
}
