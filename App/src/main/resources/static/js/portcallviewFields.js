$("#btnEditCargoIn").click(function() {switchEdit("cargoIn")});
$("#btnEditCargoOut").click(function() {switchEdit("cargoOut")});
$("#btnEditLaycanStart").click(function() {switchEdit("laycanStart")});
$("#btnEditLaycanEnd").click(function() {switchEdit("laycanEnd")});
$("#btnEditName").click(function() {switchEdit("name")});
$("#btnEditVesselId").click(function() {switchEdit("vesselId")});
$("#btnEditPortcallId").click(function() {switchEdit("portcallId")});

//Timestamps
$("#btnEditCargoOpCommenced").click(function(){switchTimeEdit("cargoOpCommenced");});

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
        laycanStart : $("#laycanStart").val(),
        laycanEnd : $("#laycanEnd").val(),
        name : $("#name").val(),
        vesselId : $("#vesselId").val(),
        portcallId : $("#portcallId").val(),
        internalId : id,
        //Timestamps
        cargoOpCommenced : getTimestamp("cargoOpCommenced")
    };

    $.ajax({
        url: 'portcalls/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
}

function getTimestamp(field){
    var timeSelect = $("#time-type-select").val().toString();

    var date = $("#" + field + "Date").val();
    var time = $("#" + field + "Time").val();

    if(date === "" || time === "") return {};

    var timeStmp = date + "T" + time + "Z";

    switch(timeSelect){
        case "ESTIMATED":
            return {estimated : timeStmp};
        case "TARGET":
            return {target : timeStmp};
        case "ACTUAL":
            return {actual : timeStmp};
        case "RECOMMENDED":
            return {actual : timeStmp};
    }

    return {};
}

