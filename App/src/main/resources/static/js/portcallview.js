var id;

function loadProperties(portcall, textStatus){
    $("#cargoIn").val(portcall.cargoIn);
    $("#cargoOut").val(portcall.cargoOut);
    $("#laycanStart").val(portcall.laycanStart);
    $("#laycanEnd").val(portcall.laycanEnd);
    $("#name").val(portcall.name);
    $("#vesselId").val(portcall.vesselId);
    $("#portcallId").val(portcall.portcallId);
    $("#comment").val(portcall.comment);

    startSubscription();
}

window.onload = function(){
    id = getId();
    $("#portCallName").append(" " + id);
    getPortCall();
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
