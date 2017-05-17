var queueId;
//"urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac";

function startSubscription(){
    var vessId = $("#vesselId").val()
    var portId = $("#portcallId").val();
    var subUrl = "";

    console.log(portId);
    console.log(vessId);
    if(portId != "") {
        subUrl = "/queue/subscribe/portcalls/" + portId;
        getQidAndMessages(subUrl);
    }
    else if(vessId != "") {
        subUrl = "/queue/subscribe/portcalls/vessel/" + vessId;
        getQidAndMessages(subUrl);
    }else{
        getNoFeedContainer()
    }

}

function getQidAndMessages(subUrl){
    $.ajax({
        url: subUrl,
        context: document.body
    }).done(function(data) { //When response is recieved
        queueId = data;
        //getNewMessages();
    });

    //Look for new messages every 10 seconds
    setInterval(getNewMessages, 10000);
}

function getNewMessages(){
   $.ajax({
       url: "/queue/" + queueId,
       context: document.body
   }).done(function(data) {
       data.forEach(function (wrapper){
           var pcm = wrapper.portcallMessage;
           console.log(pcm);
           var time = warapper.time;
           console.log(time);
           console.log("hej");
        $("#portcallmessages").html(getMessageContainer(new Date(time).toISOString().substr(0,19).replace('T', ' '), pcm.reportedBy, pcm.serviceState.timeType, pcm.serviceState.serviceObject, pcm.serviceState.time));
       });
    });
}

function addTestMessage(){
    $("#portcallmessages").html(getMessageContainer(new Date().getMilliseconds(),"Tug", "ACTUAL", "BERT_DEPARTURE", new Date().getMilliseconds()));
}

function getMessageContainer(timeReceived, sender, timeType, serviceObject, time){
    var container = document.createElement("div");
    container.classList.add("portcallmessage");
    var header = document.createElement("b");
    var headerText = document.createTextNode("Posted at " + timeReceived + " by " + sender);
    header.appendChild(headerText);
    container.appendChild(header);

    var typeAndObject = document.createTextNode("New " + timeType.toLowerCase() + " " + serviceObject.toLowerCase());
    var newTime = document.createTextNode(new Date(time).toISOString().substr(0,19).replace('T', ' '));
    container.appendChild(document.createElement("br"));
    container.appendChild(typeAndObject);
    container.appendChild(document.createElement("br"));
    container.appendChild(newTime);

    return container;
}

function getNoFeedContainer(){
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