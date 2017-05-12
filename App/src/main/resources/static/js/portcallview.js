var id;

function loadProperties(portcall, textStatus){
    $("#cargoIn").html(portcall.cargoIn);
    $("#cargoOut").html(portcall.cargoOut);
    $("#laycanStart").html(portcall.laycanStart);
    $("#laycanEnd").html(portcall.laycanEnd);
    $("#name").html(portcall.name);
    $("#vesselId").html(portcall.vesselId);
    $("#portcallId").html(portcall.portcallId);
    $("#comment").html(portcall.comment);
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
