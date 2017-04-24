package softproject.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by pontu on 2017-04-24.
 */
@RestController
public class HomeRestController {

    @CrossOrigin
    @RequestMapping("/timestamp")
    public String getTimestamp(){
        return "Here is a timestamp from java acquired our own REST api: " + new Date();
    }
}
