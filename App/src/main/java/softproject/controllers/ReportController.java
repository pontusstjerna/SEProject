package softproject.controllers;

import eu.portcdm.messaging.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softproject.model.PortCall;
import softproject.services.Amss;
import softproject.services.MessageQueueService;
import softproject.services.PortCDMRequest;
import softproject.util.PortCallMessageBuilder;
import softproject.util.ServiceStateBuilder;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import static eu.portcdm.messaging.LogicalLocation.BERTH;


@RestController
public class ReportController {

    private final String LOCATION_NAME = "520";
    private final LogicalLocation LOGICAL_LOCATION = LogicalLocation.BERTH;

    @PostMapping("/report/cargo/commenced/{time}")
    public void reportCargoOpCommenced(@RequestBody PortCall portcall, @PathVariable String time) {
        ZonedDateTime dateTime = ZonedDateTime.parse(time);

        ServiceState state = ServiceStateBuilder.newBuilder()
                .serviceObject(ServiceObject.CARGO_OPERATION)
                .time(dateTime)
                .timeType(TimeType.ACTUAL)
                .at(LOCATION_NAME, LOGICAL_LOCATION, null)
                .build();

        PortCallMessage message = PortCallMessageBuilder.newBuilder()
                .portCallId(portcall.getPortcallId())
                .vesselId(portcall.getVesselId())
                .comment("Johans kommentar")
                .serviceState(state)
                .build();

        Amss amss = new Amss(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());
        amss.postStateUpdate(message);
    }

}
