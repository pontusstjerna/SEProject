package softproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softproject.services.PortCDMRequest;
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

            return "index";
        }
        else return "login";
    }

    @GetMapping("/portcall")
    public String portcall() {
        return "portcallView";
    }

    @GetMapping("/unscheduled")
    public String getUnscheduled() {
        return "unscheduled";
    }
}
