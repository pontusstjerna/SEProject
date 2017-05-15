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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;


@RestController
public class ReportController {

    private final String LOCATION_NAME = "520";

    @PostMapping("/report/cargo/commenced/{time}")
    public void reportCargoOpCommenced(@RequestBody PortCall portcall, @PathVariable String time) {
        ZonedDateTime dateTime = ZonedDateTime.parse(time);
        System.out.println("in reportCargoOpCommenced");

        GregorianCalendar gregCal = GregorianCalendar.from(dateTime);
        XMLGregorianCalendar xmlGregCal = null;
        try {
            xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        Location location = new Location();
        location.setName(LOCATION_NAME);
        location.setLocationType(LogicalLocation.BERTH);

        ServiceState serviceState = new ServiceState();
        serviceState.setTimeSequence(ServiceTimeSequence.COMMENCED);
        serviceState.setServiceObject(ServiceObject.CARGO_OPERATION);
        serviceState.setTime(xmlGregCal);
        serviceState.setTimeType(TimeType.ACTUAL);
        serviceState.setAt(location);

        PortCallMessage message = PortCallMessageBuilder.newBuilder()
                .portCallId(portcall.getPortcallId())
                .vesselId(portcall.getVesselId())
                .comment("Johans kommentar")
//                .serviceState(serviceState)
                .build();

        Amss amss = new Amss(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());
        amss.postStateUpdate(message);
    }

}
