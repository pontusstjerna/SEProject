package softproject.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import softproject.model.Vessel;
import softproject.services.exceptions.BadRequest;
import softproject.services.exceptions.CouldNotReachPortCDM;

import java.io.IOException;

public class VesselRegistryService {

    private static final String API_URL_ADDITION = "vr";
    private static final String VESSEL_URL_ADDITION = "/vessel/";


    private OkHttpClient httpClient;
    private Request baseRequest;

    public VesselRegistryService(OkHttpClient httpClient, Request baseRequest) {
        this.httpClient = httpClient;
        this.baseRequest = baseRequest.newBuilder()
                .url(baseRequest.urlString() + API_URL_ADDITION)
                .build();
    }

    public Vessel getVessel(String vesselURN) {
        Request request = this.baseRequest.newBuilder()
                .url(this.baseRequest.urlString() + VESSEL_URL_ADDITION + vesselURN)
                .build();

        Response response = null;

        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new CouldNotReachPortCDM(e, "from VesselRegistryService::getVessel");
        }
        int resultCode = response.code();
        switch(resultCode) {
            case 500:
                System.err.println("Error code 500, call parameters are missing or invalid");
                System.err.println("parameter was: " + vesselURN);
                throw new BadRequest(resultCode);
            case 401:
                System.err.println("Error code 401, authentication headers missing!");
                throw new BadRequest(resultCode);
            case 404:
                System.err.println("Error code 404, the vessel does not exist!");
                System.err.println("paramter was: " + vesselURN);
                return null;
        }

        String result = "";
        try {
            result = response.body().string();
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return parseJson(result);
    } // END getVessel

    private Vessel parseJson(String vesselAsJson) {
        ObjectMapper mapper = new ObjectMapper();
        Vessel vessel = null;
        try {
            vessel = mapper.readValue(vesselAsJson, Vessel.class);
        } catch (IOException e) {
            System.err.println("Could not parse the vessel from JSON");
            e.printStackTrace();
        }

        return vessel;
    } // END parseJson
} // END class
