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
        state.setTime(zonedDateTime.toLocalDateTime());
        return this;
    }

    public LocationStateBuilder timeType(TimeType timeType) {
        state.setTimeType(timeType);
        return this;
    }

    public LocationStateBuilder arrivalLocation(String toLocationMRN, Position toPosition,
                                                String fromLocationMRN, Position fromPosition) {

        LocationState.ArrivalLocation arrivalLocation = new LocationState.ArrivalLocation();
        arrivalLocation.setTo(createLocation(toLocationMRN, toPosition));
        arrivalLocation.setFrom(createLocation(fromLocationMRN, fromPosition));

        state.setArrivalLocation(arrivalLocation);
        return this;
    }

    public LocationStateBuilder arrivalLocation(String toLocationMRN, Position toPosition) {
        LocationState.ArrivalLocation arrivalLocation = new LocationState.ArrivalLocation();
        arrivalLocation.setTo(createLocation(toLocationMRN, toPosition));

        state.setArrivalLocation(arrivalLocation);
        return this;
    }

    public LocationStateBuilder departureLocation(String fromLocationMRN, Position fromPosition,
                                                  String toLocationMRN, Position toPosition) {


        LocationState.DepartureLocation departureLocation = new LocationState.DepartureLocation();
        departureLocation.setTo(createLocation(toLocationMRN, toPosition));
        departureLocation.setFrom(createLocation(fromLocationMRN, fromPosition));

        state.setDepartureLocation(departureLocation);
        return this;
    }

    public LocationStateBuilder departureLocation(String fromLocationMRN, Position fromPosition) {
        LocationState.DepartureLocation departureLocation = new LocationState.DepartureLocation();
        departureLocation.setFrom(createLocation(fromLocationMRN, fromPosition));

        state.setDepartureLocation(departureLocation);
        return this;
    }



    public LocationState build() {
        return state;
    }





}
