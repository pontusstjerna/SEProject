package softproject.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.portcdm.messaging.PortCallMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import softproject.model.PortCall;
import softproject.model.PortCallRepository;
import softproject.model.Vessel;
import softproject.services.PortCDMRequest;
import softproject.services.VesselRegistryService;

@RestController
public class VesselController {
    @JsonProperty
    private boolean nameSet = false;

    @GetMapping("/vessel/{vesselURN}")
    public boolean getVessel(@PathVariable String vesselURN) {
        VesselRegistryService api = new VesselRegistryService(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());


        PortCallRepository repo = PortCallRepository.getRepo();
        PortCall portCall = repo.getFromVesselId(vesselURN);

        Vessel vessel = api.getVessel(vesselURN);

        if (portCall != null && (portCall.getVesselId() != null || !portCall.getVesselId().equals("")) && portCall.getVesselId().contains("urn:mrn:stm:vessel:IMO:")) {
            if(!portCall.getName().equals(vessel.getName())) {
                portCall.setName(vessel.getName());
                repo.add(portCall);
                nameSet = true;
                System.out.println("Vessel name was set to: " + vessel.getName());
            }else{
                System.out.println("Vessel name already exists and matches!");
            }
        }

        return nameSet;
    }
}
