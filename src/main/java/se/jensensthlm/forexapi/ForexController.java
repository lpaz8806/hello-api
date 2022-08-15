package se.jensensthlm.forexapi;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange-rate/")
public class ForexController {
    private ForexExchangeProvider exchangeProvider = new ForexExchangeProvider();

    // implement here the exchange endpoint
    // feel free to use the convention you want
    // e.g.

    // api/v1/exchange-rate/usd/sek

    // api/v1/exchange-rate
    /*
        { source: "SEK", target: "USD" }
    */
}
