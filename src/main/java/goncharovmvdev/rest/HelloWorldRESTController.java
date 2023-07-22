package goncharovmvdev.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonch
 * @since 5/28/2023, 7:24 PM
 */
@RestController
public class HelloWorldRESTController {
    private final String greeting;

    public HelloWorldRESTController(@Value("${greeting}") String greeting) {
        this.greeting = greeting;
    }

    @GetMapping
    public String greeting() {
        return greeting;
    }
}
