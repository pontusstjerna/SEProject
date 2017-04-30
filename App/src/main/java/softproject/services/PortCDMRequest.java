package softproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.squareup.okhttp.*;
import eu.portcdm.mb.dto.Filter;
import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.PortCallMessage;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Johan on 28/04/2017.
 */
public class PortCDMRequest {

    public String createQueue() {
        OkHttpClient client = new OkHttpClient();

        Filter filter = new Filter();
        filter.setType(FilterType.VESSEL);
        filter.setElement("urn:x-mrn:stm:vessel:IMO:9398917");

        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            System.out.println(Arrays.asList(mapper.writeValueAsString(filter)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        RequestBody requestBody = null;
        try {
            requestBody = RequestBody.create(MediaType.parse("application/json"), mapper.writeValueAsString(Arrays.asList(filter)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Request request = new Request.Builder()
                .header("X-PortCDM-UserId", "porter")
                .header("X-PortCDM-Password", "porter")
                .header("X-PortCDM-APIKey", "eeee")
                .url("http://192.168.56.101:8080/mb/mqs")
                .post(requestBody)
                .build();

        Response response = null;
        String queue = "";

        try {
            response = client.newCall(request).execute();
            System.out.println(response.code());

//            System.out.println(response.body().string());
            queue = response.body().string();
            System.out.println(queue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // NOW WE HAVE A QUEUE ID!!!
        return queue;
    }

    public String getNewMessages(String queue){
        OkHttpClient client = new OkHttpClient();


        Request getRequest = new Request.Builder()
                .header("X-PortCDM-UserId", "porter")
                .header("X-PortCDM-Password", "porter")
                .header("X-PortCDM-APIKey", "eeee")
                .url("http://192.168.56.101:8080/mb/mqs/" + queue)
                .build();

        Response response = null;
        try {
            response = client.newCall(getRequest).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = "";

        try {
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        XmlMapper mapper = new XmlMapper();
        try {
            List<PortCallMessage> messages = mapper.readValue(result, new TypeReference<List<PortCallMessage>>() { });
            System.out.println(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
