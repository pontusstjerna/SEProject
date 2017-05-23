package softproject.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.squareup.okhttp.*;
import eu.portcdm.mb.dto.Filter;
import eu.portcdm.messaging.PortCallMessage;
import softproject.services.exceptions.BadRequest;
import softproject.services.exceptions.CouldNotReachPortCDM;
import softproject.services.exceptions.IllegalFilters;

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

        return executeRequest(request);
    }

    // Tries to create a queue using filters and fromTime sent in as parameters
    public String postMqs(List<Filter> filters, String fromTime) throws IllegalFilters, CouldNotReachPortCDM, BadRequest {
        RequestBody reqBody = createRequestBody(filters);

        Request request = this.baseRequest.newBuilder()
                .url(this.baseRequest.urlString() + "/mqs?fromTime="+fromTime)
                .post(reqBody)
                .build();

        return executeRequest(request);
    }

    // Tries to create a queue using fromTime sent in as parameter
    public String postMqs(String fromTime) throws IllegalFilters, CouldNotReachPortCDM, BadRequest {
        Request request = this.baseRequest.newBuilder()
                .url(this.baseRequest.urlString() + "/mqs?fromTime="+fromTime)
                .post(RequestBody.create(MediaType.parse("application/json"),""))
                .build();

        return executeRequest(request);
    }

    private String executeRequest(Request request) {
        try {
            Response response = this.httpClient.newCall(request).execute();
            if(response.isSuccessful()) {
                String result = response.body().string();
                response.body().close();
                return result;
            } else {
                throw new BadRequest(response.code());
            }
        } catch (IOException e) {
            throw new CouldNotReachPortCDM(e, "from MessageQueueService::postMqs(List<Filter> filters, String fromTime)");
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
        throw new UnsupportedOperationException();
    }

    public List<PortCallMessage> getMqs(String queue) throws CouldNotReachPortCDM, BadRequest {
        return getMqs(queue, 100);
    }

    public List<PortCallMessage> getMqs(String queue, int limit) throws CouldNotReachPortCDM, BadRequest {
        Request request = this.baseRequest.newBuilder()
                .url(this.baseRequest.urlString() + "/mqs/" + queue + "?count=" + limit)
                .build();


        try {
            Response response = this.httpClient.newCall(request).execute();
            if(response.isSuccessful()) {
                String messagesAsXML = response.body().string();
                response.body().close();
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
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(messagesAsXML, new TypeReference<List<PortCallMessage>>() {});
    }


}
