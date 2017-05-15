package softproject.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softproject.model.PortCall;

import java.time.ZonedDateTime;


@RestController
public class ReportController {

    @PostMapping("/report/cargo/commenced/{time}")
    public void reportCargoOpCommenced(@RequestBody PortCall portcall, @PathVariable ZonedDateTime time) {
        String vesselId = portcall.getVesselId();
        String portcallId = portcall.getPortcallId();
    }

}
