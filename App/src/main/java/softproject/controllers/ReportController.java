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

    private final String LOCATION_NAME = "519";
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
                .at("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
                .build();

        PortCallMessage message = createMessage(portcall, state, null);

        sendMessage(message);
    }

    @PostMapping("/report/cargo/completed")
    public void reportCargoOpCompleted(@RequestBody PortCall portcall,
                                       @RequestParam String time,
                                       @RequestParam TimeType timeType) {
        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.CARGO_OPERATION)
                .timeSequence(ServiceTimeSequence.COMPLETED)
                .time(dateTime)
                .timeType(timeType)
                .at("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
                .build();

        PortCallMessage message = createMessage(portcall, state, null); //comment "From reportCargoOpCompleted"

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
                .departureLocation("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
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
                .arrivalLocation("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
                .build();

        PortCallMessage message = createMessage(portcall, null, state);

        sendMessage(message);
    }

    @PostMapping("/report/readytosailop/completed")
    public void reportReadyToSailOpCompleted(@RequestBody PortCall portcall,
                                             @RequestParam String time,
                                             @RequestParam TimeType timeType){
        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.DEPARTURE_BERTH)
                .timeSequence(ServiceTimeSequence.COMPLETED)
                .time(dateTime)
                .timeType(timeType)
                .at("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
                .build();

        PortCallMessage message = createMessage(portcall, state, null); //.comment("Ready-to-Sail Operations Completed")

        sendMessage(message);
    }


    @PostMapping("/report/slopop/received")
    public void reportSlopOpReqReceived(@RequestBody PortCall portcall,
                                        @RequestParam String time,
                                        @RequestParam TimeType timeType) {

        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.SLOP_OPERATION)
                .timeSequence(ServiceTimeSequence.REQUEST_RECEIVED)
                .time(dateTime)
                .timeType(timeType)
                .at("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
                .build();

        PortCallMessage message = createMessage(portcall, state, null);

        sendMessage(message);
    }


    @PostMapping("/report/slopop/confirmed")
    public void reportSlopOpConfirmed(@RequestBody PortCall portcall,
                                      @RequestParam String time,
                                      @RequestParam TimeType timeType) {
        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.SLOP_OPERATION)
                .timeSequence(ServiceTimeSequence.CONFIRMED)
                .time(dateTime)
                .timeType(timeType)
                .at("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
                .build();

        PortCallMessage message = createMessage(portcall, state, null);
        //.comment("Slop Operation confirmed")


        sendMessage(message);
    }

        @PostMapping("/report/slopop/denied")
        public void reportSlopOpDenied(@RequestBody PortCall portcall,
                                        @RequestParam String time,
                                        @RequestParam TimeType timeType) {
            ZonedDateTime dateTime = ZonedDateTime.parse(time);

            ServiceState state = ServiceStateBuilder.newBuilder()
                    .serviceObject(ServiceObject.SLOP_OPERATION)
                    .timeSequence(ServiceTimeSequence.DENIED)
                    .time(dateTime)
                    .timeType(timeType)
                    .at("urn:mrn:stm:location:segot:BERTH:skarvik" + LOCATION_NAME, null)
                    .build();

            PortCallMessage message = createMessage(portcall, state, null);

            sendMessage(message);
        }


    private PortCallMessage createMessage(PortCall portcall, ServiceState serviceState, LocationState locationState) {
        if(portcall.getVesselId() == null){
            return PortCallMessageBuilder.newBuilder()
                    .serviceState(serviceState)
                    .locationState(locationState)
                    .portCallId(portcall.getPortcallId())
                    .build();
        }else
            return PortCallMessageBuilder.newBuilder()
                .serviceState(serviceState)
                .locationState(locationState)
                .vesselId(portcall.getVesselId())
                .portCallId(portcall.getPortcallId())
                .build();
    }

    private void sendMessage(PortCallMessage message) {
        Amss amss = new Amss(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());

        if(message.getVesselId() == null)
            amss.postMSS(message);
        else
            amss.postStateUpdate(message);
    }

}
