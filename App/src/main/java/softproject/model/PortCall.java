package softproject.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Time;
import java.time.ZonedDateTime;

public class PortCall implements Serializable{

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
    private String berth;

    //To PortCDM
    private Timestamp cargoOpCommenced;
    private Timestamp cargoOpCompleted;
    private Timestamp readyToSail;
    private Timestamp SlopOpConfirmed;
    private Timestamp SlopOpDenied;
    private Timestamp SlopOpReqRecieved;
    private Timestamp ArrivalVesselBerth;
    private Timestamp DepartureVesselBerth;

    public PortCall() {
        portCallCount++;
        internalId = portCallCount;
    }

    public long getInternalId(){
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

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getLaycanStart() {
        return laycanStart;
    }

    public void setLaycanStart(ZonedDateTime laycanStart) {
        this.laycanStart = laycanStart;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
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

    public String getBerth(){return berth;}

    public void setBerth(String berth){this.berth = berth;}

    public Timestamp getCargoOpCommenced() {
        return cargoOpCommenced;
    }

    public void setCargoOpCommenced(Timestamp cargoOpCommenced) {
        this.cargoOpCommenced = cargoOpCommenced;
    }

    public Timestamp getCargoOpCompleted() {
        return cargoOpCompleted;
    }

    public void setCargoOpCompleted(Timestamp cargoOpCompleted) {
        this.cargoOpCompleted = cargoOpCompleted;
    }

    public Timestamp getReadyToSail() {
        return readyToSail;
    }

    public void setReadyToSail(Timestamp readyToSail) {
        this.readyToSail = readyToSail;
    }

    public Timestamp getSlopOpConfirmed() {
        return SlopOpConfirmed;
    }

    public void setSlopOpConfirmed(Timestamp slopOpConfirmed) {
        SlopOpConfirmed = slopOpConfirmed;
    }

    public Timestamp getSlopOpDenied() {
        return SlopOpDenied;
    }

    public void setSlopOpDenied(Timestamp slopOpDenied) {
        SlopOpDenied = slopOpDenied;
    }

    public Timestamp getSlopOpReqRecieved() {
        return SlopOpReqRecieved;
    }

    public void setSlopOpReqRecieved(Timestamp slopOpReqRecieved) {
        SlopOpReqRecieved = slopOpReqRecieved;
    }

    public Timestamp getArrivalVesselBerth() {
        return ArrivalVesselBerth;
    }

    public void setArrivalVesselBerth(Timestamp arrivalVesselBerth) {
        ArrivalVesselBerth = arrivalVesselBerth;
    }

    public Timestamp getDepartureVesselBerth() {
        return DepartureVesselBerth;
    }

    public void setDepartureVesselBerth(Timestamp departureVesselBerth) {
        DepartureVesselBerth = departureVesselBerth;
    }
}
