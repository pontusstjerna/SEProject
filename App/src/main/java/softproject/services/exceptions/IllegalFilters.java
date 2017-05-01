package softproject.services.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by johan on 01/05/2017.
 */
public class IllegalFilters extends Exception {
    public IllegalFilters() {
        super("Could not parse the list of Filters to JSON");
    }
}
