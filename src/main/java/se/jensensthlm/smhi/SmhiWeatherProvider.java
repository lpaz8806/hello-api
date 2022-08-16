package se.jensensthlm.smhi;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SmhiWeatherProvider {
    private final String API_ENDPOINT_FORMAT = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&hourly=temperature_2m,relativehumidity_2m,windspeed_10m";

    public WeatherInfo get(String latitude, String longitude) {
        var endpoint = buildEndpoint(latitude, longitude);
        var jsonData = getJsonData(endpoint);
        var weather = getWeatherFromJsonData(jsonData);
        return new WeatherInfo(latitude, longitude);
    }
    private String buildEndpoint(String latitude, String longitude) {
        return API_ENDPOINT_FORMAT.formatted(latitude, longitude);
    }
    private JSONObject getWeatherFromJsonData(String jsonData) {
        JSONObject data = new JSONObject(jsonData);
        return data
                .getJSONObject("temperature_2m")
                .getJSONObject("time");
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
