var rdMsgObj;
var rdIDObj;

var bkMsgObj;
var bkIDObj;

window.onload = function() {
  rdMsgObj = document.getElementById("rdMsg");
  rdIDObj = document.getElementById("rdID");

  bkMsgObj = document.getElementById("bkMsg");
  bkIDObj = document.getElementById("bkID");

  var borrowManager = document.getElementById("borrowManager");
  borrowManager.style.backgroundColor = "#8695a7";
  borrowManager.style.color = "#fff";

  rdIDObj.focus();
}

function checkReaderForm() {
  var msg = "";
  var rdID = rdIDObj.value.trim();
  if(!rdID) {
    msg = "查询条件不能为空！";
  }
  rdMsgObj.style.color = "#ff5777";
  rdMsgObj.innerHTML = msg;
  return msg == "";
}

function checkBookForm() {
  var msg = "";
  var bkID = bkIDObj.value.trim();
  if(!bkID) {
    msg = "查询条件不能为空！";
  }
  bkMsgObj.style.color = "#ff5777";
  bkMsgObj.innerHTML = msg;
  return msg == "";
}