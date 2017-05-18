package softproject.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class PortCall implements Serializable{

    private long internalId;
    private String comment;
    private String cargoIn;
    private String cargoOut;
    private String name;
    private String vesselId;
    private String portcallId;
    private String berth;

    //Timestamps
    private ZonedDateTime laycanStart;
    private ZonedDateTime laycanEnd;
    private ZonedDateTime cargoOpCommenced;
    private ZonedDateTime cargoOpCompleted;
    private ZonedDateTime readyToSail;
    private ZonedDateTime slopOpConfirmed;
    private ZonedDateTime slopOpDenied;
    private ZonedDateTime slopOpReqReceived;
    private ZonedDateTime arrivalVesselBerth;
    private ZonedDateTime departureVesselBerth;
    

    public long getInternalId(){
        return internalId;
    }

    public void setInternalId(long id){internalId = id;}

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

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getCargoOpCommenced() {
        return cargoOpCommenced;
    }

    public void setCargoOpCommenced(ZonedDateTime cargoOpCommenced) {
        this.cargoOpCommenced = cargoOpCommenced;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getCargoOpCompleted() {
        return cargoOpCompleted;
    }

    public void setCargoOpCompleted(ZonedDateTime cargoOpCompleted) {
        this.cargoOpCompleted = cargoOpCompleted;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getReadyToSail() {
        return readyToSail;
    }

    public void setReadyToSail(ZonedDateTime readyToSail) {
        this.readyToSail = readyToSail;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getSlopOpConfirmed() {
        return slopOpConfirmed;
    }

    public void setSlopOpConfirmed(ZonedDateTime slopOpConfirmed) {
        this.slopOpConfirmed = slopOpConfirmed;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getSlopOpDenied() {
        return slopOpDenied;
    }

    public void setSlopOpDenied(ZonedDateTime slopOpDenied) {
        this.slopOpDenied = slopOpDenied;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getSlopOpReqReceived() {
        return slopOpReqReceived;
    }

    public void setSlopOpReqReceived(ZonedDateTime slopOpReqReceived) {
        this.slopOpReqReceived = slopOpReqReceived;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getArrivalVesselBerth() {
        return arrivalVesselBerth;
    }

    public void setArrivalVesselBerth(ZonedDateTime arrivalVesselBerth) {
        this.arrivalVesselBerth = arrivalVesselBerth;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public ZonedDateTime getDepartureVesselBerth() {
        return departureVesselBerth;
    }

    public void setDepartureVesselBerth(ZonedDateTime departureVesselBerth) {
        this.departureVesselBerth = departureVesselBerth;
    }
}
