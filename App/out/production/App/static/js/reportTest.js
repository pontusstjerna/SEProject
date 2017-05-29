var cargoCommenced = $('#cargoop-commenced-button');
var cargoCompleted = $('#cargoop-completed-button');
var readyToSail = $('#ready-to-sail-completed-button');
var slopopConfirmed = $('#slopop-confirmed-button');
var slopopDenied = $('#slopop-denied-button');
var slopopReceived = $('#slopop-reqreceived-button');
var arrivalVessel = $('#arrival-vessel-berth-button');
var departureVessel = $('#departure-vessel-berth-button');

var timeTypeSelect = $('#time-type-select');

var baseURL = 'http://localhost:8080/';

console.log('reportTest entered!');
var newPortCall = {
    vesselId: 'urn:mrn:stm:vessel:IMO:9398917'
    // portcallId: 'urn:x-mrn:stm:portcdm:port_call:SEGOT:ca1a795e-ee95-4c96-96d1-53896617c9ac'
};


readyToSail.on('click', function () {
    console.log('in readyToSail');
    timeType = timeTypeSelect.val().toString();
    console.log(typeof  timeType);
    console.log(timeType);

    $.ajax({
        url: baseURL + 'report/readytosailop/completed?time=2017-05-15T12:00:00Z&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

slopopConfirmed.on('click', function () {
    console.log('in slopopConfirmed');
    timeType = timeTypeSelect.val().toString();

    $.ajax({
        url: baseURL + 'report/slopop/confirmed?time=2017-05-15T12:00:00Z&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

slopopDenied.on('click', function () {
    console.log('in slopopDenied');
    timeType = timeTypeSelect.val().toString();

    $.ajax({
        url: baseURL + 'report/slopop/denied?time=2017-05-15T12:00:00Z&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

slopopReceived.on('click', function () {
    console.log('in slopopReceived');
    timeType = timeTypeSelect.val().toString();

    $.ajax({
        url: baseURL + 'report/slopop/received?time=2017-05-15T12:00:00Z&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

arrivalVessel.on('click', function () {
    console.log('in arrivalVessel');
    timeType = timeTypeSelect.val().toString();

    $.ajax({
        url: baseURL + 'report/vessel/arrival?time=2017-05-15T12:00:00Z&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

departureVessel.on('click', function () {
    console.log('in departureVessel');
    timeType = timeTypeSelect.val().toString();

    $.ajax({
        url: baseURL + 'report/vessel/departure?time=2017-05-15T12:00:00Z&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

cargoCommenced.on('click', function() {
    console.log("cargoCommenced clicked");
    timeType = timeTypeSelect.val().toString();

    $.ajax({
        url: baseURL + 'report/cargo/commenced?time=2017-05-15T12:00:00Z&timeType=' + timeType, // this URL only works when the server is on the same machine!
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});

cargoCompleted.on('click', function () {
    console.log("cargoCompleted clicked");
    timeType = timeTypeSelect.val().toString();
    $.ajax({
        url: baseURL + 'report/cargo/completed?time=2017-05-15T13:00:00Z&timeType=' + timeType,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(newPortCall),
        dataType: 'json'
    });
});


