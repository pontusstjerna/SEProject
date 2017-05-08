var baseURL = 'http://localhost:8080/'

var addPortCallButton = $('#add-portcall-button');
var addPortCallForm = $('.add-portcall-input');
var portCallList = $('#list-of-portcalls');

$('#load-port-calls').on('click',getAllPortCalls);

addPortCallButton.on('click', function () {
    addPortCallForm.removeClass('hidden');
});

addPortCallForm.on('submit', function(){
    var newPortCall = {
        cargoIn : $('#cargo-in-input').val(),
        cargoOut : $('#cargo-out-input').val()
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
            '<li>'+ 'cargo in: ' + listOfPortCalls[i].cargoIn +'</li>' +
            '<li>'+  'cargo out: ' + listOfPortCalls[i].cargoOut +'</li>' +
            '</ul>' +
            '</li>')
    }
}

// get all port calls immediately, and then every 10 seconds
getAllPortCalls();
setInterval(getAllPortCalls, 10000);