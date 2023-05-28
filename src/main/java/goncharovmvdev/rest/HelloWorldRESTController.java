package goncharovmvdev.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gonch
 * @since 5/28/2023, 7:24 PM
 */
@RestController
@RequestMapping("${uris.root:/}")
public class HelloWorldRESTController {
    private final String greeting;

    public HelloWorldRESTController(@Value("greeting") String greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/{name}")
    public String helloWorld(@PathVariable(value = "name", required = false) String name) {
        return name == null
                ? greeting + ", World!"
                : greeting + ", " + name;
    }
}
