function sendCargoOpCommenced(){
    console.log("Sending cargoOpCommenced to PortCDM...");

    var timeType = "ESTIMATED"; //TODO MIKI
    setModalPanel(true);

    var timeStamp = getStringFromDate("cargoOpCommenced");

    $.ajax({
        url: 'report/cargo/commenced?time=' + timeStamp + '&timeType=' + timeType, 
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function sendReadyToSail(){
    var timeType = "ESTIMATED"; //TODO MIKI

    var timeStamp = getStringFromDate("readyToSail");

    $.ajax({
        url: 'report/readytosailop/completed?time=' + timeStamp + '&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function sendSlopOpConfirmed(){
    var timeType = "ESTIMATED"; //TODO MIKI

    var timeStamp = getStringFromDate("slopOpConfirmed");

    $.ajax({
        url: 'report/slopop/completed?time=' + timeStamp + '&timeType=' + timeType,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function sendSlopOpDenied(){
    var timeType = "ESTIMATED"; //TODO MIKI

    var timeStamp = getStringFromDate("slopOpDenied");

    $.ajax({
        url: 'report/slopop/denied?time=' + timeStamp + '&timeType=' + timeType,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function sendSlopOpReqReceived(){
    var timeType = "ESTIMATED"; //TODO MIKI

    var timeStamp = getStringFromDate("slopOpReqReceived");

    $.ajax({
        url: 'report/slopop/received?time=' + timeStamp + '&timeType=' + timeType,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function sendArrivalVesselBerth(){
    var timeType = "ESTIMATED"; //TODO MIKI

    var timeStamp = getStringFromDate("arrivalVesselBerth");

    $.ajax({
        url: 'report/vessel/arrival?time=' + timeStamp + '&timeType=' + timeType,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function sendDepartureVesselBerth(){
    var timeType = "ESTIMATED"; //TODO MIKI

    var timeStamp = getStringFromDate("departureVesselBerth");

    $.ajax({
        url: 'report/vessel/departure?time=' + timeStamp + '&timeType=' + timeType,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function sendCargoOpCompleted(){
    var timeType = "ESTIMATED"; //TODO MIKI

    var timeStamp = getStringFromDate("cargoOpCompleted");

    $.ajax({
        url: 'report/cargo/completed?time=' + timeStamp + '&timeType=' + timeType,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getPortCDMPortCall()),
        dataType: 'json'
    });
}

function getPortCDMPortCall(){
    var vesselId = $("#vesselId").val();
    var portcallId = $("#portcallId").val();

    if(portcallId === ""){
        if(vesselId === "") return {};
        else return {vesselId : vesselId};
    }else{
        if(vesselId === "") return {portcallId : portcallId};
        else return {portcallId : portcallId, vesselId : vesselId};
    }
}

var modalPanel = document.getElementById("modalPanel");
function setModalPanel(visible){
    
    if(visible)
        modalPanel.style.display = "block";
    else 
        modalPanel.style.display = "none";
}

window.onclick = function(){
    //setModalPanel(false);
}