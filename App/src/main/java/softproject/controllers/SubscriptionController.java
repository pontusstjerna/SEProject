package softproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import softproject.services.PortCDMRequest;


@RestController
public class SubscriptionController {

    @GetMapping("/queue/subscribe")
    public String subscribe() {
        PortCDMRequest req = new PortCDMRequest();

        return req.createQueue();

    }

    @GetMapping("/queue/{queueId}")
    public String viewQueue(@PathVariable String queueId) {
        PortCDMRequest req = new PortCDMRequest();
        String result = req.getNewMessages(queueId);

        System.out.println(result);

        return queueId;
    }

}
