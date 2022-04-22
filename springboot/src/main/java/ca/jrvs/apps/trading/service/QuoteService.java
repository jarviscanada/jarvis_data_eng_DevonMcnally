package ca.jrvs.apps.trading.service;


import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.model.IexQuote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Transactional
@Service
public class QuoteService {


    private MarketDataDao marketDataDao;


    @Autowired
    public QuoteService(MarketDataDao marketDataDao){

        this.marketDataDao = marketDataDao;
    }

    public IexQuote findIexQuoteByTicker(String ticker) {

        return marketDataDao.findById(ticker).orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));

    }
}
