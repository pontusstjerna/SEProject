package softproject.services.exceptions;

public class IncompletePortCallMessage extends Exception {
    public IncompletePortCallMessage() {
        super("A PortCallMessage requires messageId, vesselId and one of: locationState/serviceState");
    }
}
