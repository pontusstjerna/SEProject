var msgModal = document.getElementById('messageBox');
var msgButton = $('#btnSendPCM');

console.log("halloo");

msgButton.on('click',function(){
    msgModal.style.display = "block";
});

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    msgModal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        msgModal.style.display = "none";
    }
}