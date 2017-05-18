package softproject.util;

import eu.portcdm.dto.DepartureLocation;
import eu.portcdm.messaging.*;
import softproject.services.exceptions.IncompletePortCallMessage;
import java.time.ZonedDateTime;
import java.util.UUID;

public class PortCallMessageBuilder {

    private PortCallMessage message = new PortCallMessage();

    private PortCallMessageBuilder(String messageId) {
        message = new PortCallMessage();
        message.setMessageId(messageId);
    }

    public static PortCallMessageBuilder newBuilder() {
        return new PortCallMessageBuilder("urn:mrn:stm:portcdm:message:" + UUID.randomUUID().toString());
    }


    public PortCallMessageBuilder portCallId(String id) {
        this.message.setPortCallId(id);
        return this;
    }

    public PortCallMessageBuilder localPortCallId(String id) {
        this.message.setLocalPortCallId(id);
        return this;
    }

    public PortCallMessageBuilder localJobId(String id) {
        this.message.setLocalJobId(id);
        return this;
    }

    public PortCallMessageBuilder vesselId(String id) {
        this.message.setVesselId(id);
        return this;
    }

    public PortCallMessageBuilder messageId(String id) {
        this.message.setMessageId(id);
        return this;
    }

    public PortCallMessageBuilder groupWith(String value) {
        this.message.setGroupWith(value);
        return this;
    }

    public PortCallMessageBuilder reportedAt(ZonedDateTime time) {
        this.message.setReportedAt(time);
        return this;
    }

    public PortCallMessageBuilder comment(String comment) {
        this.message.setComment(comment);
        return this;
    }

    public PortCallMessageBuilder locationState(LocationState state) {
        this.message.setLocationState(state);
        return this;
    }

    public PortCallMessageBuilder locationState(LocationReferenceObject refObject,
                                                TimeType timeType,
                                                ZonedDateTime time,
                                                LocationState.ArrivalLocation arrivalLocation,
                                                LocationState.DepartureLocation departureLocation) {
        LocationState locationState = new LocationState();
        locationState.setReferenceObject(refObject);
        locationState.setTimeType(timeType);
        locationState.setTime(time.toLocalDateTime());
        locationState.setArrivalLocation(arrivalLocation);
        locationState.setDepartureLocation(departureLocation);
        this.message.setLocationState(locationState);
        return this;
    }


    public PortCallMessageBuilder serviceState(ServiceState state) {
        this.message.setServiceState(state);
        return this;
    }

    public PortCallMessageBuilder serviceState(Location location,
                                               eu.portcdm.messaging.ServiceState.Between between,
                                               String performingActor,
                                               ServiceObject serviceObject,
                                               ZonedDateTime time,
                                               ServiceTimeSequence serviceTimeSequence,
                                               TimeType timeType
                                               ) {
        ServiceState serviceState = new ServiceState();
        serviceState.setAt(location);
        serviceState.setBetween(between);
        serviceState.setPerformingActor(performingActor);
        serviceState.setServiceObject(serviceObject);
        serviceState.setTime(time.toLocalDateTime());
        serviceState.setTimeSequence(serviceTimeSequence);
        serviceState.setTimeType(timeType);
        this.message.setServiceState(serviceState);
        return this;
    }

    public PortCallMessageBuilder messageOperation(String operation) {
        MessageOperation op = new MessageOperation();
        op.setMessageId(this.message.getMessageId());
        op.setOperation(operation);
        this.message.setMessageOperation(op);
        return this;
    }

    public PortCallMessage build() {
        if(this.message.getMessageId() != null
                && this.message.getVesselId() != null
                && !(this.message.getLocationState() == null && this.message.getServiceState() == null)) {
            return this.message;
        } else {
//            throw new IncompletePortCallMessage();
            return null;
        }
    }

}
