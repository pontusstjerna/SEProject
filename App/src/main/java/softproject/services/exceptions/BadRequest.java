package softproject.services.exceptions;


public class BadRequest extends Throwable {
    public BadRequest(int responseCode) {
        super("Bad request, returned with error code" + Integer.toString(responseCode));
    }
}
