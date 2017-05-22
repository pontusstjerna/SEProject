package softproject.util;

import eu.portcdm.messaging.Location;
import eu.portcdm.messaging.LogicalLocation;
import eu.portcdm.messaging.Position;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;


abstract class StateBuilder {

    XMLGregorianCalendar createXmlGregorianCalendar(ZonedDateTime dateTime) {
        GregorianCalendar gregCal = GregorianCalendar.from(dateTime);
        XMLGregorianCalendar xmlGregCal = null;
        try {
            xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregCal);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }

        return xmlGregCal;
    }

    Location createLocation(String locationMRN, Position position){
        Location location = new Location();
        location.setLocationMRN(locationMRN);
        location.setPosition(position);
        return location;
    }

}
