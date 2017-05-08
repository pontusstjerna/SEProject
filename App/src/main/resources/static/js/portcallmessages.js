var queueId;

//Start subscription by getting a queueID
window.onload = function () {
    $.ajax({
        url: "http://localhost:8080/queue/subscribe",
        context: document.body
    }).done(function(data) { //When response is recieved     
        queueId = data;
    });

    //Look for new messages every 10 seconds
    setInterval(getNewMessages, 10000);
}

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
    var currentDate = new Date();
    return currentDate.getFullYear() + "-" + currentDate.getMonth() + "-" + currentDate.getDate() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds();
}