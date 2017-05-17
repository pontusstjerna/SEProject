package softproject.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softproject.model.PortCall;
import softproject.model.PortCallRepository;

import javax.annotation.PostConstruct;
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


    @RequestMapping("/internalPortcall")
    public PortCall portcall(@RequestParam(value="id") String id) {
        int portId = Integer.parseInt(id);

        return PortCallRepository.getRepo().get(portId);
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

    @GetMapping("/portcalls")
    public List<PortCall> getAllPortCalls() {


        return PortCallRepository.getRepo().getAll();
    }
}
