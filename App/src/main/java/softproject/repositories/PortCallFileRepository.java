package softproject.repositories;

import softproject.model.PortCall;
import softproject.model.PortCallRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PortCallFileRepository implements PortCallRepository {

    private static final String FILE_PATH = "portcallstorage.data";
    List<PortCall> portcalls;

    public PortCallFileRepository() {
        portcalls = readPortCallsFromFile();
    }


    @Override
    // returns all portcallmessages in our datastructure
    public List<PortCall> getAll() {
        return portcalls;
    }

    @Override
    // returns the portcall with the specified id, or NULL if the id can't be found
    public PortCall get(int internalId) {
        Optional<PortCall> maybePortCall =
                portcalls.stream()
                         .filter(p -> p.getInternalId() == internalId)
                         .findFirst();

        return maybePortCall.orElse(null);
    }

    @Override
    public void add(PortCall newPortCall) {

        System.out.println("Adding or editing portcall " + newPortCall.getInternalId());

        System.out.println("PRESENT::");
        for(PortCall p : portcalls) System.out.println("Portcall: " + p.getInternalId());

        //If id already exists, edit instead of adding
        Optional<PortCall> maybeOld = portcalls.stream().filter(x -> x.getInternalId() == newPortCall.getInternalId()).findFirst();
        if(maybeOld.isPresent()){ //Update old
            System.out.println("This is an edit!");
            PortCall old = maybeOld.get();
            old.setCargoIn(newPortCall.getCargoIn());
            old.setCargoOut(newPortCall.getCargoOut());
            old.setLaycanStart(newPortCall.getLaycanStart());
            old.setLaycanEnd(newPortCall.getLaycanEnd());
            old.setName(newPortCall.getName());
            old.setVesselId(newPortCall.getVesselId());
            old.setPortcallId(newPortCall.getVesselId());
        }else portcalls.add(newPortCall);

        savePortCallsToFile();
    }

    @Override
    // deletes the portcall sent in as a parameter
    // returns TRUE if something was deleted, FALSE otherwise
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

    // reads the portcalls contained in resources\files\portcallstorage.data
    private List<PortCall> readPortCallsFromFile() {
        List<PortCall> newPortCalls;

        FileInputStream fileIn;
        ObjectInputStream objectIn;
        try {
            File file = new File(FILE_PATH);
            if(!file.exists()) {
                return new ArrayList<PortCall>();
            }

            fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);
            newPortCalls = (ArrayList<PortCall>) objectIn.readObject();

            objectIn.close();
            fileIn.close();
            return newPortCalls;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<PortCall>();
    }

    // saves all the portcalls in our list to resources\files\portcallstorage.data
    private void savePortCallsToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(portcalls);
            objectOut.flush();

            objectOut.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR SAVING TO FILE, file not found " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong serializing");
        }
        System.out.println("Everything serialized!");
    }
}
