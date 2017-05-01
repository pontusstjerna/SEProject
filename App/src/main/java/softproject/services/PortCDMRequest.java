package softproject.services;

import com.squareup.okhttp.*;
import eu.portcdm.mb.dto.Filter;
import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.*;
import softproject.services.exceptions.CouldNotReachPortCDM;
import softproject.services.exceptions.IllegalFilters;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class PortCDMRequest {

    private static OkHttpClient clientInstance = null;
    private static Request baseRequest = null;

    public static OkHttpClient getClientInstance() {
        if(clientInstance == null)
            clientInstance = new OkHttpClient();

        return clientInstance;
    }

    public static Request getBaseRequest() {
        if(baseRequest == null) {
            baseRequest = new Request.Builder()
                    .header("X-PortCDM-UserId", "porter")
                    .header("X-PortCDM-Password", "porter")
                    .header("X-PortCDM-APIKey", "eeee")
                    .url("http://192.168.56.101:8080")
                    .build();
        }

        return baseRequest;
    }

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
        }

        return result;


//        OkHttpClient client = new OkHttpClient();
//
//        Filter filter = new Filter();
//        filter.setType(FilterType.VESSEL);
//        filter.setElement("urn:x-mrn:stm:vessel:IMO:9398917");
//
//        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        try {
//            System.out.println(Arrays.asList(mapper.writeValueAsString(filter)));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        RequestBody requestBody = null;
//        try {
//            requestBody = RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(Arrays.asList(filter)));
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        Request request = new Request.Builder()
//                .header("X-PortCDM-UserId", "porter")
//                .header("X-PortCDM-Password", "porter")
//                .header("X-PortCDM-APIKey", "eeee")
//                .url("http://192.168.56.101:8080/mb/mqs")
//                .post(requestBody)
//                .build();
//
//        Response response = null;
//        String queue = "";
//
//        try {
//            response = client.newCall(request).execute();
//            System.out.println(response.code());
//
////            System.out.println(response.body().string());
//            queue = response.body().string();
//            System.out.println(queue);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // NOW WE HAVE A QUEUE ID!!!
//        return queue;
    }

    public String getNewMessages(String queue){
        MessageQueueService mqs = new MessageQueueService(PortCDMRequest.getClientInstance(), PortCDMRequest.getBaseRequest());

        List<PortCallMessage> messages = null;
        try {
            messages = mqs.getMqs(queue);
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            couldNotReachPortCDM.printStackTrace();
        }

        System.out.println(messages);
        return messages.toString();


//        OkHttpClient client = new OkHttpClient();
//
//
//        Request getRequest = new Request.Builder()
//                .header("X-PortCDM-UserId", "porter")
//                .header("X-PortCDM-Password", "porter")
//                .header("X-PortCDM-APIKey", "eeee")
//                .url("http://192.168.56.101:8080/mb/mqs/" + queue)
//                .build();
//
//        Response response = null;
//        try {
//            response = client.newCall(getRequest).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String result = "";
//
//        try {
//            result = response.body().string();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        XmlMapper mapper = new XmlMapper();
//        try {
//            List<PortCallMessage> messages = mapper.readValue(result, new TypeReference<List<PortCallMessage>>() { });
//            System.out.println(messages);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//        return result;
    }

    public String sendMessage() {
        Amss amss = new Amss(getClientInstance(), getBaseRequest());

        PortCallMessage message = new PortCallMessage();
        LocationState locationState = new LocationState();
        LocationState.ArrivalLocation arrivalLocation = new LocationState.ArrivalLocation();
        Location location = new Location();
        location.setLocationType(LogicalLocation.BERTH);

        message.setMessageId("urn:x-mrn:stm:portcdm:message:" + UUID.randomUUID().toString());
        message.setVesselId("urn:x-mrn:stm:vessel:IMO:9398917");
        message.setPortCallId("urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac");

        locationState.setArrivalLocation(arrivalLocation);
        locationState.setReferenceObject(LocationReferenceObject.VESSEL);
        locationState.setTimeType(TimeType.ESTIMATED);
        try {
            locationState.setTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(2017,05,01,12,00,00,00,0));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        arrivalLocation.setTo(location);


        message.setLocationState(locationState);

        amss.postStateUpdate(message);

        return "";
    }
}
