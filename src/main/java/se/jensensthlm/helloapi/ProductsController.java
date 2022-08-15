package se.jensensthlm.helloapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductsController {

    @GetMapping
    public List<String> getAllProducts() {
        return List.of("P1", "P2", "P3");
    }
}
