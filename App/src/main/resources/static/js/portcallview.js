var id;
var currentPortcall;

function loadProperties(portcall, textStatus){
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
    setTimestamp("laycanStart", portcall.laycanStart);
    setTimestamp("laycanEnd", portcall.laycanEnd);

    startSubscription();
}

window.onload = function(){
    id = getId();
    $("#portCallName").append(" " + id);
    getPortCall();
    $("#time-type-select").change(getPortCall);
}
    
function getId(){
    var arguments = window.location.search;
    return arguments.split("=")[1];
}

function getPortCall(){
 $.ajax({
     url: '/internalPortcall?id=' + id,
     type: 'GET',
     success: loadProperties
 });
}

function Return(){
    window.open("/unscheduled", "_self");
}

function setTimestamp(field, value){
    var timeStmp;
    var date = $("#" + field + "Date");
    var time = $("#" + field + "Time");

    var timeSelect = $("#time-type-select").val().toString();

    if(value != null){
        switch(timeSelect){
            case "ESTIMATED":
                timeStmp = value.estimated;
                break;
            case "TARGET":
                timeStmp = value.target;
                break;
            case "ACTUAL":
                timeStmp = value.actual;
                break;
            case "RECOMMENDED":
                timeStmp = value.recommended;
                break;
        }
    }
    

    if(timeStmp === undefined || timeStmp === null){
        date.val("");
        time.val("");
    }else{
        var dateTime = timeStmp.split("T");
        date.val(dateTime[0]);
        time.val(dateTime[1].substr(0,5)); //Time will be like 23:00UTC
    }
}