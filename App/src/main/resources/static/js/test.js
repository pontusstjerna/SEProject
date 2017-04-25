window.onload = function () {

  }

  function GetTestShip(){
      document.getElementById("result").innerHTML = httpGet("/ship");
  }

  function httpGet(url) {
    var xmlhttp;
    var response = "";
    if (window.XMLHttpRequest) {
      xmlhttp = new XMLHttpRequest();
    } else {
      // code for older browsers, such as IE 5 or 6
      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        response = this.responseText;
      }
    };
    xmlhttp.open("GET", url, false);
    xmlhttp.send();

    return response;
  }