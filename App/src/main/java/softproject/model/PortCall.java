package softproject.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.Time;
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
    private Timestamp laycanStart;
    private Timestamp laycanEnd;
    private Timestamp cargoOpCommenced;
    private Timestamp cargoOpCompleted;
    private Timestamp readyToSail;
    private Timestamp SlopOpConfirmed;
    private Timestamp SlopOpDenied;
    private Timestamp SlopOpReqRecieved;
    private Timestamp ArrivalVesselBerth;
    private Timestamp DepartureVesselBerth;

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
    public Timestamp getLaycanStart() {
        return laycanStart;
    }

    public void setLaycanStart(Timestamp laycanStart) {
        this.laycanStart = laycanStart;
    }

    @JsonFormat (shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mmz")
    public Timestamp getLaycanEnd() {
        return laycanEnd;
    }

    public void setLaycanEnd(Timestamp laycanEnd) {
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
