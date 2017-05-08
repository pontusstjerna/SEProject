var queueId;

//Start subscription by getting a queueID
window.onload = function () {
    function startSubscription() {
        $.ajax({
            url: "http://localhost:8080/queue/subscribe",
            context: document.body
        }).done(function(data) { //When response is recieved
            $('#queueId').text(data)
            queueId = $('#queueId').text();
        });
    }

    //Look for new messages every 10 seconds
    setInterval(getNewMessages, 10000);
}

function getNewMessages(){
   $.ajax({
       url: "http://localhost:8080/queue/" + queueId,
       context: document.body
   }).done(function(data) {
       data.forEach(function (m){
        $("#portcallmessages").prepend(getMessageContainer(m.portCallId + " 05:23:" + new Date().getSeconds(),m.comment));
       });
    });
}

function addTestMessage(){
    $("#portcallmessages").prepend(getMessageContainer("Tug 05:23:" + new Date().getSeconds(),"New ETB 22:12."));
}

function getMessageContainer(headerText, message){
    var container = document.createElement("div");
    container.classList.add("portcallmessage");
    var header = document.createElement("h4");
    var headerText = document.createTextNode(headerText);
    header.appendChild(headerText);
    container.appendChild(header);

    var messageHeader = document.createElement("b");
    messageHeader.innerHTML = "Message: ";
    container.appendChild(messageHeader);
    container.appendChild(document.createTextNode(message));

    return container;
}