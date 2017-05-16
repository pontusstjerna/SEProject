package softproject.controllers;

import eu.portcdm.messaging.*;
import org.springframework.web.bind.annotation.*;
import softproject.model.PortCall;
import softproject.services.Amss;
import softproject.services.PortCDMRequest;
import softproject.util.LocationStateBuilder;
import softproject.util.PortCallMessageBuilder;
import softproject.util.ServiceStateBuilder;
import java.time.ZonedDateTime;

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

        PortCallMessage message = createMessage(portcall, state, null);

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

        PortCallMessage message = createMessage(portcall, state, null);

        sendMessage(message);
    }

    @PostMapping("/report/vessel/departure")
    public void reportDepartureVesselBerth(@RequestBody PortCall portcall,
                                           @RequestParam String time,
                                           @RequestParam TimeType timeType) {

        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        LocationState state = LocationStateBuilder.newBuilder()
                .referenceObject(LocationReferenceObject.VESSEL)
                .time(dateTime)
                .timeType(timeType)
                .departureLocation(LOCATION_NAME, LOGICAL_LOCATION, null)
                .build();

        PortCallMessage message = createMessage(portcall, null, state);

        sendMessage(message);
    }

    @PostMapping("/report/vessel/arrival")
    public void reportArrivalVesselBerth(@RequestBody PortCall portcall,
                                         @RequestParam String time,
                                         @RequestParam TimeType timeType) {

        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        LocationState state = LocationStateBuilder.newBuilder()
                .referenceObject(LocationReferenceObject.VESSEL)
                .time(dateTime)
                .timeType(timeType)
                .arrivalLocation(LOCATION_NAME, LOGICAL_LOCATION, null)
                .build();

        PortCallMessage message = createMessage(portcall, null, state);

        sendMessage(message);
    }

    @PostMapping("/report/slop/reqreceived")
    public void reportSlopOpReqReceived(@RequestBody PortCall portcall,
                                        @RequestParam String time,
                                        @RequestParam TimeType timeType) {

        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.SLOP_OPERATION)
                .timeSequence(ServiceTimeSequence.REQUEST_RECEIVED)
                .time(dateTime)
                .timeType(timeType)
                .at(LOCATION_NAME, LOGICAL_LOCATION, null)
                .build();

        PortCallMessage message = createMessage(portcall, state, null);

        sendMessage(message);
    }

    @PostMapping("/report/slop/denied")
    public void reportSlopOpDenied(@RequestBody PortCall portcall,
                                        @RequestParam String time,
                                        @RequestParam TimeType timeType) {

        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.SLOP_OPERATION)
                .timeSequence(ServiceTimeSequence.DENIED)
                .time(dateTime)
                .timeType(timeType)
                .at(LOCATION_NAME, LOGICAL_LOCATION, null)
                .build();

        PortCallMessage message = createMessage(portcall, state, null);

        sendMessage(message);
    }

    private PortCallMessage createMessage(PortCall portcall, ServiceState serviceState, LocationState locationState) {
        PortCallMessageBuilder.newBuilder()
                .serviceState(serviceState)
                .locationState(locationState)
                .vesselId(portcall.getVesselId())
                .portCallId(portcall.getPortcallId())
                .build();
    }

    private void sendMessage(PortCallMessage message) {
        Amss amss = new Amss(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());
        amss.postStateUpdate(message);
    }

}
