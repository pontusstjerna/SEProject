package softproject.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pontu on 2017-04-24.
 */


@RestController
public class ShipController {

    @RequestMapping("/ship")
    public String getShip(){
        return "This is a ship.";
    }
}
