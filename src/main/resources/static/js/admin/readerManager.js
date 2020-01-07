var msgObj;
var rdTypeObj;
var rdIDObj;
var rdDeptObj;
var rdNameObj;

window.onload = function() {
  msgObj = document.getElementById("msg");
  rdTypeObj = document.getElementById("rdType");
  rdIDObj = document.getElementById("rdID");
  rdDeptObj = document.getElementById("rdDept");
  rdNameObj = document.getElementById("rdName");

  var readerManager = document.getElementById("readerManager");
  readerManager.style.backgroundColor = "#8695a7";
  readerManager.style.color = "#fff";

  rdIDObj.focus();
}

function checkForm() {
  var msg = "";
  var rdType = rdTypeObj.value.trim();
  var rdID = rdIDObj.value.trim();
  var rdDept = rdDeptObj.value.trim();
  var rdName = rdNameObj.value.trim();
  if(!(rdID || rdDept || rdName)) {
    msg = "查询条件不能全为空！";
  }
  if(!rdType) {
    msg = "请选择读者类型！";
  }
  msgObj.style.color = "#ff5777";
  msgObj.innerHTML = msg;
  return msg == "";
}