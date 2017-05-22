var queueId;

function startSubscription(){
    var vessId = $("#vesselId").val()
    var portId = $("#portcallId").val();
    var subUrl = "";

    if (portId != "") {
        subUrl = "/queue/subscribe/portcalls/" + portId;
        getQidAndMessages(subUrl);
    }
    else if (vessId != "") {
        subUrl = "/queue/subscribe/portcalls/vessel/" + vessId;
        getQidAndMessages(subUrl);
    } else {
        $("#portcallmessages").html(getNoFeedContainer());
    }

}

function getQidAndMessages(subUrl) {
    $.ajax({
        url: subUrl,
        context: document.body
    }).done(function (data) { //When response is received
        queueId = data;
        getOldMessages();

        //Look for new messages every 3 seconds
        setInterval(getNewMessages, 3000);
    });
}

function getOldMessages() {
    console.log("old runnin");
    $.ajax({
        url: "/queue/old/" + queueId,
        context: document.body
    }).done(function (data) {
        console.log(data);
        data.forEach(function (m) {
            buildMessageToDisplay(m);
        });
    });
}

function getNewMessages() {
    console.log("new runnin");
    $.ajax({
        url: "/queue/new/" + queueId,
        context: document.body
    }).done(function (data) {
        data.forEach(function (m) {
            buildMessageToDisplay(m);
        });
    });
}

function buildMessageToDisplay(m){
    if (m.serviceState !== null) {
        $("#portcallmessages").prepend(getMessageContainer("Posted at " + m.reportedAt.replace('T', ' ') + " by " + m.reportedBy.substring(22), "New " +  m.serviceState.serviceObject.replace('_',' ').toLowerCase() + " " + m.serviceState.timeType.toLowerCase() + " to be " + m.serviceState.timeSequence.replace('_',' ').toLowerCase() + " at:", new Date(m.serviceState.time).toISOString().substr(0, 19).replace('T', ' '), "PortCallID: " + m.portCallId));
    } else if (m.locationState !== null) {
        if (m.locationState.arrivalLocation !== null) {     
            if (m.locationState.arrivalLocation.to !== null) {
                $("#portcallmessages").prepend(getMessageContainer("Posted at " + m.reportedAt.replace('T', ' ') + " by " + m.reportedBy.substring(22), "New " + m.locationState.timeType.toLowerCase() + " arrival of " + m.locationState.referenceObject.toLowerCase() + " to " + m.locationState.arrivalLocation.to.locationMRN.substring(27).replace('_', ' ').toLowerCase(), new Date(m.locationState.time).toISOString().substr(0, 19).replace('T', ' '), "PortCallID: " + m.portCallId ));
            }else{
                $("#portcallmessages").prepend(getMessageContainer("Posted at " + m.reportedAt.replace('T', ' ') + " by " + m.reportedBy.substring(22), "New " + m.locationState.timeType.toLowerCase() + " arrival of " + m.locationState.referenceObject.toLowerCase() + " from " + m.locationState.arrivalLocation.from.locationMRN.substring(27).replace('_', ' ').toLowerCase(), new Date(m.locationState.time).toISOString().substr(0, 19).replace('T', ' '), "PortCallID: " + m.portCallId ));
            }
        }else {
            if (m.locationState.departureLocation.from !== null) {
                $("#portcallmessages").prepend(getMessageContainer("Posted at " + m.reportedAt.replace('T', ' ') + " by " + m.reportedBy.substring(22), "New " + m.locationState.timeType.toLowerCase() + " departure of " + m.locationState.referenceObject.toLowerCase() + " from " + m.locationState.departureLocation.from.locationMRN.substring(27).replace('_', ' ').toLowerCase(), new Date(m.locationState.time).toISOString().substr(0, 19).replace('T', ' '), "PortCallID: " + m.portCallId));
            }else{
                $("#portcallmessages").prepend(getMessageContainer("Posted at " + m.reportedAt.replace('T', ' ') + " by " + m.reportedBy.substring(22), "New " + m.locationState.timeType.toLowerCase() + " departure of " + m.locationState.referenceObject.toLowerCase() + " to " + m.locationState.departureLocation.to.locationMRN.substring(27).replace('_', ' ').toLowerCase(), new Date(m.locationState.time).toISOString().substr(0, 19).replace('T', ' '), "PortCallID: " + m.portCallId));

            }
        }
    } else {
        $("#portcallmessages").prepend(getMessageContainer("Posted at " + m.reportedAt.replace('T', ' ') + " by " + m.reportedBy.substring(22), "New " +  m.messageOperation.operation.toLowerCase(), "No time to display"), "PortCallID: " + m.portCallId);
    }
}


function getMessageContainer(headerText, typeAndObjectText, newTimeText, portCallIdText) {
    var container = document.createElement("div");
    container.classList.add("portcallmessage");
    var header = document.createElement("b");
    var headerText = document.createTextNode(headerText);
    header.appendChild(headerText);
    container.appendChild(header);

    var typeAndObject = document.createTextNode(typeAndObjectText);
    var newTime = document.createTextNode(newTimeText);
    var portCallId = document.createTextNode(portCallIdText);
    container.appendChild(document.createElement("br"));
    container.appendChild(typeAndObject);
    container.appendChild(document.createElement("br"));
    container.appendChild(newTime);
    container.appendChild(document.createElement("br"));
    container.appendChild(portCallId);

    return container;
}

function getNoFeedContainer() {
    var container = document.createElement("div");
    container.classList.add("infodisplay");
    var header = document.createElement("b");
    var headerText = document.createTextNode("No feed available!");
    header.appendChild(headerText);
    container.appendChild(header);

    var info = document.createTextNode("Need PortCallID or VesselID to display feed.");
    container.appendChild(document.createElement("br"));
    container.appendChild(info);

    return container;
}