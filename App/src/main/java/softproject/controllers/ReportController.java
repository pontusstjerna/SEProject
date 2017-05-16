package softproject.controllers;

import eu.portcdm.messaging.*;
import org.springframework.web.bind.annotation.*;
import softproject.model.PortCall;
import softproject.services.Amss;
import softproject.services.PortCDMRequest;
import softproject.util.PortCallMessageBuilder;
import softproject.util.ServiceStateBuilder;
import java.time.ZonedDateTime;
import java.util.Map;


@RestController
public class ReportController {

    private final String LOCATION_NAME = "520";
    private final LogicalLocation LOGICAL_LOCATION = LogicalLocation.BERTH;

    @PostMapping("/report/cargo/commenced")
    public void reportCargoOpCommenced(@RequestBody PortCall portcall,
                                       @RequestParam String time,
                                       @RequestParam TimeType timeType) {
        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.CARGO_OPERATION)
                .timeSequence(ServiceTimeSequence.COMMENCED)
                .time(dateTime)
                .timeType(timeType)
                .at(LOCATION_NAME, LOGICAL_LOCATION, null)
                .build();

        PortCallMessage message = PortCallMessageBuilder.newBuilder()
                .portCallId(portcall.getPortcallId())
                .vesselId(portcall.getVesselId())
                .comment("Johans kommentar 2")
                .serviceState(state)
                .messageOperation("CargoOp_Commenced")
                .build();

        sendMessage(message);
    }

    @PostMapping("/report/cargo/completed/{time}")
    public void reportCargoOpCompleted(@RequestBody PortCall portcall, @PathVariable String time) {
        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.CARGO_OPERATION)
                .timeSequence(ServiceTimeSequence.COMPLETED)
                .time(dateTime)
                .timeType(TimeType.ACTUAL)
                .at(LOCATION_NAME, LOGICAL_LOCATION, null)
                .build();

        PortCallMessage message = PortCallMessageBuilder.newBuilder()
                .portCallId(portcall.getPortcallId())
                .vesselId(portcall.getVesselId())
                .comment("From reportCargoOpCompleted")
                .serviceState(state)
                .build();

        sendMessage(message);
    }






    private void sendMessage(PortCallMessage message) {
        Amss amss = new Amss(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());
        amss.postStateUpdate(message);
    }

}
