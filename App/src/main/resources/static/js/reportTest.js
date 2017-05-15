var sendButton = $('#send-button');
var baseURL = 'http://localhost:8080/';

console.log('reportTest entered!');

sendButton.on('click', function() {
    console.log("Button clicked");
    var newPortCall = {
        vesselId: 'urn:x-mrn:stm:vessel:IMO:9398917',
        portcallId: 'urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac'
    };

    $.ajax({
        url: baseURL + 'report/cargo/commenced/2017-05-15T14:00:00Z', // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });

});