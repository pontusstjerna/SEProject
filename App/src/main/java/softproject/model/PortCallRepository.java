package softproject.model;

import softproject.repositories.PortCallFileRepository;

import java.util.List;



public interface PortCallRepository{

    PortCallRepository repo = new PortCallFileRepository();

    List<PortCall> getAll();
    PortCall get(int id);
    PortCall getFromPortcallId(String portCallId);
    PortCall getFromVesselId(String vesselId);
    PortCall getFromQueueId(String queueId);
    void add(PortCall newPortCall);
    boolean delete(PortCall portCallToDelete);

    static PortCallRepository getRepo() {
        return repo;
    }

}
