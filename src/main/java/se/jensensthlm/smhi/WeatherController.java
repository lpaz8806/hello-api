package se.jensensthlm.smhi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class WeatherController {
    private SmhiWeatherProvider weatherProvider = new SmhiWeatherProvider();

    @GetMapping
    public ResponseEntity<WeatherInfo> weather(@RequestBody WeatherInputDto input) {
        var info = weatherProvider.get(input.latitude(), input.longitude());
        return ResponseEntity.ok().body(info);
    }
}
