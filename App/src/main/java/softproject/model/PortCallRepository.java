package softproject.model;

import softproject.repositories.PortCallFileRepository;

import java.util.List;



public interface PortCallRepository{

    PortCallRepository repo = new PortCallFileRepository();

    List<PortCall> getAll();
    PortCall get(int id);
    void add(PortCall newPortCall);
    boolean delete(PortCall portCallToDelete);

    static PortCallRepository getRepo() {
        return repo;
    }

}
