function sendCargoOpCommenced(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType(); 
        var timeStamp = getStringFromDate("cargoOpCommenced");

        $.ajax({
            url: 'report/cargo/commenced?time=' + timeStamp + '&timeType=' + timeType, 
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        });
    });
    
}

function sendReadyToSail(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType();
        var timeStamp = getStringFromDate("readyToSail");

        $.ajax({
            url: 'report/readytosailop/completed?time=' + timeStamp + '&timeType=' + timeType, // this URL only works when the server is on the same machine!
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        });
    });
}

function sendSlopOpConfirmed(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType();
        var timeStamp = getStringFromDate("slopOpConfirmed");

        $.ajax({
            url: 'report/slopop/confirmed?time=' + timeStamp + '&timeType=' + timeType,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        });
    });
}

function sendSlopOpDenied(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType();
        var timeStamp = getStringFromDate("slopOpDenied");

        $.ajax({
            url: 'report/slopop/denied?time=' + timeStamp + '&timeType=' + timeType,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        });
    });
}

function sendSlopOpReqReceived(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType();
        var timeStamp = getStringFromDate("slopOpReqReceived");

        $.ajax({
            url: 'report/slopop/received?time=' + timeStamp + '&timeType=' + timeType,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        });
    });
}

function sendArrivalVesselBerth(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType();
        var timeStamp = getStringFromDate("arrivalVesselBerth");

        $.ajax({
            url: 'report/vessel/arrival?time=' + timeStamp + '&timeType=' + timeType,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        });
    });
}

function sendDepartureVesselBerth(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType();
        var timeStamp = getStringFromDate("departureVesselBerth");

        $.ajax({
            url: 'report/vessel/departure?time=' + timeStamp + '&timeType=' + timeType,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        });
    });
}

function sendCargoOpCompleted(){
    setModalPanel(true);

    $("#btnSendToPortCDM").off("click").on("click", function(){
        setModalPanel(false);
        var timeType = getTimeType();
        var timeStamp = getStringFromDate("cargoOpCompleted");

        $.ajax({
            url: 'report/cargo/completed?time=' + timeStamp + '&timeType=' + timeType,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getPortCDMPortCall()),
            dataType: 'json'
        })
    });
}

function getPortCDMPortCall(){
    var vesselId = $("#vesselId").val();
    var portcallId = $("#portcallId").val();
    var berth = $('#berth').val();

    if(vesselId === "") vesselId = null;
    if(portcallId === "") portcallId = null;
    if(berth === "") berth = null;


    return {vesselId : vesselId, portcallId : portcallId, berth: berth};
}

function setModalPanel(visible){
    var modalPanel = document.getElementById("modalPanel");

    if(visible){
        modalPanel.style.display = "block";
    }else 
        modalPanel.style.display = "none";
}

/*window.onclick = function(){
    if (event.target !== document.getElementById("modalPanel")) {
        setModalPanel(false);
    }
}*/

function getTimeType(){
    return $('input[name=timeType]:checked').val();
}

function enableSending(){
    var vesselId = $("#vesselId").val();
    var portcallId = $("#portcallId").val();

    if(getTimeType() !== "" && (vesselId !== "" || portcallId !== ""))
        $("#btnSendToPortCDM").attr("disabled", false);
}

