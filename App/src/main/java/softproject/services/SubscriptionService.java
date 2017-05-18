package softproject.services;

import eu.portcdm.mb.dto.Filter;
import eu.portcdm.mb.dto.FilterType;
import eu.portcdm.messaging.PortCallMessage;
import softproject.services.exceptions.BadRequest;
import softproject.services.exceptions.CouldNotReachPortCDM;
import softproject.services.exceptions.IllegalFilters;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Skillzore on 2017-05-18.
 */
public class SubscriptionService {

    PortCDMRequest portCDMRequest = new PortCDMRequest();

    // Subscribe with one filter
    public String subscribe(FilterType type, String elementToFind) {
        Filter filter = new Filter();
        filter.setType(type);
        filter.setElement(elementToFind);

        MessageQueueService queueService = new MessageQueueService(portCDMRequest.getClientInstance(), portCDMRequest.getBaseRequest());

        String queueId = "";

        try {
            queueId = queueService.postMqs(Arrays.asList(filter));
        } catch (IllegalFilters illegalFilters) {
            illegalFilters.printStackTrace();
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            couldNotReachPortCDM.printStackTrace();
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }

        return queueId;
    }

    // Subscribe with one filter and fromTime
    public String subscribe(FilterType type, String elementToFind, String fromTime) {
        Filter filter = new Filter();
        filter.setType(type);
        filter.setElement(elementToFind);

        MessageQueueService queueService = new MessageQueueService(portCDMRequest.getClientInstance(), portCDMRequest.getBaseRequest());

        String queueId = "";

        try {
            queueId = queueService.postMqs(Arrays.asList(filter), fromTime);
        } catch (IllegalFilters illegalFilters) {
            illegalFilters.printStackTrace();
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            couldNotReachPortCDM.printStackTrace();
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }

        return queueId;
    }

    // Subscribe with fromTime
    public String subscribe(String fromTime) {

        MessageQueueService queueService = new MessageQueueService(portCDMRequest.getClientInstance(), portCDMRequest.getBaseRequest());

        String queueId = "";

        try {
            queueId = queueService.postMqs(fromTime);
        } catch (IllegalFilters illegalFilters) {
            illegalFilters.printStackTrace();
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            couldNotReachPortCDM.printStackTrace();
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }

        return queueId;
    }

    //Get new messages from queue
    public List<PortCallMessage> getNewMessages(String queueId) {
        List<PortCallMessage> messages = null;
        MessageQueueService queueService = new MessageQueueService(portCDMRequest.getClientInstance(), portCDMRequest.getBaseRequest());
        try {
            messages = queueService.getMqs(queueId);
        } catch (CouldNotReachPortCDM couldNotReachPortCDM) {
            couldNotReachPortCDM.printStackTrace();
        } catch (BadRequest badRequest) {
            badRequest.printStackTrace();
        }

        return messages;
    }


}
