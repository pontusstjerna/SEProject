package softproject.controllers;

import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.PortCallMessage;
import org.springframework.web.bind.annotation.*;
import softproject.model.PortCall;
import softproject.model.PortCallRepository;
import softproject.services.PortCDMRequest;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SubscriptionController {

    @GetMapping("/queue/subscribe/portcalls/{portCallId}")
    public String getPortCallQueueId(@PathVariable String portCallId){
        PortCDMRequest portCDMRequest = new PortCDMRequest();
        String qID = portCDMRequest.subscribe(FilterType.PORT_CALL, portCallId);
        PortCallRepository repo = PortCallRepository.getRepo();
        PortCall portCall = repo.getFromPortcallId(portCallId);
        portCall.setQueueID(qID);
        return qID;
    }

    @GetMapping("/queue/subscribe/portcalls/vessel/{vesselId}")
    public String getVesselQueueId(@PathVariable String vesselId){
        PortCDMRequest portCDMRequest = new PortCDMRequest();
        String qID = portCDMRequest.subscribe(FilterType.VESSEL, vesselId);
        PortCallRepository repo = PortCallRepository.getRepo();
        PortCall portCall = repo.getFromVesselId(vesselId);
        portCall.setQueueID(qID);
        return qID;
    }

    @GetMapping("/ship/subscribe")
    public String subscribe() {
        PortCDMRequest req = new PortCDMRequest();

        return req.createQueue();

    }

    @GetMapping("/queue/{queueId}")
    public List<PortCallMessage> viewQueue(@PathVariable String queueId) {
        PortCDMRequest req = new PortCDMRequest();
        PortCallRepository repo = PortCallRepository.getRepo();
        PortCall portCall = repo.getFromQueueId(queueId);
        List<PortCallMessage> result = req.getNewMessages(queueId);
        if (portCall.getMessages() == null) {
            portCall.setMessages(new ArrayList<>());
        }
        portCall.getMessages().addAll(result);
        return portCall.getMessages();
    }

    @GetMapping("/message/post")
    public String postMessage() {
        PortCDMRequest req = new PortCDMRequest();
        String result = req.sendMessage();

        return result;
    }

}
