package softproject.controllers;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import softproject.Greeting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Pontus on 2017-04-23.
 */

@RestController
public class TestRestController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/test")
    public Greeting greeting(@RequestParam(value="name", defaultValue="there") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/test2")
    public String greeting() {
        return "I come from java.";
    }

    @RequestMapping("/test3")
    public String greeting() {return "I did it"}
}
