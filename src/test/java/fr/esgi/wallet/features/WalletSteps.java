package fr.esgi.wallet.features;

import fr.esgi.wallet.model.Stock;
import fr.esgi.wallet.model.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WalletSteps {

    private final FeatureContext featureContext;
    private List<Stock> stocks;
    private double total;
    private final Utils utils;

    public WalletSteps(final FeatureContext featureContext) {
        this.featureContext = featureContext;
        this.utils = new Utils();
    }

    private List<Stock> readDataTable(DataTable table) {
        ArrayList<Stock> stockArrayList = new ArrayList<Stock>();
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            stockArrayList.add(new Stock(columns.get("type"), Double.parseDouble(columns.get("value")), Double.parseDouble(columns.get("share"))));
        }
        return stockArrayList;
    }

    @Given("^a wallet is containing Stocks$")
    public void aWalletContainingStocks(DataTable table) {
        this.setStocks(this.readDataTable(table));
        this.stocks = this.featureContext.stocks();
    }

    private void setStocks(List<Stock> s) {
        this.featureContext.setStocks(s);
    }

    // And
    @Given("exchange rates are from external API")
    public void exchangeRatesAreFromExternalAPI() {
        // Rates retrieved in Wallet class via ExchangeRatesAPI class
    }

    // And
    @Given("the {string} rate from external API does not exist")
    public void theCurrencyRateDoesNotExist(String currency) {
        // Error is setted from Wallet.convertToCurrency()
    }

    @When("the total is calculating in {string}")
    public void totalIsCalculating(String currency) {
        this.featureContext.wallet().convertToCurrency(currency);
    }

    @When("EUR is converted to {string}")
    public void walletIsConvertedToUsingRate(String currency){
        this.total = this.featureContext.wallet().convertToCurrency(currency);
    }


    @Then("the total should be {float}")
    public void theTotalShouldBeValue(double res) {
        res = this.utils.roundDouble(res);
        Assertions.assertThat(this.featureContext.wallet().total()).isEqualTo(res);
    }

    @Then("the {string} rate should be {float}")
    public void theCurrencyRateShouldBe(String currency, double rate) {
        rate = this.utils.roundDouble(rate);
        Assertions.assertThat(this.featureContext.exchangeRate(currency)).isEqualTo(rate);
    }

    // And
    @Then("the value should be {float}")
    public void theValueShouldBe(double res) {
        res = this.utils.roundDouble(res);
        Assertions.assertThat(this.total).isEqualTo(res);
    }

    @Then("error should be {string}")
    public void errorShouldBe(String error) {
        Assertions.assertThat(this.featureContext.wallet().error()).isEqualTo(error);
    }


}
