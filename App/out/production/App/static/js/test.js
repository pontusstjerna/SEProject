var queueId;
$("#subscribe").on("click", function (e) {
    $.ajax({
        url: "http://localhost:8080/queue/subscribe",
        context: document.body
    }).done(function(data) {
        $('#queueId').text(data)
        queueId = $('#queueId').text();
    });
});

$('#getNewMessages').on('click', function (e) {

   $.ajax({
       url: "http://localhost:8080/queue/" + queueId,
       context: document.body
   }).done(function(data) {
       data.forEach(function (m){
           $('#resultList').append("<li><span>" + m.messageId + "," + m.vesselId + "</span></li>");
       });
    });
});

window.onload = function () {

  }

  function GetTestShip(){
      document.getElementById("result").innerHTML = httpGet("/port_calls");
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