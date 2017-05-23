package softproject.controllers;

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

    @GetMapping("/vessel/{vesselURN}")
    public Vessel getVessel(@PathVariable String vesselURN) {
        VesselRegistryService api = new VesselRegistryService(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());

        PortCallRepository repo = PortCallRepository.getRepo();
        PortCall portCall = repo.getFromVesselId(vesselURN);

        Vessel vessel = api.getVessel(vesselURN);

        if (portCall != null && (portCall.getVesselId() != null || !portCall.getVesselId().equals("")) && portCall.getVesselId().contains("urn:mrn:stm:vessel:IMO:")) {
            if(portCall.getName() != vessel.getName()) {
                portCall.setName(vessel.getName());
                repo.add(portCall);
                System.out.println("Vessel name was set to: " + vessel.getName());
            }else{
                System.out.println("Vessel name already exists and matches!");
            }
        }

        return vessel;
    }
}
