var msgObj;
var oldPwdObj;
var newPwdObj;
var rePwdObj;

window.onload = function() {
  msgObj = document.getElementById("msg");
  oldPwdObj = document.getElementById("oldPwd");
  newPwdObj = document.getElementById("newPwd");
  rePwdObj = document.getElementById("rePwd");

  oldPwdObj.focus();
}

function checkForm() {
  var msg = "";
  var oldPwd = oldPwdObj.value.trim();
  var newPwd = newPwdObj.value.trim();
  var rePwd = rePwdObj.value.trim();
  if(!(oldPwd && newPwd && rePwd)) {
    msg = "提交条件不能有空值！";
  }
  if(newPwd != rePwd) {
    msg = "确认密码与新密码不一致！";
  }
  msgObj.style.color = "#ff5777";
  msgObj.innerHTML = msg;
  return msg == "";
}