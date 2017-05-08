var addPortCallButton = $('#add-portcall-button');
var addPortCallForm = $('.add-portcall-input');
var portCallList = $('#list-of-portcalls');

$('#load-port-calls').on('click',getAllPortCalls);

addPortCallButton.on('click', function () {
    addPortCallForm.removeClass('hidden');
});

addPortCallForm.on('submit', function(){
    // var cargoInVal = $('#cargo-in-input').val();
    // var cargoOutVal = $('#cargo-out-input').val();

    var newPortCall = {
        cargoIn : $('#cargo-in-input').val(),
        cargoOut : $('#cargo-out-input').val()
    };

    $.ajax({
        url: 'http://localhost:8080/portcalls/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });

    return false;
});

function getAllPortCalls(){
 $.ajax({
     url: 'http://localhost:8080/portcalls',
     type: 'GET',
     success: updatePortCallList
 });
}

function updatePortCallList(listOfPortCalls, textStatus){
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