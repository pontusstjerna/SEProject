var addPortcallButton = $('#add-portcall-button');
// var submitPortcall = $('#submit-portcall-button');
var addPortCallForm = $('.add-portcall-input');

addPortcallButton.on('click', function () {
    addPortCallForm.removeClass('hidden');
});

addPortCallForm.on('submit', function(){
    var cargoInVal = $('#cargo-in-input').val();
    var cargoOutVal = $('#cargo-out-input').val();

    var newPortCall = {
        cargoIn : cargoInVal,
        cargoOut : cargoOutVal
    };

    // $.post('http://locahost:8080/portcalls/add', newPortCall, function () {
    //     alert("Portcall sent");
    // });

    $.ajax({
        url: 'http://localhost:8080/portcalls/add',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
    return false;
});