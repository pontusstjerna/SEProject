package softproject.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
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
}
