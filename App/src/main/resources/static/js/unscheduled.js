var baseURL = 'http://localhost:8080/';

// How often we should poll the backend for new portcalls, in milliseconds
var pollingFrequency = 3000;

// Things we are interested in from HTML
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
var vesselIdInput = $('#vesselId-input');
var portcallIdInput = $('#portcall-id-input');
var cancelButton = $('#clear-new-portcall-button');

function openPortCall(id){
    window.open("/portcall?id=" + id,"_self");
}

function liHoverOut(internalId) {
    console.log("liHoverOut");
    var btn = $(this).find('.delete-portcall-btn');
    btn.addClass('hidden-icon');
}

function liHoverIn(internalId) {
    console.log(internalId);
    // console.log("liHoverIn: " + internalId);
    // var btn = $(this).find('.delete-portcall-btn');
    // btn.removeClass('hidden-icon');
    // btn.on('click', function (e) {
    //     e.stopImmediatePropagation();
    //     e.stopPropagation();
    //     if(confirm('Are you sure you want to delete this portcall?')) {
    //         deletePortCall(internalId);
    //     }
    // });
}

// $('#load-port-calls').on('click',getAllPortCalls);

addPortCallButton.on('click', function () {
    // addPortCallForm.removeClass('hidden');
    addPortCallForm.toggleClass('hidden');
    if(addPortCallButton.text() === 'Add Portcall') {
        addPortCallButton.text('Cancel');
        cargoInInput.focus();
    } else {
        addPortCallButton.text('Add Portcall')
    }
});

cancelButton.on('click',function(){
   clearAllInputs();
   addPortCallForm.addClass('hidden');
});

addPortCallForm.on('submit', function(){
    var laycanStart = laycanStartDateInput.val() + 'T' + laycanStartTimeInput.val() + 'Z';
    var laycanEnd   = laycanEndDateInput.val() + 'T' + laycanEndTimeInput.val() + 'Z';

    laycanStart.replace('T', ' ');
    laycanEnd.replace('T', ' ');

    console.log(laycanStart + '\n');
    console.log(laycanEnd + '\n');

    var newPortCall = {
        cargoIn : cargoInInput.val(),
        cargoOut : cargoOutInput.val(),
        laycanStart : laycanStart,
        laycanEnd : laycanEnd,
        name : nameInput.val(),
        vesselId : vesselIdInput.val(),
        portcallId : portcallIdInput.val(),
        internalId : 0
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

function updatePortCallList(listOfPortCalls, textStatus){
    portCallList.empty();
    for (i = 0; i < listOfPortCalls.length; i++){
        var portcall = listOfPortCalls[i];

        console.log(portcall);
        var htmlForLi = '<li class=show-info-li onclick=openPortCall(' + portcall.internalId + ')>' +
                '<div> <strong>Cargo to unload: </strong>' + portcall.cargoIn + '</div>' +
                '<div> <strong>Cargo to load: </strong>' + portcall.cargoOut + '</div>' +
                '<div> <strong>Laycan: </strong>' + portcall.laycanStart + ' to ' + portcall.laycanEnd +'</div>' +
                '<div> <strong>Name: </strong>' + portcall.name + '</div>' +
                '<div> <strong>Vessel IMO: </strong>' + portcall.vesselId + '</div>' +
                '<div> <strong>PortCDM Portcall ID: </strong>' + portcall.portcallId + '</div>' +
                '<button class="fa fa-trash-o delete-portcall-btn hidden-icon" aria-hidden="true" ' +
                    'onmouseenter=liHoverIn(10)></button>' +
                '</li>';
        console.log(portcall);
        portCallList.append(htmlForLi);
    }
}

function clearAllInputs() {
    cargoInInput.clear();
    cargoOutInput.clear();
    laycanStartTimeInput.clear();

    // var cargoOutInput = $('#cargo-out-input');
    // var laycanStartDateInput = $('#laycan-start-picker-date');
    // var laycanStartTimeInput = $('#laycan-start-picker-time');
    // var laycanEndDateInput = $('#laycan-end-picker-date');
    // var laycanEndTimeInput = $('#laycan-end-picker-time');
    // var nameInput = $('#name-input');
    // var vesselIdInput = $('#name-input');
    // var portcallIdInput = $('#portcall-id-input');
}

// portCallList.on('mouseenter', 'li', liHoverIn);
// portCallList.on('mouseleave', 'li', liHoverOut);



function deletePortCall(internalId) {
    $.ajax({
        url: 'portcalls/delete/' + internalId,
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json'
    });
}

function getAllPortCalls(){
    $.ajax({
        url: baseURL + 'portcalls', // this URL only works when the server is on the same machine!
        type: 'GET',
        success: updatePortCallList
    });
}

// get all port calls immediately, and then every pollingFrequency seconds
getAllPortCalls();
setInterval(getAllPortCalls, pollingFrequency);