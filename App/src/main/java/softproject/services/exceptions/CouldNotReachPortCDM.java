package softproject.services.exceptions;

import java.io.IOException;

/**
 * Created by johan on 01/05/2017.
 */
public class CouldNotReachPortCDM extends Throwable {
    public CouldNotReachPortCDM(IOException e, String message) {
        super("Error getting a response from portCDM " + message);
    }
}
