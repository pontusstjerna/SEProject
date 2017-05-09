var queueId;
var portCallId = "urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac";

//Start subscription by getting a queueID
window.onload = function () {
    $.ajax({
        url: "http://localhost:8080/queue/subscribe/portcalls/" + portCallId,
        context: document.body
    }).done(function(data) { //When response is recieved     
        queueId = data;
    });

    //Look for new messages every 10 seconds
    setInterval(getNewMessages, 10000);
};

function getNewMessages(){
   $.ajax({
       url: "http://localhost:8080/queue/" + queueId,
       context: document.body
   }).done(function(data) {
       data.forEach(function (m){
        console.log(m.serviceState);
        $("#portcallmessages").prepend(getMessageContainer(getCurrentDate(), "unknown", m.serviceState.timeType, m.serviceState.serviceObject, m.serviceState.time));
       });
    });
}

function addTestMessage(){
    $("#portcallmessages").prepend(getMessageContainer("Tug 05:23:" + new Date().getSeconds(),"New ETB 22:12."));
}

function getMessageContainer(timeReceived, sender, timeType, serviceObject, time){
    var container = document.createElement("div");
    container.classList.add("portcallmessage");
    var header = document.createElement("b");
    var headerText = document.createTextNode(timeReceived + " from " + sender);
    header.appendChild(headerText);
    container.appendChild(header);

    var typeAndObject = document.createTextNode("New " + timeType.toLowerCase() + " " + serviceObject.toLowerCase());
    var newTime = document.createTextNode(time);
    container.appendChild(document.createElement("br"));
    container.appendChild(typeAndObject);
    container.appendChild(document.createElement("br"));
    container.appendChild(newTime);

    return container;
}

function getCurrentDate(){
    var currentDate = new Date(new Date().getTime() - new Date().getTimezoneOffset()*60*1000).toISOString().substr(0,19).replace('T', ' ');
                                //getFullYear() + "-" + currentDate.getMonth() + "-" + currentDate.getDate() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds();
    return currentDate;
}