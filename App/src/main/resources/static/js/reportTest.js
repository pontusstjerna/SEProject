var cargoCommenced = $('#cargoop-commenced-button');
var cargoCompleted = $('#cargoop-completed-button');
var baseURL = 'http://localhost:8080/';

console.log('reportTest entered!');
var newPortCall = {
    vesselId: 'urn:x-mrn:stm:vessel:IMO:9398917',
    portcallId: 'urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac'
};

cargoCommenced.on('click', function() {
    console.log("cargoCommenced clicked");
    $.ajax({
        url: baseURL + 'report/cargo/commenced?time=2017-05-15T12:00:00Z&timeType=ACTUAL', // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

cargoCompleted.on('click', function () {
    console.log("cargoCompleted clicked");
    $.ajax({
        url: baseURL + 'report/cargo/completed/2017-05-16T14:00:00Z',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});