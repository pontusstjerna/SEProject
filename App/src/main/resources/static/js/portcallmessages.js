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
        data.forEach(function (m) {
            if (m.serviceState !== null) {
                $("#portcallmessages").prepend(getServiceContainer(m.reportedAt.replace('T', ' '), m.reportedBy.substring(22), m.serviceState.timeType, m.serviceState.serviceObject, m.serviceState.time, m.serviceState.timeSequence));
            } else if (m.locationState !== null) {
                if (m.locationState.arrivalLocation !== null) {       // FIXA CASES FÖR ALLA ARRIVAL OCH DEPARTURE LOCATIONS!!!!!!!!!!!!!!
                    if (m.locationState.arrivalLocation.to !== null) {
                        $("#portcallmessages").prepend(getLocationContainer(m.reportedAt.replace('T', ' '), m.reportedBy.substring(22), m.locationState.timeType, m.locationState.referenceObject, m.locationState.time, m.locationState.arrivalLocation.to.locationMRN));
                    }
                }
            } else {
                $("#portcallmessages").prepend(getMessageContainer(m.reportedAt.replace('T', ' '), m.reportedBy.substring(22), m.messageOperation.operatioN));
            }
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
            if (m.serviceState !== null) {
                $("#portcallmessages").prepend(getServiceContainer(m.reportedAt.replace('T', ' '), m.reportedBy.substring(22), m.serviceState.timeType, m.serviceState.serviceObject, m.serviceState.time, m.serviceState.timeSequence));
            } else if (m.locationState !== null) {
                if (m.locationState.arrivalLocation !== null) {           // FIXA CASES FÖR ALLA ARRIVAL OCH DEPARTURE LOCATIONS!!!!!!!!!!!!!!
                    if (m.locationState.arrivalLocation.to !== null) {
                        $("#portcallmessages").prepend(getLocationContainer(m.reportedAt.replace('T', ' '), m.reportedBy.substring(22), m.locationState.timeType, m.locationState.referenceObject, m.locationState.time, m.locationState.arrivalLocation.to.locationMRN));
                    }
                }
            } else {
                $("#portcallmessages").prepend(getMessageContainer(m.reportedAt.replace('T', ' '), m.reportedBy.substring(22), m.messageOperation.operatioN));
            }
        });
    });
}

function getServiceContainer(timeReceived, sender, timeType, serviceObject, time, timeSequence) {
    var container = document.createElement("div");
    container.classList.add("portcallmessage");
    var header = document.createElement("b");
    var headerText = document.createTextNode("Posted at " + timeReceived + " by " + sender);
    header.appendChild(headerText);
    container.appendChild(header);

    var typeAndObject = document.createTextNode("New " + timeType.toLowerCase() + " " + timeSequence.toLowerCase() + " " + serviceObject.toLowerCase());
    var newTime = document.createTextNode(new Date(time).toISOString().substr(0, 19).replace('T', ' '));
    container.appendChild(document.createElement("br"));
    container.appendChild(typeAndObject);
    container.appendChild(document.createElement("br"));
    container.appendChild(newTime);

    return container;
}

function getLocationContainer(timeReceived, sender, timeType, referenceObject, time, locationMRN) {
    var container = document.createElement("div");
    container.classList.add("portcallmessage");
    var header = document.createElement("b");
    var headerText = document.createTextNode("Posted at " + timeReceived + " by " + sender);
    header.appendChild(headerText);
    container.appendChild(header);

    var typeAndObject = document.createTextNode("New " + timeType.toLowerCase() + " arrival of " + referenceObject.toLowerCase() + " to " + locationMRN.toLowerCase());
    var newTime = document.createTextNode(new Date(time).toISOString().substr(0, 19).replace('T', ' '));
    container.appendChild(document.createElement("br"));
    container.appendChild(typeAndObject);
    container.appendChild(document.createElement("br"));
    container.appendChild(newTime);

    return container;
}

function getMessageContainer(timeReceived, sender, operatioN) {
    var container = document.createElement("div");
    container.classList.add("portcallmessage");
    var header = document.createElement("b");
    var headerText = document.createTextNode("Posted at " + timeReceived + " by " + sender);
    header.appendChild(headerText);
    container.appendChild(header);

    var typeAndObject = document.createTextNode("New " + operatioN.toLowerCase());
    //var newTime = document.createTextNode(new Date(time).toISOString().substr(0, 19).replace('T', ' '));
    container.appendChild(document.createElement("br"));
    container.appendChild(typeAndObject);
    //container.appendChild(document.createElement("br"));
    //container.appendChild(newTime);

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