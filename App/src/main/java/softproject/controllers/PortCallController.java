package softproject.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softproject.model.PortCall;
import softproject.model.PortCallRepository;

import java.util.List;


/**
 * Created by pontu on 2017-04-26.
 */

@RestController
public class PortCallController {

    @PostMapping("/portcalls/add")
    public void addPortCall(@RequestBody PortCall newPortCall) {
        System.out.println(newPortCall.getLaycanStart());
        System.out.println(newPortCall.getLaycanEnd());
        PortCallRepository.getRepo().add(newPortCall);
    }

    @GetMapping("/portcalls")
    public List<PortCall> getAllPortCalls() {
        return PortCallRepository.getRepo().getAll();
    }


}
