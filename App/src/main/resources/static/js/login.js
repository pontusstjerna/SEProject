var username = document.getElementById("username");
var password = document.getElementById("password");
var error = document.getElementById("error");

document.getElementById("login")
    .addEventListener("keyup", function(event) {
    event.preventDefault();
    if (event.keyCode == 13) {
        document.getElementById("login").click();
    }
});

function login(){

    //I know this is ugly AF but hey, it works
    window.open("/login?user=" + username.value + "&password=" + password.value,"_self");
}