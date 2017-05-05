package softproject.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import softproject.HTTPRequests;

/**
 * Created by pontu on 2017-04-26.
 */

@RestController
public class PortCallController {

    @RequestMapping("/port_calls")
    public String getPortCalls(){
        //Detta är Pontuss VBoxs superfina IP
        return HTTPRequests.executeGet("http://192.168.56.101:8080/dmp/port_calls?count=1");
    }

    @RequestMapping("/portcall")
    public DummyPortCall portcall(@RequestParam(value="id") String id) {
        int portId = Integer.parseInt(id);
        //Plocka ur lista egentligen
        return new DummyPortCall();
    }

    private class DummyPortCall{
        private int id;
        private String vesselName;

        public DummyPortCall(){
            id = 1337;
            vesselName = "Superfint anrop";
        }

        public int getId(){
            return id;
        }

        public String getVesselName(){
            return vesselName;
        }
    }
}
