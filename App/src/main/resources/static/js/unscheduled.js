var baseURL = 'http://localhost:8080/'

var addPortCallButton = $('#add-portcall-button');
var addPortCallForm = $('.add-portcall-input');
var portCallList = $('#list-of-portcalls');
var cargoInInput = $('#cargo-in-input');
var cargoOutInput = $('#cargo-out-input');
var laycanStartDateInput = $('#laycan-start-picker-date');
var laycanStartTimeInput = $('#laycan-start-picker-time');
var laycanEndDateInput = $('#laycan-end-picker-date');
var laycanEndTimeInput = $('#laycan-end-picker-time');
var nameInput = $('#name-input');
var vesselIdInput = $('#name-input');
var portcallIdInput = $('#portcall-id-input');


$('#load-port-calls').on('click',getAllPortCalls);

addPortCallButton.on('click', function () {
    addPortCallForm.removeClass('hidden');
});

addPortCallForm.on('submit', function(){

    var laycanStart = laycanStartDateInput.val() + 'T' + laycanStartTimeInput.val()+'Z';
    var laycanEnd   = laycanEndDateInput.val() + 'T' + laycanEndTimeInput.val()+'Z';

    console.log(laycanStart + '\n');
    console.log(laycanEnd + '\n');

    var newPortCall = {
        cargoIn : cargoInInput.val(),
        cargoOut : cargoOutInput.val(),
        laycanStart : laycanStart,
        laycanEnd : laycanEnd,
        name : nameInput.val(),
        vesselId : vesselIdInput.val(),
        portcallId : portcallIdInput.val()
    };

    $.ajax({
        url: baseURL + 'portcalls/add', // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });

    getAllPortCalls();
    return false;
});

function getAllPortCalls(){
 $.ajax({
     url: baseURL + 'portcalls', // this URL only works when the server is on the same machine!
     type: 'GET',
     success: updatePortCallList
 });
}

function updatePortCallList(listOfPortCalls, textStatus){
    console.log(typeof listOfPortCalls)
    portCallList.empty();
    for (i = 0; i < listOfPortCalls.length; i++){
        portCallList.append('<li>' +
            '<ul>' +
            '<li> id: ' + listOfPortCalls[i].internalId + '</li>' +
            '<li>'+ 'cargo in: ' + listOfPortCalls[i].cargoIn +'</li>' +
            '<li>'+  'cargo out: ' + listOfPortCalls[i].cargoOut +'</li>' +
            '</ul>' +
            '</li>')
    }
}

function clearAllInputs() {
    return;
}


// get all port calls immediately, and then every 10 seconds
getAllPortCalls();
setInterval(getAllPortCalls, 10000);