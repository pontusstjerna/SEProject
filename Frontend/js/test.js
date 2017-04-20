window.onload = function () {
  alert('I am alive!');

  document.getElementById("pontusIsGood").onclick = function() {
    document.getElementById("pontusIsGood").innerHTML = "Oh no!";
  }
}