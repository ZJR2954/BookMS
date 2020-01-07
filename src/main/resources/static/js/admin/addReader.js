var msgObj;
var rdTypeObj;
var rdDeptObj;
var rdNameObj;
var reMsgObj;
var reRdTypeObj;
var reRdDeptObj;
var reRdNameObj;
var rdDateRegObj;

window.onload = function() {
  msgObj = document.getElementById("msg");
  rdTypeObj = document.getElementById("rdType");
  rdDeptObj = document.getElementById("rdDept");
  rdNameObj = document.getElementById("rdName");

  reMsgObj = document.getElementById("reMsg");
  reRdTypeObj = document.getElementById("reRdType");
  reRdDeptObj = document.getElementById("reRdDept");
  reRdNameObj = document.getElementById("reRdName");
  rdDateRegObj = document.getElementById("rdDateReg");

  rdDateRegObj.valueAsDate = new Date();

  rdDeptObj.focus();

}

function checkFindForm() {
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

function checkReForm() {
  var reMsg = "";
  var reRdDept = reRdDeptObj.value.trim();
  var reRdName = reRdNameObj.value.trim();
  if(!(reRdDept&&reRdName)) {
    reMsg = "请先查询基本信息是否重复！";
  }
  reMsgObj.style.color = "#ff5777";
  reMsgObj.innerHTML = reMsg;
  return reMsg == "";
}