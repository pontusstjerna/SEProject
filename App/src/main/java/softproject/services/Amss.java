package softproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.squareup.okhttp.*;
import eu.portcdm.messaging.PortCallMessage;

import java.io.IOException;


/**
 * Created by johan on 01/05/2017.
 */
public class Amss {

    public static final String API_URL_ADDITION = "dmp/mss";
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
        ObjectWriter writer = new XmlMapper().writer().withDefaultPrettyPrinter();



        RequestBody reqBody = null;
        String portCallAsXML = "";
        try {
            portCallAsXML = writer.writeValueAsString(message);
            reqBody = RequestBody.create(mediaType, writer.writeValueAsString(message));
            Request request = this.amssRequest.newBuilder()
                    .url(this.amssRequest.urlString() + STATE_UPDATE_URL_ADDITION)
                    .post(reqBody)
                    .build();

            // TODO problem, får 400, bad request hela tiden. XML formatet ser inte helt ok ut heller
            Response response = this.httpClient.newCall(request).execute();
            switch(response.code()) {
                case 202:
                    System.out.println("202");
                    break;
                case 400:
                    System.out.println("400");
                    break;
                case 401:
                    System.out.println("401");
                    break;
                case 404:
                    System.out.println("404");
                    break;
            }


        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }


        return true;
    }
}
