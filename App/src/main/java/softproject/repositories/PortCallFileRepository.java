package softproject.repositories;

import softproject.model.PortCall;
import softproject.model.PortCallRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PortCallFileRepository implements PortCallRepository {

    List<PortCall> portcalls;

    public PortCallFileRepository() {
        portcalls = readPortCallsFromFile();
    }


    @Override
    // returns all portcallmessages in our datastructure
    public List<PortCall> getAll() {
        return portcalls;
    }

    // returns the portcall with the specified id, or NULL if the id can't be found
    @Override
    public PortCall get(int internalId) {
        Optional<PortCall> maybePortCall =
                portcalls.stream()
                         .filter(p -> p.getInternalId() == internalId)
                         .findFirst();

        if (maybePortCall.isPresent())
            return maybePortCall.get();
        else
            return null;
    }

    @Override
    public void add(PortCall newPortCall) {
        // TODO check so that the id isn't already in the list
        portcalls.add(newPortCall);
    }

    // deletes the portcall sent in as a parameter
    // returns TRUE if something was deleted, FALSE otherwise
    @Override
    public boolean delete(PortCall portCallToDelete) {
        List<PortCall> newPortCalls = portcalls.stream()
                .filter(p -> p.getInternalId() != portCallToDelete.getInternalId())
                .collect(Collectors.toList());

        if(newPortCalls.size() < this.portcalls.size()) {
            this.portcalls = newPortCalls;
            savePortCallsToFile();
            return true;
        } else {
            return false;
        }
    }

    private List<PortCall> readPortCallsFromFile() {
        return null;
    }

    private void savePortCallsToFile() {
        return;
    }
}
