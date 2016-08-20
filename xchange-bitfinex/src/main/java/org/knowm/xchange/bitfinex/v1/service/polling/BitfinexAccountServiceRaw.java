package org.knowm.xchange.bitfinex.v1.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitfinex.v1.dto.BitfinexException;
import org.knowm.xchange.bitfinex.v1.dto.account.*;
import org.knowm.xchange.exceptions.ExchangeException;

import java.io.IOException;
import java.math.BigDecimal;

public class BitfinexAccountServiceRaw extends BitfinexBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitfinexAccountServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public BitfinexBalancesResponse[] getBitfinexAccountInfo() throws IOException {

    try {
      BitfinexBalancesResponse[] balances = bitfinex.balances(apiKey, payloadCreator, signatureCreator,
          new BitfinexBalancesRequest(String.valueOf(exchange.getNonceFactory().createValue())));
      return balances;
    } catch (BitfinexException e) {
      throw new ExchangeException(e);
    }
  }

  public BitfinexMarginInfosResponse[] getBitfinexMarginInfos() throws IOException {

    try {
      BitfinexMarginInfosResponse[] marginInfos = bitfinex.marginInfos(apiKey, payloadCreator, signatureCreator,
          new BitfinexMarginInfosRequest(String.valueOf(exchange.getNonceFactory().createValue())));
      return marginInfos;
    } catch (BitfinexException e) {
      throw new ExchangeException(e);
    }
  }

  public String withdraw(String withdrawType, String walletSelected, BigDecimal amount, String address) throws IOException {

    BitfinexWithdrawalResponse[] withdrawRepsonse = bitfinex.withdraw(apiKey, payloadCreator, signatureCreator,
        new BitfinexWithdrawalRequest(String.valueOf(exchange.getNonceFactory().createValue()), withdrawType, walletSelected, amount, address));
    return withdrawRepsonse[0].getWithdrawalId();
  }

  public BitfinexDepositAddressResponse requestDepositAddressRaw(String currency) throws IOException {
    try {
      String type = "unknown";
      if (currency.equalsIgnoreCase("BTC")) {
        type = "bitcoin";
      }else if (currency.equalsIgnoreCase("LTC")) {
        type = "litecoin";
      }else if (currency.equalsIgnoreCase("ETH")) {
        type = "ethereum";
      }

      BitfinexDepositAddressResponse requestDepositAddressResponse = bitfinex.requestDeposit(apiKey, payloadCreator, signatureCreator,
                      new BitfinexDepositAddressRequest(String.valueOf(exchange.getNonceFactory().createValue()), type, "exchange",0));
      if (requestDepositAddressResponse != null) {
        return requestDepositAddressResponse;
      }else{
        return null;
      }
    } catch (BitfinexException e) {
      throw new ExchangeException(e);
    }
  }


}
