package softproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import softproject.model.Vessel;
import softproject.services.PortCDMRequest;
import softproject.services.VesselRegistryService;

@RestController
public class VesselController {

    @GetMapping("/vessel/{vesselURN}")
    public Vessel getVessel(@PathVariable String vesselURN) {
        VesselRegistryService api = new VesselRegistryService(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());

        return api.getVessel(vesselURN);
    }
}
