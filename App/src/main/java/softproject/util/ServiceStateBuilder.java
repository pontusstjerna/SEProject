package softproject.util;


import eu.portcdm.messaging.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

public class ServiceStateBuilder extends StateBuilder{

    private ServiceState state = new ServiceState();

    private ServiceStateBuilder() {}

    public static ServiceStateBuilder newBuilder(){
        return new ServiceStateBuilder();
    }

    public ServiceStateBuilder serviceObject (ServiceObject serviceObject){
        state.setServiceObject(serviceObject);
        return this;
    }

    public ServiceStateBuilder performingActor (String performingActor){
        state.setPerformingActor(performingActor);
        return this;
    }

    public ServiceStateBuilder timeSequence (ServiceTimeSequence serviceTimeSequence){
        state.setTimeSequence(serviceTimeSequence);
        return this;
    }

    public ServiceStateBuilder time (ZonedDateTime zonedDateTime){

        state.setTime(zonedDateTime.toLocalDateTime());
        return this;
    }

    public ServiceStateBuilder timeType (TimeType timeType){
        state.setTimeType(timeType);
        return this;
    }

    public ServiceStateBuilder at (String locationMRN, Position position){
        Location location = createLocation(locationMRN, position);
        state.setAt(location);
        return this;
    }

    public ServiceStateBuilder between (String toLocationMRN, Position toPosition,
                                        String fromlocationMRN, Position fromPosition){
        Location toLocation = createLocation(toLocationMRN, toPosition);
        Location fromLocation = createLocation(fromlocationMRN, fromPosition);
        ServiceState.Between between = new ServiceState.Between();
        between.setTo(toLocation);
        between.setFrom(fromLocation);
        state.setBetween(between);
        return this;
    }

    public ServiceState build (){
        return state;
    }

}
