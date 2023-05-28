package goncharovmvdev.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonch
 * @since 5/28/2023, 7:24 PM
 *
 */
@RestController
@RequestMapping("${uris.api}${uris.greeting}")
public class HelloWorldRESTController {
    private final String greeting;

    public HelloWorldRESTController(@Value("${greeting}") String greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/v0")
    public String helloWorldGET(@RequestParam(value = "name", required = false) String name) {
        return name == null
                ? greeting + ", World!"
                : greeting + ", " + name + "!";
    }
}
