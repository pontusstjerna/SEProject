package softproject.controllers;


import eu.portcdm.messaging.Location;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softproject.model.PortCall;
import softproject.model.PortCallRepository;
import softproject.model.Vessel;
import softproject.services.LocationRegistryService;
import softproject.services.PortCDMRequest;
import softproject.services.VesselRegistryService;
import softproject.services.exceptions.BadRequest;

import javax.annotation.PostConstruct;
import javax.sound.sampled.Port;
import java.util.List;


/**
 * Created by pontu on 2017-04-26.
 */

@RestController
public class PortCallController {

    private static final String BERTH_BASE = "urn:mrn:stm:location:segot:BERTH:";

    @PostMapping("/portcalls/add")
    public boolean addPortCall(@RequestBody PortCall newPortCall) {

        LocationRegistryService ls = new LocationRegistryService(PortCDMRequest.getClientInstance(),
                                                                 PortCDMRequest.getBaseRequest());

        boolean exist = ls.doesLocationMRNExist(BERTH_BASE + newPortCall.getBerth());
        if(!exist) {
            System.out.println("Invalid location URN!");
            return false;
        }

        PortCallRepository.getRepo().add(newPortCall);
        return true;
    }

    @RequestMapping("/internalPortcall")
    public PortCall portcall(@RequestParam(value="id") String id) {
        int portId = Integer.parseInt(id);

        return PortCallRepository.getRepo().get(portId);
    }

    private class DummyPortCall{
        private int id;
        private String vesselName;

        public DummyPortCall(){
            id = 1337;
            vesselName = "Superfint anrop";
        }

        public int getId(){
            return id;
        }

        public String getVesselName(){
            return vesselName;
        }
    }

    @GetMapping("/portcalls")
    public List<PortCall> getAllPortCalls() {


        return PortCallRepository.getRepo().getAll();
    }
}
