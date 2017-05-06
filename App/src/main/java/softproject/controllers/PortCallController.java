package softproject.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softproject.HTTPRequests;

/**
 * Created by pontu on 2017-04-26.
 */

@RestController
public class PortCallController {

    @PostMapping("/portcalls/add")
    public void addPortCall(@RequestBody String newPortCall) {
        System.out.println(newPortCall);

    }

}
