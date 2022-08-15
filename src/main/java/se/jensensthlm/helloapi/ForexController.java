package se.jensensthlm.helloapi;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/exchange-rate")
public class ForexController {

    // forexService

    @GetMapping("{sourceCurrency}/{targetCurrency}")
    public double getExchangeRate(@PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
        return 0.0;
    }
}
