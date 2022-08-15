package se.jensensthlm.helloapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HelloApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloApiApplication.class, args);
    }
}


// GET api/v1/exchange-rate
// GET api/v1/products
// GET api/v1/products/123

// GET localhost:8080