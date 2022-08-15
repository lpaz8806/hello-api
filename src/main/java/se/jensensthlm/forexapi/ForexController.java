package se.jensensthlm.forexapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange-rate")
public class ForexController {
    private ForexExchangeProvider exchangeProvider = new ForexExchangeProvider();

    @GetMapping
    public ResponseEntity<ExchangeInfo> exchange(@RequestBody ForexInputDto input) {
        var info = exchangeProvider.get(input.sourceCurrency(), input.targetCurrency());
        return ResponseEntity.ok().body(info);
    }

    // implement here the exchange endpoint
    // feel free to use the convention you want
    // e.g.

    // api/v1/exchange-rate/usd/sek

    // api/v1/exchange-rate
    /*
        { source: "SEK", target: "USD" }
    */
}
