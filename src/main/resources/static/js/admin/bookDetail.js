var msgObj;
var bkDatePressObj;
var bkDateInObj;

window.onload = function() {
  msgObj = document.getElementById("msg");
  bkDatePressObj = document.getElementById("bkDatePress");
  bkDateInObj = document.getElementById("bkDateIn");

  bkDatePressObj.innerHTML = new Date(bkDatePressObj.innerHTML).toLocaleDateString();
  bkDateInObj.innerHTML = new Date(bkDateInObj.innerHTML).toLocaleDateString();
}