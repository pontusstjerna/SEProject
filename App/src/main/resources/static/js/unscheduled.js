var addPortCallButton = $('#add-portcall-button');
var addPortCallForm = $('.add-portcall-input');

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