var msgObj;
var rdDateRegObj;
var rdTypeObj;
var rdDeptObj;
var rdNameObj;


window.onload = function() {
  msgObj = document.getElementById("msg");
  rdDateRegObj = document.getElementById("rdDateReg");
  rdTypeObj = document.getElementById("rdType");
  rdDeptObj = document.getElementById("rdDept");
  rdNameObj = document.getElementById("rdName");

  rdDateRegObj.innerHTML = new Date(rdDateRegObj.innerHTML).toLocaleDateString();
}

function checkForm() {
  var msg = "";
  var rdDept = rdDeptObj.value.trim();
  var rdName = rdNameObj.value.trim();
  if(!rdName) {
    msg = "姓名不能为空！";
  }
  if(!rdDept) {
    msg = "所在单位不能为空！";
  }
  msgObj.style.color = "#ff5777";
  msgObj.innerHTML = msg;
  return msg == "";
}