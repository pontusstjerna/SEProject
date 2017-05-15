package softproject.util;

import eu.portcdm.messaging.*;
import java.time.ZonedDateTime;


public class LocationStateBuilder extends StateBuilder{

    private LocationState state = new LocationState();

    private LocationStateBuilder() {}

    public static LocationStateBuilder newBuilder() {
        return new LocationStateBuilder();
    }

    public LocationStateBuilder referenceObject(LocationReferenceObject locationReferenceObject) {
        state.setReferenceObject(locationReferenceObject);
        return this;
    }

    public LocationStateBuilder time(ZonedDateTime zonedDateTime) {
        state.setTime(createXmlGregorianCalendar(zonedDateTime));
        return this;
    }

    public LocationStateBuilder timeType(TimeType timeType) {
        state.setTimeType(timeType);
        return this;
    }

    public LocationStateBuilder arrivalLocation(String toName, LogicalLocation toLogicalLocation, Position toPosition,
                                                String fromName, LogicalLocation fromLogicalLocation, Position fromPosition) {

        LocationState.ArrivalLocation arrivalLocation = new LocationState.ArrivalLocation();
        arrivalLocation.setTo(createLocation(toName, toLogicalLocation, toPosition));
        arrivalLocation.setFrom(createLocation(fromName, fromLogicalLocation, fromPosition));

        state.setArrivalLocation(arrivalLocation);
        return this;
    }

    public LocationStateBuilder arrivalLocation(String toName, LogicalLocation toLogicalLocation, Position toPosition) {
        return arrivalLocation(toName, toLogicalLocation, toPosition, null, null, null);
    }

    public LocationStateBuilder departureLocation(String fromName, LogicalLocation fromLogicalLocation, Position fromPosition,
                                                  String toName, LogicalLocation toLogicalLocation, Position toPosition) {


        LocationState.DepartureLocation departureLocation = new LocationState.DepartureLocation();
        departureLocation.setTo(createLocation(toName, toLogicalLocation, toPosition));
        departureLocation.setFrom(createLocation(fromName, fromLogicalLocation, fromPosition));

        state.setDepartureLocation(departureLocation);
        return this;
    }

    public LocationStateBuilder departureLocation(String fromName, LogicalLocation fromLogicalLocation, Position fromPosition) {
        return departureLocation(fromName, fromLogicalLocation, fromPosition, null, null, null);
    }









}
