package softproject.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import eu.portcdm.messaging.PortCallMessage;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PortCall implements Serializable {

    private static int portCallCount = 0;

    private long internalId;
    private String comment;
    private String cargoIn;
    private String cargoOut;
    private String name;
    private String vesselId;
    private String portcallId;
    private ZonedDateTime laycanStart;
    private ZonedDateTime laycanEnd;
    private String queueID;
    private List<PCMTimeWrapper> messages = new ArrayList<>();

    public PortCall() {
        portCallCount++;
        internalId = portCallCount;
    }

    public long getInternalId() {
        return internalId;
    }


    public String getCargoIn() {
        return cargoIn;
    }

    public void setCargoIn(String cargoIn) {
        this.cargoIn = cargoIn;
    }

    public String getCargoOut() {
        return cargoOut;
    }

    public void setCargoOut(String cargoOut) {
        this.cargoOut = cargoOut;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getLaycanStart() {
        return laycanStart;
    }

    public void setLaycanStart(ZonedDateTime laycanStart) {
        this.laycanStart = laycanStart;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getLaycanEnd() {
        return laycanEnd;
    }

    public void setLaycanEnd(ZonedDateTime laycanEnd) {
        this.laycanEnd = laycanEnd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVesselId() {
        return vesselId;
    }

    public void setVesselId(String vesselId) {
        this.vesselId = vesselId;
    }

    public String getPortcallId() {
        return portcallId;
    }

    public void setPortcallId(String portcallId) {
        this.portcallId = portcallId;
    }

    public String getQueueID() {
        return queueID;
    }

    public void setQueueID(String queueID) {
        this.queueID = queueID;
    }

    public List<PCMTimeWrapper> getMessages() { return messages; }

    public void setMessages(List<PCMTimeWrapper> messages) { this.messages = messages;
                                                             }
}

