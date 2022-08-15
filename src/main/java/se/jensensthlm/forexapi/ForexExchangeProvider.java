package se.jensensthlm.forexapi;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ForexExchangeProvider {
    private final String API_ENDPOINT_FORMAT = "https://api.forex.se/currency/exchangeRates/SWE-%s-%s";

    public ExchangeInfo get(String sourceCurrency, String targetCurrency) {
        var endpoint = buildEndpoint(sourceCurrency, targetCurrency);
        var jsonData = getJsonData(endpoint);
        var rate = getExchangeRateFromJsonData(jsonData);
        return new ExchangeInfo(sourceCurrency, targetCurrency, rate);
    }

    private String buildEndpoint(String sourceCurrency, String targetCurrency) {
        return API_ENDPOINT_FORMAT.formatted(sourceCurrency, targetCurrency);
    }

    private double getExchangeRateFromJsonData(String jsonData) {
        JSONObject data = new JSONObject(jsonData);
        return data
                .getJSONObject("data")
                .getJSONObject("attributes")
                .getDouble("rate");
    }

    private String getJsonData(String uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        try {
            var response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
            return response.body();
        } catch (Exception e) {
            return null;
        }
    }
}
