package se.jensensthlm.helloapi;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/math")
public class MathController {
    @GetMapping
    public String hello() {
        return "Hello Math";
    }

    @GetMapping("operations")
    public List<String> operationsSupported() {
        return List.of("+", "-", "*", "/");
    }

    @GetMapping("operation")
    public ResponseEntity<String> operation2(@RequestBody OperationInputDto input) {
        var op = input.op();
        var x = input.x();
        var y = input.y();

        return switch (op) {
            case "+" -> getAdd(x, y);
            case "-" -> getSub(x, y);
            case "*" -> getMul(x, y);
            case "/" -> getDiv(x, y);
            default -> ResponseEntity.badRequest().body("%s is not a supported operation".formatted(op));
        };
    }

    @GetMapping("operation-naive")
    public ResponseEntity<String> operation(@RequestBody String input) {
        JSONObject data = new JSONObject(input);

        var op = data.getString("op");
        var x = data.getDouble("x");
        var y = data.getDouble("y");

        return switch (op) {
            case "+" -> getAdd(x, y);
            case "-" -> getSub(x, y);
            case "*" -> getMul(x, y);
            case "/" -> getDiv(x, y);
            default -> ResponseEntity.badRequest().body("%s is not a supported operation".formatted(op));
        };
    }

    @GetMapping("add/{fooX}/{fooY}")
    public ResponseEntity<String> getAdd(@PathVariable("fooX") double x, @PathVariable("fooY") double y) {
        return ResponseEntity.ok().body(Double.toString(x+y));
    }

    @GetMapping("sub/{x}/{y}")
    public ResponseEntity<String> getSub(@PathVariable double x, @PathVariable double y) {
        return ResponseEntity.ok().body(Double.toString(x-y));
    }

    @GetMapping("mul/{x}/{y}")
    public ResponseEntity<String> getMul(@PathVariable double x, @PathVariable double y) {
        return ResponseEntity.ok().body(Double.toString(x*y));
    }

    @GetMapping("div/{x}/{y}")
    public ResponseEntity<String> getDiv(@PathVariable double x, @PathVariable double y) {
        if(y == 0)
            return ResponseEntity.badRequest().body("Cannot divide by zero, yo!");

        return ResponseEntity.ok().body(Double.toString(x/y));
    }
}

// Spring Boot
// Map body to method argument

// GET api/v1/math/add/2/5

/* GET api/v1/math/operation
{
    op: "/",
    x: 2,
    y: 0
}
*/

// @RestController indicates that a class will contain endpoints / actions
// @RequestMapping("a/base/path") specifies the base path for the controller
// @GetMapping("name-of-resource") specifies that the action will be triggered on GET request to <base-path>/<name-of-resource>
