package softproject.model;

import eu.portcdm.messaging.PortCallMessage;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Skillzore on 2017-05-17.
 */
public class PCMTimeWrapper implements Serializable {

    private static long PCMTimeCount = 0;

    private final long INTERNAL_ID;
    private final PortCallMessage MESSAGE;
    private final long TIME;

    public PCMTimeWrapper(PortCallMessage message, long time){
        PCMTimeCount++;
        this.INTERNAL_ID = PCMTimeCount;
        this.MESSAGE = message;
        this.TIME = time;
    }

    public PortCallMessage getMESSAGE() { return MESSAGE; }

    public long getTIME() {
        return TIME;
    }

    public long getINTERNAL_ID() { return INTERNAL_ID; }
}
