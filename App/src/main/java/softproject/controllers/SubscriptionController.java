package softproject.controllers;

import eu.portcdm.messaging.PortCallMessage;
import org.springframework.web.bind.annotation.*;
import softproject.services.PortCDMRequest;

import java.util.List;


@RestController
public class SubscriptionController {

    @GetMapping("/queue/subscribe")
    public String getQueueId(){
        PortCDMRequest portCDMRequest = new PortCDMRequest();

        return portCDMRequest.subscribe();
    }

    @GetMapping("/ship/subscribe")
    public String subscribe() {
        PortCDMRequest req = new PortCDMRequest();

        return req.createQueue();

    }

    @GetMapping("/queue/{queueId}")
    public List<PortCallMessage> viewQueue(@PathVariable String queueId) {
        PortCDMRequest req = new PortCDMRequest();
        List<PortCallMessage> result = req.getNewMessages(queueId);
        return result;
    }

    @GetMapping("/message/post")
    public String postMessage() {
        PortCDMRequest req = new PortCDMRequest();
        String result = req.sendMessage();

        return result;
    }

}
