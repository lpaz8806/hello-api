package se.jensensthlm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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