var dummyMessage = "<div class=\"portcallmessage\>\n" + 
          "<h4>07:32 Tug boat</h4>\n" +
          "<b>Message: </b>\n" +
          "<span>New ETA 08:23... more stuff here</span>\n" +
    "</div>\n";

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