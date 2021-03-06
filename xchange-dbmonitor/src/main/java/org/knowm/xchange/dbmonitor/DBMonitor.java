package org.knowm.xchange.dbmonitor;

import org.knowm.xchange.btc38.Btc38Exchange;
import org.knowm.xchange.btc38.service.polling.Btc38MarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

public class DBMonitor {

  public static void main(String[] args) throws Exception {

    Btc38Exchange exchange = new Btc38Exchange();
    exchange.applySpecification(exchange.getDefaultExchangeSpecification());
    Btc38MarketDataService marketDataService = (Btc38MarketDataService) exchange.getPollingMarketDataService();
    Ticker ticker = marketDataService.getTicker(new CurrencyPair("DOGE", "BTC"));
    System.out.println(ticker.toString());
  }
}
