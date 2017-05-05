package softproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.squareup.okhttp.*;
import eu.portcdm.mb.dto.Filter;
import eu.portcdm.messaging.PortCallMessage;
import softproject.services.exceptions.BadRequest;
import softproject.services.exceptions.CouldNotReachPortCDM;
import softproject.services.exceptions.IllegalFilters;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.util.List;


public class MessageQueueService {
    private OkHttpClient httpClient;
    private MediaType mediaType;
    private Request baseRequest;


    public MessageQueueService(OkHttpClient httpClient, Request baseRequest) {
        this.httpClient = httpClient;
        this.mediaType = MediaType.parse("application/json");

        this.baseRequest = baseRequest.newBuilder()
                .url(baseRequest.urlString() + "mb")
                .build();

    }

    // Tries to create a queue using filters sent in as parameters
    public String postMqs(List<Filter> filters) throws IllegalFilters, CouldNotReachPortCDM, BadRequest {
        RequestBody reqBody = createRequestBody(filters);

        Request request = this.baseRequest.newBuilder()
                .url(this.baseRequest.urlString() + "/mqs")
                .post(reqBody)
                .build();

        try {
            Response response = this.httpClient.newCall(request).execute();
            if(response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new BadRequest(response.code());
            }
        } catch (IOException e) {
            throw new CouldNotReachPortCDM(e, "from MessageQueueService::postMqs(List<Filter>");
        }


    }

    // creates the RequestBody for a postMqs call, converting filters to a JSON array
    private RequestBody createRequestBody(List<Filter> filters) throws IllegalFilters {
        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        RequestBody reqBody;
        try {
            reqBody = RequestBody.create(mediaType, writer.writeValueAsString(filters));
        } catch (JsonProcessingException e) {
            throw new IllegalFilters();
        }
        return reqBody;
    }

    public void putMqs(String queue, List<Filter> filters) {
        throw new NotImplementedException();
    }

    public List<PortCallMessage> getMqs(String queue) throws CouldNotReachPortCDM, BadRequest {
        return getMqs(queue, 100);
    }

    public List<PortCallMessage> getMqs(String queue, int limit) throws CouldNotReachPortCDM, BadRequest {
        Request request = this.baseRequest.newBuilder()
                .url(this.baseRequest.urlString() + "/mqs/" + queue + "?count=" + limit)
                .build();

        Response response = null;
        String messagesAsXML = "";
        try {
            response = this.httpClient.newCall(request).execute();
            if(response.isSuccessful()) {
                messagesAsXML = response.body().string();
                return convertFromXmlToPortCall(messagesAsXML);
            } else {
                throw new BadRequest(response.code());
            }
        } catch (IOException e) {
            throw new CouldNotReachPortCDM(e, "from MessageQueueService::getMqs(String, int)");
        }
    }

    private List<PortCallMessage> convertFromXmlToPortCall(String messagesAsXML) throws IOException {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(messagesAsXML, new TypeReference<List<PortCallMessage>>() {});
    }


}
