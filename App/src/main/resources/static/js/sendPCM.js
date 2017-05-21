// Get all the <span> elements that closes the modals, in a linked list
var span = document.getElementsByClassName("close");
//Get modals and buttons
var btnCargoCommenced = $('#btnSendCargoCommenced');
var modalCargoCommenced = document.getElementById('modalCargoCommenced');
var btnCargoCompleted = $('#btnSendCargoCompleted');
var modalCargoCompleted = document.getElementById('modalCargoCompleted');
var btnSlopConfirmed = $('#btnSendSlopConfirmed');
var modalSlopConfirmed = document.getElementById('modalSlopConfirmed');
var btnSlopDenied = $('#btnSendSlopDenied');
var modalSlopDenied = document.getElementById('modalSlopDenied');
var btnSlopReqRec = $('#btnSendSlopReqRec');
var modalSlopReqRec = document.getElementById('modalSlopReqRec');
var btnReadyToSail = $('#btnSendReadyToSail');
var modalReadyToSail = document.getElementById('modalReadyToSail');
var btnArrivalVesselBerth = $('#btnSendArrivalVesselBerth');
var modalArrivalVesselBerth = document.getElementById('modalArrivalVesselBerth');
var btnDepartureVesselBerth = $('#btnSendDepartureVesselBerth');
var modalDepartureVesselBerth = document.getElementById('modalDepartureVesselBerth');

//Make the modals open and close via buttons
btnCargoCommenced.on('click',function(){
    modalCargoCommenced.style.display = "block";
});
span[0].onclick = function() {
    modalCargoCommenced.style.display = "none";// When the user clicks on <span> (x), close the modal
}
btnCargoCompleted.on('click',function(){
    modalCargoCompleted.style.display = "block";
});
span[1].onclick = function() {
    modalCargoCompleted.style.display = "none";
}
btnSlopConfirmed.on('click',function(){
    modalSlopConfirmed.style.display = "block";
});
span[2].onclick = function() {
    modalSlopConfirmed.style.display = "none";
}
btnSlopDenied.on('click',function(){
    modalSlopDenied.style.display = "block";
});
span[3].onclick = function() {
    modalSlopDenied.style.display = "none";
}
btnSlopReqRec.on('click',function(){
    modalSlopReqRec.style.display = "block";
});
span[4].onclick = function() {
    modalSlopReqRec.style.display = "none";
}
btnReadyToSail.on('click',function(){
    modalReadyToSail.style.display = "block";
});
span[5].onclick = function() {
    modalReadyToSail.style.display = "none";
}
btnArrivalVesselBerth.on('click',function(){
    modalArrivalVesselBerth.style.display = "block";
});
span[6].onclick = function() {
    modalArrivalVesselBerth.style.display = "none";
}
btnDepartureVesselBerth.on('click',function(){
    modalDepartureVesselBerth.style.display = "block";
});
span[7].onclick = function() {
    modalDepartureVesselBerth.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
//window.onclick = function(event) {
//    if (event.target == modal) {
//        modalSlopDenied.style.display = "none";
//    }
//}

