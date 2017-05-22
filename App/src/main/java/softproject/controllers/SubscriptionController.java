package softproject.controllers;

import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.PortCallMessage;
import org.springframework.web.bind.annotation.*;
import softproject.model.PortCall;
import softproject.model.PortCallRepository;
import softproject.services.SubscriptionService;

import java.util.*;


@RestController
public class SubscriptionController {

    @GetMapping("/queue/subscribe/portcalls/{portCallId}")
    public String getPortCallQueueId(@PathVariable String portCallId) {
        SubscriptionService subService = new SubscriptionService();
        PortCallRepository repo = PortCallRepository.getRepo();
        if(repo.getFromPortcallId(portCallId) == null){
            return "";
        }
        PortCall portCall = repo.getFromPortcallId(portCallId);
        String qID;
        if (portCall.getQueueID() == null || portCall.getQueueID().equals("")) {
            qID = subService.subscribe(FilterType.PORT_CALL, portCallId);
            portCall.setQueueID(qID);
            repo.add(portCall);
            System.out.println("PortCallId found and saved! queueId did not exists! Created and saved!");
            return qID;
        }
        qID = portCall.getQueueID();
        System.out.println("PortCallId found and saved! queueId already existed and was fetched!");
        return qID;
    }

    @GetMapping("/queue/subscribe/portcalls/vessel/{vesselId}")
    public String getVesselQueueId(@PathVariable String vesselId) {
        SubscriptionService subService = new SubscriptionService();
        PortCallRepository repo = PortCallRepository.getRepo();
        if(repo.getFromPortcallId(vesselId) == null){
            return "";
        }
        PortCall portCall = repo.getFromVesselId(vesselId);
        String qID;
        if (portCall.getQueueID() == null || portCall.getQueueID().equals("")) {
            qID = subService.subscribe(FilterType.VESSEL, vesselId);
            portCall.setQueueID(qID);
            repo.add(portCall);
            System.out.println("PortCallId found and saved! queueId did not exists! Created and saved!");
            return qID;
        }
        qID = portCall.getQueueID();
        System.out.println("PortCallId found and saved! queueId already existed and was fetched!");
        return qID;
    }

    @GetMapping("/queue/new/{queueId}")
    public List<PortCallMessage> newQueue(@PathVariable String queueId) {
        SubscriptionService subService = new SubscriptionService();
        List<PortCallMessage> result = subService.getNewMessages(queueId);
        System.out.println("New Queue " + result);
        return result;
    }

    @GetMapping("/queue/old/{queueId}")
    public List<PortCallMessage> oldQueue(@PathVariable String queueId) {

        SubscriptionService subService = new SubscriptionService();
        PortCallRepository repo = PortCallRepository.getRepo();
        if(repo.getFromQueueId(queueId) == null){
            return new ArrayList<>();
        }
        PortCall portCall = repo.getFromQueueId(queueId);
        if(portCall.getPortcallId() == null || portCall.getPortcallId().equals("")){
            String qID = subService.subscribe(FilterType.VESSEL, portCall.getVesselId(),"2017-04-20T00:00:00Z");
            List<PortCallMessage> result = subService.getNewMessages(qID);
            System.out.println("Old Queue: " + result);
            return result;
        }
        String qID = subService.subscribe(FilterType.PORT_CALL, portCall.getPortcallId(),"2017-04-20T00:00:00Z");
        List<PortCallMessage> result = subService.getNewMessages(qID);
        System.out.println("Old Queue: " + result);
        return result;
    }
}
