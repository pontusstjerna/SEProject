$("#btnEditCargoIn").click(function() {switchEdit("cargoIn")});
$("#btnEditCargoOut").click(function() {switchEdit("cargoOut")});
$("#btnEditName").click(function() {switchEdit("name")});
$("#btnEditVesselId").click(function() {switchEdit("vesselId")});
$("#btnEditPortcallId").click(function() {switchEdit("portcallId")});
$("#btnEditBerth").click(function(){switchEdit("berth");});

//Timestamps
$("#btnEditCargoOpCommenced").click(function(){switchTimeEdit("cargoOpCommenced");});
$("#btnEditLaycanStart").click(function() {switchTimeEdit("laycanStart")});
$("#btnEditLaycanEnd").click(function() {switchTimeEdit("laycanEnd")});

function setBtnSave(button){
    $("#" + button).html("Save");
}

function setBtnEdit(button){
    $("#" + button).html("Edit");
}

function switchEdit(field){
    var elem = $("#" + field);
    var readonly = elem.attr("readonly");
    if(readonly == "readonly"){
        elem.focus();
        setBtnSave("btnEdit" + field.capitalize());    
        elem.removeAttr("readonly");
    }else{
        setBtnEdit("btnEdit" + field.capitalize());
        elem.attr("readonly", "readonly");
        saveChanges();
    }
}

function switchTimeEdit(field){
    var elemDate = $("#" + field + "Date");
    var elemTime = $("#" + field + "Time");
    var readonly = elemDate.attr("readonly");
    if(readonly === "readonly"){
        elemDate.focus();
        setBtnSave("btnEdit" + field.capitalize());
        elemDate.removeAttr("readonly");
        elemTime.removeAttr("readonly");
    }else{
        setBtnEdit("btnEdit" + field.capitalize());
        elemDate.attr("readonly", "readonly");
        elemTime.attr("readonly", "readonly");
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
        name : $("#name").val(),
        vesselId : $("#vesselId").val(),
        portcallId : $("#portcallId").val(),
        berth : $("#berth").val(),
        internalId : id,
        //Timestamps
        cargoOpCommenced : getStringFromDate("cargoOpCommenced", currentPortcall.cargoOpCommenced),
        laycanStart : getStringFromDate("laycanStart", currentPortcall.laycanStart),
        laycanEnd : getStringFromDate("laycanEnd", currentPortcall.laycanEnd)
    };

    $.ajax({
        url: 'portcalls/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
}

function getStringFromDate(field){
    var date = $("#" + field + "Date").val();
    var time = $("#" + field + "Time").val();

    if(date === "" || time === "") return "";
    else return date + "T" + time + "Z";
}

