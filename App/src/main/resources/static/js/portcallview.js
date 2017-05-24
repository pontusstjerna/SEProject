var id = getId();
var currentPortcall;

function loadProperties(portcall) {
    currentPortcall = portcall;

    $("#cargoIn").val(portcall.cargoIn);
    $("#cargoOut").val(portcall.cargoOut);
    $("#berth").val(portcall.berth === "" || portcall.berth === null ? "Unassigned" : portcall.berth);
    $("#name").val(portcall.name);
    $("#vesselId").val(portcall.vesselId);
    $("#portcallId").val(portcall.portcallId);
    $("#comment").val(portcall.comment);

    //Timestamps
    setTimestamp("cargoOpCommenced", portcall.cargoOpCommenced);
    setTimestamp("cargoOpCompleted", portcall.cargoOpCompleted);
    setTimestamp("slopOpConfirmed", portcall.slopOpConfirmed);
    setTimestamp("slopOpDenied", portcall.slopOpDenied);
    setTimestamp("slopOpReqReceived", portcall.slopOpReqReceived);
    setTimestamp("readyToSail", portcall.readyToSail);
    setTimestamp("arrivalVesselBerth", portcall.arrivalVesselBerth);
    setTimestamp("departureVesselBerth", portcall.departureVesselBerth);
    setTimestamp("laycanStart", portcall.laycanStart);
    setTimestamp("laycanEnd", portcall.laycanEnd);

    startSubscription();
}

window.onload = function () {
    $("#portCallName").append(" " + id);
    getPortCall();
    $("#time-type-select").change(getPortCall);
}

function getId() {
    var arguments = window.location.search;
    return arguments.split("=")[1];
}

function getPortCall() {
    $.ajax({
        url: '/internalPortcall?id=' + id,
        type: 'GET',
        success: loadProperties
    });
}

function Return() {
    window.open("/unscheduled", "_self");
}

function setTimestamp(field, value) {
    var date = $("#" + field + "Date");
    var time = $("#" + field + "Time");

    if (value === undefined || value === null) {
        date.val("");
        time.val("");
    } else {
        var dateTime = value.split("T");
        date.val(dateTime[0]);
        time.val(dateTime[1].substr(0, 5)); //Time will be like 23:00UTC
    }
}

function getNsetVesselName() {
    var vesselId = currentPortcall.vesselId;
    $.ajax({
        url: '/vessel/' + vesselId,
        context: document.body
    }).done(function (data) {
        if (data) {
            $(document).ajaxStop(function () {
                location.reload(true);
            })
        }
    });
}