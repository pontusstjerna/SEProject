$("#btnEditCargoIn").click(function() {switchEdit("cargoIn")});
$("#btnEditCargoOut").click(function() {switchEdit("cargoOut")});
$("#btnEditLaycanStart").click(function() {switchEdit("laycanStart")});
$("#btnEditLaycanEnd").click(function() {switchEdit("laycanEnd")});
$("#btnEditName").click(function() {switchEdit("name")});
$("#btnEditVesselId").click(function() {switchEdit("vesselId")});
$("#btnEditPortcallId").click(function() {switchEdit("portcallId")});

function setBtnSave(button){
    $("#" + button).html("Save");
}

function setBtnEdit(button){
    $("#" + button).html("Edit");
}

function switchEdit(field){
    var readonly = $("#" + field).attr("readonly");
    if(readonly == "readonly"){
        $("#" + field).focus();
        setBtnSave("btnEdit" + field.capitalize());    
        $("#" + field).removeAttr("readonly");
    }else{
        setBtnEdit("btnEdit" + field.capitalize());
        $("#" + field).attr("readonly", "readonly");
        saveChanges();
    }
}
    
String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

function saveChanges(){
    var newPortCall = {
        cargoIn : $("#cargoIn").val(),
        cargoOut : $("#cargoOut").val(),
        laycanStart : $("#laycanStart").val(),
        laycanEnd : $("#laycanEnd").val(),
        name : $("#name").val(),
        vesselId : $("#vesselId").val(),
        portcallId : $("#portcallId").val(),
        internalId : id
    };

    $.ajax({
        url: 'portcalls/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
}

