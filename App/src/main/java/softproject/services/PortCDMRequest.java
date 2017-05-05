package softproject.services;

import com.squareup.okhttp.*;
import eu.portcdm.mb.dto.Filter;
import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.*;
import softproject.services.exceptions.BadRequest;
import softproject.services.exceptions.CouldNotReachPortCDM;
import softproject.services.exceptions.IllegalFilters;
import softproject.util.PortCallMessageBuilder;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PortCDMRequest {

    private static OkHttpClient clientInstance = null;
    private static Request baseRequest = null;

    public static OkHttpClient getClientInstance() {
        if (clientInstance == null)
            clientInstance = new OkHttpClient();

        return clientInstance;
    }

    public static Request getBaseRequest() {
        if (baseRequest == null) {
            baseRequest = new Request.Builder()
                    .header("X-PortCDM-UserId", "porter")
                    .header("X-PortCDM-Password", "porter")
                    .header("X-PortCDM-APIKey", "eeee")
                    .url("http://192.168.56.101:8080")
                    .build();
        }

        return baseRequest;
    }

    // TEST METODER FÖR ATT SE OM BIBLIOTEKET FUNGERAR
    public String createQueue() {
        MessageQueueService mqs = new MessageQueueService(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());

        Filter filter = new Filter();
        filter.setType(FilterType.VESSEL);
        filter.setElement("urn:x-mrn:stm:vessel:IMO:9398917");

        String result = "";
        try {
            result = mqs.postMqs(Arrays.asList(filter));
        } catch (IllegalFilters illegalFilters) {
            System.out.println("ILLEGAL FILTERS!!!");
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            System.out.println("COULD NOT CONNECT");
        } catch (BadRequest e) {
            System.out.println("Badly formed request");
        }

        return result;
    }

    /// public String getNewMessages(String queue){
    ///     MessageQueueService mqs = new MessageQueueService(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());

    ///     List<PortCallMessage> messages = null;
    ///     try {
    ///         messages = mqs.getMqs(queue);
    ///     } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
    ///         couldNotReachPortCDM.printStackTrace();
    ///     } catch (BadRequest badRequest) {
    ///         badRequest.printStackTrace();
    ///     }

    ///     System.out.println(messages);
    // return messages.toString();
    //}

    public String sendMessage() {
        Amss amss = new Amss(getClientInstance(), getBaseRequest());

        XMLGregorianCalendar time = null;
        try {
            time = DatatypeFactory.newInstance().newXMLGregorianCalendar(2017, 05, 01, 12, 00, 00, 00, 0);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        LocationState.ArrivalLocation arrivalLocation = new LocationState.ArrivalLocation();
        Location location = new Location();
        location.setLocationType(LogicalLocation.BERTH);
        arrivalLocation.setTo(location);

        PortCallMessage message = PortCallMessageBuilder.newBuilder()
                .vesselId("urn:x-mrn:stm:vessel:IMO:9398917")
                .portCallId("urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac")
                .locationState(LocationReferenceObject.VESSEL, TimeType.RECOMMENDED, time, arrivalLocation, null)
                .build();

        if (message != null)
            amss.postStateUpdate(message);
        return "";
    }

    public String subscribe() {
        Filter filter1 = new Filter();
        filter1.setType(FilterType.SERVICE_STATE_LOCATION_TYPE);
        filter1.setElement("BERTH");

        Filter filter2 = new Filter();
        filter2.setType(FilterType.VESSEL);
        filter2.setElement("urn:x-mrn:stm:vessel:IMO:9398917");

        MessageQueueService queueService = new MessageQueueService(getClientInstance(), getBaseRequest());

        String queueId = "";

        try {
            queueId = queueService.postMqs(Arrays.asList(filter1, filter2));
        } catch (IllegalFilters illegalFilters) {
            illegalFilters.printStackTrace();
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            couldNotReachPortCDM.printStackTrace();
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }


        // §List<PortCallMessage> messages = null;
        // §try {
        // §    messages = queueService.getMqs(queueId);
        // §} catch (CouldNotReachPortCDM couldNotReachPortCDM) {
        // §    couldNotReachPortCDM.printStackTrace();
        // §} catch (BadRequest badRequest) {
        // §    badRequest.printStackTrace();
        // §}

        XMLGregorianCalendar time = null;
        try {
            time = DatatypeFactory.newInstance().newXMLGregorianCalendar(2017, 05, 01, 12, 00, 00, 00, 0);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        // ServiceState serviceState = new ServiceState();
        // serviceState.setServiceObject(ServiceObject.ARRIVAL_BERTH);
        // serviceState.setTimeSequence(ServiceTimeSequence.CONFIRMED);
        // serviceState.setTimeType(TimeType.ESTIMATED);
        // serviceState.setTime(time);

        // Location location = new Location();
        // location.setLocationType(LogicalLocation.BERTH);

        // serviceState.setAt(location);

//       LocationState.ArrivalLocation arrivalLocation = new LocationState.ArrivalLocation();
//       arrivalLocation.setTo(location);

        // PortCallMessage newMessage = PortCallMessageBuilder.newBuilder()
        //         .vesselId("urn:x-mrn:stm:vessel:IMO:9398917")
        //         .portCallId("urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac")
        //         .serviceState(serviceState)
//      //           .locationState(LocationReferenceObject.VESSEL,
//      //                   TimeType.ESTIMATED,
//      //                   time,
//      //                   arrivalLocation,
//      //                   null)
        //         .build();

        // PortCallMessage newMessage2 = PortCallMessageBuilder.newBuilder()
        //         .vesselId("urn:x-mrn:stm:vessel:IMO:9398910")
        //         .portCallId("urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac")
        //         .serviceState(serviceState)
//      //           .locationState(LocationReferenceObject.VESSEL,
//      //                   TimeType.ESTIMATED,
//      //                   time,
//      //                   arrivalLocation,
//      //                   null)
        //         .build();

        // amss.postStateUpdate(newMessage);
        // amss.postStateUpdate(newMessage2);

        return queueId;
    }

    public List<PortCallMessage> getNewMessages(String queueId) {
        List<PortCallMessage> messages = null;
        MessageQueueService queueService = new MessageQueueService(getClientInstance(), getBaseRequest());
        try {
            messages = queueService.getMqs(queueId);
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            couldNotReachPortCDM.printStackTrace();
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }

        //Filtrera ut alla meddeleanden för kaj 520
        messages.stream().forEach(m->filterBerth(m));

        return messages;
    }

    public List<PortCallMessage> filterBerth(PortCallMessage message){
        List<PortCallMessage> ourMessages = new ArrayList<>();

        ServiceState state = message.getServiceState();
        String name = state.getBetween().getTo().getName();
        if (name.contains("520")) {
            ourMessages.add(message);
        }
        return ourMessages;
    }


//    private String apa() {
//        for ( QueueFilter filter : collect ) {
//            Predicate innerPredicate;
//            switch ( filter.getType() ) {
//                case VESSEL:
//                    innerPredicate = builder.equal( join.get( "vesselId" ), filter.getElement() );
//                    break;
//                case PORT_CALL:
//                    innerPredicate = builder.equal( join.get( "portCallId" ), filter.getElement() );
//                    break;
//                case TIME_TYPE:
//                    TimeType timeType = TimeType.valueOf( filter.getElement() );
//                    innerPredicate = builder.or( builder.equal( join.get( "locationState" ).get( "timeType" ), timeType ), builder.equal( join.get( "serviceState" ).get( "timeType" ), timeType ) );
//                    break;
//                case REFERENCE_OBJECT:
//                    innerPredicate = builder.equal( join.get( "locationState" ).get( "referenceObject" ), LocationReferenceObject.valueOf( filter.getElement() ) );
//                    break;
//                case TIME_SEQUENCE:
//                    innerPredicate = builder.equal( join.get( "serviceState" ).get( "timeSequence" ), ServiceTimeSequence.valueOf( filter.getElement() ) );
//                    break;
//                case LOCATION_TYPE:
//                    LogicalLocation locationType = LogicalLocation.valueOf( filter.getElement() );
//                    innerPredicate = builder.or(
//                            builder.equal( join.get( "serviceState" ).get( "at" ).get( "locationType" ), locationType ),
//                            builder.equal( join.get( "serviceState" ).get( "between" ).get( "to" ).get( "locationType" ), locationType ),
//                            builder.equal( join.get( "serviceState" ).get( "between" ).get( "from" ).get( "locationType" ), locationType ),
//                            builder.equal( join.get( "locationState" ).get( "arrivalLocation" ).get( "to" ).get( "locationType" ), locationType ),
//                            builder.equal( join.get( "locationState" ).get( "arrivalLocation" ).get( "from" ).get( "locationType" ), locationType ),
//                            builder.equal( join.get( "locationState" ).get( "departureLocation" ).get( "to" ).get( "locationType" ), locationType ),
//                            builder.equal( join.get( "locationState" ).get( "departureLocation" ).get( "from" ).get( "locationType" ), locationType )
//                    );
//                    break;
//                default:
//                    continue;
//            }
//
//            if ( predicateTMP == null )
//                predicateTMP = innerPredicate;
//            else
//                predicateTMP = builder.or( predicateTMP, innerPredicate );
//        }
//    }

}
