package softproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by skyw on 5/6/17.
 */

@Controller
public class DockController {
    @GetMapping("/docks")
    public String getDocks(){
        System.out.println("Hello");
        return "docks";
    }
}
