package softproject.controllers;

import lombok.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softproject.services.PortCDMRequest;

import java.util.Date;
import java.util.Map;

/**
 * Created by skyw on 4/20/17.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        PortCDMRequest req = new PortCDMRequest();

        req.fixSubscription();
        return "index";
    }
}
