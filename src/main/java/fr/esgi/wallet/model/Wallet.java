package fr.esgi.wallet.model;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class Wallet {

    private List<Stock> stocks;
    private final ExchangeRatesAPI exchangeRatesAPI;
    private String error;
    private final Utils utils;


    public Wallet() throws IOException, JSONException {
        this.exchangeRatesAPI = new ExchangeRatesAPI();
        this.utils = new Utils();
    }

    public List<Stock> stocks() { return this.stocks; }

    public void setStocks(List<Stock> stockList) {
        this.stocks = stockList;
    }

    private double setError(double rate, String currency) {
        if (rate == -1) {
            this.error = "Currency " + currency + " doesn't exist.";
            return 0;
        } else {
            this.error = "";
            return rate;
        }
    }

    public double total() {
        double total = 0;
        for (Stock stock: this.stocks()) {
            total += stock.value() * stock.share();
        }
        return this.utils.roundDouble(total);
    }

    public double convertToCurrency(String currency) {
        double currencyRate = this.exchangeRatesAPI.searchRate(currency);
        currencyRate = this.setError(currencyRate, currency);
        return this.utils.roundDouble(this.total() * currencyRate);
    }

    public String error() {
        return this.error;
    }
}
