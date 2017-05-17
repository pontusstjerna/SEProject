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

        state.setTime(createXmlGregorianCalendar(zonedDateTime));
        return this;
    }

    public ServiceStateBuilder timeType (TimeType timeType){
        state.setTimeType(timeType);
        return this;
    }

    public ServiceStateBuilder at (String name, LogicalLocation logicalLocation, Position position){
        Location location = createLocation(name, logicalLocation, position);
        state.setAt(location);
        return this;
    }

    public ServiceStateBuilder between (String toName, LogicalLocation toLogicalLocation, Position toPosition,
                                        String fromName, LogicalLocation fromLogicalLocation, Position fromPosition){
        Location toLocation = createLocation(toName, toLogicalLocation, toPosition);
        Location fromLocation = createLocation(fromName, fromLogicalLocation, fromPosition);
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
