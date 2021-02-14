package fr.esgi.wallet.features;

import fr.esgi.wallet.model.ExchangeRatesAPI;
import fr.esgi.wallet.model.Stock;
import fr.esgi.wallet.model.Wallet;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class FeatureContext {

    private Wallet wallet = new Wallet();
    private ExchangeRatesAPI exchangeRatesAPI = new ExchangeRatesAPI();

    public FeatureContext() throws IOException, JSONException {}

    public void setStocks(List<Stock> stocks) {
        this.wallet.setStocks(stocks);
    }

    public double exchangeRate(String currency) {
        return this.exchangeRatesAPI.searchRate(currency);
    }

    public Wallet wallet() { return this.wallet; }

    public List<Stock> stocks() { return this.wallet.stocks(); }
}
