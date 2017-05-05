package softproject.controllers;

import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softproject.services.PortCDMRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

/**
 * Created by skyw on 4/20/17.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String login(@RequestParam Map<String, String> requestParams) {
        String username = requestParams.get("user");
        String password = requestParams.get("password");

        if (username != null && password != null && username.equals("potatis") && password.equals("kakor")) {
            PortCDMRequest req = new PortCDMRequest();
            req.subscribe();
            return "index";
        }
        else return "login";
    }

    @GetMapping("/portcallx")
    public String portcall(){
        return "portcallView";
    }
}
