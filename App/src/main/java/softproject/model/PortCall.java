package softproject.model;


import java.io.Serializable;
import java.time.LocalDateTime;

public class PortCall implements Serializable{

    private static int portCallCount = 0;

    private long internalId;
    private String comment;
    private String cargoIn;
    private String cargoOut;
    private LocalDateTime laycanStart;
    private LocalDateTime laycanEnd;

    public PortCall() {
        portCallCount++;
        internalId = portCallCount;
    }

    public long getInternalId(){
        return internalId;
    }

    public static int getPortCallCount() {
        return portCallCount;
    }

    public static void setPortCallCount(int portCallCount) {
        PortCall.portCallCount = portCallCount;
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

    public LocalDateTime getLaycanStart() {
        return laycanStart;
    }

    public void setLaycanStart(LocalDateTime laycanStart) {
        this.laycanStart = laycanStart;
    }

    public LocalDateTime getLaycanEnd() {
        return laycanEnd;
    }

    public void setLaycanEnd(LocalDateTime laycanEnd) {
        this.laycanEnd = laycanEnd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
