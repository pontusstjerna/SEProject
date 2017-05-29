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
                    .header("X-PortCDM-UserId", "test1")
                    .header("X-PortCDM-Password", "test123")

                    .header("X-PortCDM-APIKey", "Terminal 2")
                    .url("http://sandbox-5.portcdm.eu:8080")
                    .build();
        }

        return baseRequest;
    }
}
