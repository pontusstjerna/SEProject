package softproject.controllers;

import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.PortCallMessage;
import org.springframework.web.bind.annotation.*;
import softproject.model.PCMTimeWrapper;
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

    @GetMapping("/queue/{queueId}")
    public List<PCMTimeWrapper> viewQueue(@PathVariable String queueId) {
        PortCDMRequest req = new PortCDMRequest();
        PortCallRepository repo = PortCallRepository.getRepo();
        if(repo.getFromQueueId(queueId) == null){
            System.out.println("WARNING! Page was already up when server restarted. Therefore we have an old queueID polling but no corresponding PortCall!");
            return new ArrayList<>();
        }
        PortCall portCall = repo.getFromQueueId(queueId);
        List<PortCallMessage> result = req.getNewMessages(queueId);
        List<PCMTimeWrapper> wrapper = new ArrayList<>();
        result.stream().forEach(m -> wrapper.add(new PCMTimeWrapper(m,new Date().getTime())));
        if (portCall.getMessages() == null) {
            portCall.setMessages(new ArrayList<>());
        }
        List<PCMTimeWrapper> storedMessages = portCall.getMessages();
        System.out.println(storedMessages);
        storedMessages.addAll(wrapper);
        System.out.println(storedMessages);
        portCall.setMessages(storedMessages);
        //repo.savePortCallsToFile();
        return storedMessages;
    }

    @GetMapping("/message/post")
    public String postMessage() {
        PortCDMRequest req = new PortCDMRequest();
        String result = req.sendMessage();

        return result;
    }

}
