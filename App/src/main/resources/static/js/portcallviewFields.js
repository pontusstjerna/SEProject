$("#btnEditCargoIn").click(function() {switchEdit("cargoIn")});
$("#btnEditCargoOut").click(function() {switchEdit("cargoOut")});
$("#btnEditLaycanStart").click(function() {switchEdit("laycanStart")});
$("#btnEditLaycanEnd").click(function() {switchEdit("laycanEnd")});
$("#btnEditName").click(function() {switchEdit("name")});
$("#btnEditVesselId").click(function() {switchEdit("vesselId")});
$("#btnEditPortCallId").click(function() {switchEdit("portCallId")});

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
    }
}
    
String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}


