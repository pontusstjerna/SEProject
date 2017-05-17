package softproject.controllers;

import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.PortCallMessage;
import org.springframework.web.bind.annotation.*;
import softproject.model.PortCall;
import softproject.model.PortCallRepository;
import softproject.services.PortCDMRequest;

import java.util.*;


@RestController
public class SubscriptionController {

    @GetMapping("/queue/subscribe/portcalls/{portCallId}")
    public String getPortCallQueueId(@PathVariable String portCallId){
        PortCDMRequest portCDMRequest = new PortCDMRequest();
        String qID = portCDMRequest.subscribe(FilterType.PORT_CALL, portCallId);
        PortCallRepository repo = PortCallRepository.getRepo();
        PortCall portCall = repo.getFromPortcallId(portCallId);
        portCall.setQueueID(qID);
        System.out.println("PortCallId found and saved!");
        return qID;
    }

    @GetMapping("/queue/subscribe/portcalls/vessel/{vesselId}")
    public String getVesselQueueId(@PathVariable String vesselId){
        PortCDMRequest portCDMRequest = new PortCDMRequest();
        String qID = portCDMRequest.subscribe(FilterType.VESSEL, vesselId);
        PortCallRepository repo = PortCallRepository.getRepo();
        PortCall portCall = repo.getFromVesselId(vesselId);
        portCall.setQueueID(qID);
        System.out.println("VesselId found and saved!");
        return qID;
    }

    @GetMapping("/ship/subscribe")
    public String subscribe() {
        PortCDMRequest req = new PortCDMRequest();

        return req.createQueue();

    }

    @GetMapping("/queue/new/{queueId}")
    public List<PortCallMessage> newQueue(@PathVariable String queueId) {
        PortCDMRequest req = new PortCDMRequest();
        List<PortCallMessage> result = req.getNewMessages(queueId);
        System.out.println("New Queue " + result);
        return result;
    }

    @GetMapping("/queue/old/{queueId}")
    public List<PortCallMessage> oldQueue(@PathVariable String queueId) {
        PortCDMRequest req = new PortCDMRequest();
        PortCallRepository repo = PortCallRepository.getRepo();
        if(repo.getFromQueueId(queueId) == null){
            System.out.println("WARNING! Page was already up when server restarted. Therefore we have an old queueID polling but no corresponding PortCall!");
            return new ArrayList<>();
        }
        PortCall portCall = repo.getFromQueueId(queueId);
        String qID = req.subscribe(FilterType.PORT_CALL, portCall.getPortcallId(), "1970-01-01T00%3A00%3A00Z");
        List<PortCallMessage> result = req.getNewMessages(qID);
        System.out.println("Old Queue: " + result);
        return result;
    }

    @GetMapping("/message/post")
    public String postMessage() {
        PortCDMRequest req = new PortCDMRequest();
        String result = req.sendMessage();

        return result;
    }

}
