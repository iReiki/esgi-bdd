package fr.esgi.wallet.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ExchangeRatesAPI {

    private final String defaultCurrency = "EUR";
    private final String url = "https://api.exchangeratesapi.io/2021-02-01?base=EUR";
    private final JSONObject rates;
    private final Utils utils;

    public ExchangeRatesAPI() throws IOException, JSONException {
        this.rates = this.readJsonFromUrl().getJSONObject("rates");
        this.utils = new Utils();
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl() throws IOException, JSONException {
        try (InputStream is = new URL(this.url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = this.readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public double searchRate(String rateName) {
        if (rateName.equals(this.defaultCurrency)) { return 1; }
        try {
            return this.utils.roundDouble(this.rates.getDouble(rateName));
        } catch (JSONException e) {
            return -1;
        }
    }
}
