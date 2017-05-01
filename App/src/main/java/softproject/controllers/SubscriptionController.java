package softproject.controllers;

import org.springframework.web.bind.annotation.*;
import softproject.services.PortCDMRequest;


@RestController
public class SubscriptionController {

    @GetMapping("/ship/subscribe")
    public String subscribe() {
        PortCDMRequest req = new PortCDMRequest();

        return req.createQueue();

    }

    @GetMapping("/queue/{queueId}")
    public String viewQueue(@PathVariable String queueId) {
        PortCDMRequest req = new PortCDMRequest();
        String result = req.getNewMessages(queueId);

        System.out.println(result);

        return result;
    }

    @GetMapping("/message/post")
    public String postMessage() {
        PortCDMRequest req = new PortCDMRequest();
        String result = req.sendMessage();

        return result;
    }

}
