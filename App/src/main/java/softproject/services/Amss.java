package softproject.services;

import com.squareup.okhttp.*;
import eu.portcdm.messaging.PortCallMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;



public class Amss {

    public static final String API_URL_ADDITION = "amss";
    public static final String STATE_UPDATE_URL_ADDITION = "/state_update";
    public static final String MEDIA_TYPE_STRING = "application/xml";

    private OkHttpClient httpClient;
    private Request amssRequest;
    private MediaType mediaType;


    public Amss(OkHttpClient httpClient, Request baseRequest) {
        this.httpClient = httpClient;
        this.amssRequest = baseRequest.newBuilder()
                .url(baseRequest.urlString() + API_URL_ADDITION)
                .build();

        this.mediaType = MediaType.parse(MEDIA_TYPE_STRING);
    }

    public boolean postStateUpdate(PortCallMessage message) {

        String messageAsXML = convertPortCallMessageToXML(message);

        RequestBody requestBody = null;
        requestBody = RequestBody.create(mediaType, messageAsXML);
        Request request = this.amssRequest.newBuilder()
                .url(this.amssRequest.urlString() + STATE_UPDATE_URL_ADDITION)
                .post(requestBody)
                .build();

        return sendRequest(message, request);
        // TODO fixa error hantering osv
    }

    public boolean postMSS(PortCallMessage message) {
        String messageAsXML = convertPortCallMessageToXML(message);

        RequestBody requestBody = null;
        requestBody = RequestBody.create(mediaType, messageAsXML);

        Request request = PortCDMRequest.getBaseRequest().newBuilder()
                .url(PortCDMRequest.getBaseRequest().urlString() + "mb/mss")
                .post(requestBody)
                .build();

        return sendRequest(message, request);
    }


    private boolean sendRequest(PortCallMessage message, Request request) {
        Response response = null;
        try {
            response = this.httpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        System.out.println(response.code());
        return true;
    }


    private String convertPortCallMessageToXML(PortCallMessage message) {
        String messageAsXML = "";
        try {
            Marshaller marshaller = null;
            marshaller = JAXBContext.newInstance(new Class[] {message.getClass()}).createMarshaller();
            StringWriter sw = new StringWriter();
            marshaller.marshal(message, sw);
            messageAsXML = sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return messageAsXML;
    }
}
