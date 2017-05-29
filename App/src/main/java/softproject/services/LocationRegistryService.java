package softproject.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import eu.portcdm.messaging.Location;
import softproject.services.exceptions.BadRequest;
import softproject.services.exceptions.CouldNotReachPortCDM;

import java.io.IOException;

public class LocationRegistryService {

    private static final String API_URL_ADDITION = "location-registry";
    private static final String LOCATION_URL_ADDITION = "/location/";

    private MediaType mediaType = MediaType.parse("application/json");

    private OkHttpClient httpClient;
    private Request baseRequest;

    public LocationRegistryService(OkHttpClient httpClient, Request baseRequest) {
        this.httpClient = httpClient;
        this.baseRequest = baseRequest.newBuilder()
                .url(baseRequest.urlString() + API_URL_ADDITION)
                .build();
    }

    public boolean doesLocationMRNExist(String locationURN) {
        Request request = this.baseRequest.newBuilder()
                .url(this.baseRequest.urlString() + LOCATION_URL_ADDITION + locationURN)
                .build();

        Response response = null;

        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotReachPortCDM(e, "from LocationRegistryService::getLocation");
        }
        int resultCode = response.code();
        switch(resultCode) {
            case 404:
                System.err.println("Error code 404, the locationURN does not exist!");
                System.err.println("paramter was: " + locationURN);
                return false;
        }

        return true;
    } // END getVessel
}
