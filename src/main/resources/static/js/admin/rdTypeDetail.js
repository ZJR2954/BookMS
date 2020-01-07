var msgObj;
var rdTypeNameObj;
var canLendQtyObj;
var canLendDayObj;
var canContinueTimesObj;
var punishRateObj;
var dateValidObj;

window.onload = function() {
  msgObj = document.getElementById("msg");
  rdTypeNameObj = document.getElementById("rdTypeName");
  canLendQtyObj = document.getElementById("canLendQty");
  canLendDayObj = document.getElementById("canLendDay");
  canContinueTimesObj = document.getElementById("canContinueTimes");
  punishRateObj = document.getElementById("punishRate");
  dateValidObj = document.getElementById("dateValid");
}

function checkForm() {
  var msg = "";
  var rdTypeName = rdTypeNameObj.value.trim();
  var canLendQty = canLendQtyObj.value.trim();
  var canLendDay = canLendDayObj.value.trim();
  var canContinueTimes = canContinueTimesObj.value.trim();
  var punishRate = punishRateObj.value.trim();
  var dateValid = dateValidObj.value.trim();
  if(!(rdTypeName&&canLendQty&&canLendDay&&canContinueTimes&&punishRate&&dateValid)) {
    msg = "请将信息填写完整！";
  }
  msgObj.style.color = "#ff5777";
  msgObj.innerHTML = msg;
  return msg == "";
}