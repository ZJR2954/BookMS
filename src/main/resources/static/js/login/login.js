var msgObj;
var usernameObj;
var passwordObj;

window.onload = function() {
msgObj = document.getElementById("msg");
usernameObj = document.getElementById("username");
passwordObj = document.getElementById("password");
usernameObj.focus();
}

function checkForm() {
	var msg = "";
	var username = usernameObj.value.trim();
	var password = passwordObj.value.trim();
	if(!password) {
		msg = "密码不能为空！"
	}
	if(!username) {
		msg = "账号不能为空！";
	}
	msgObj.style.color = "#ff5777";
	msgObj.innerHTML = msg;
	return msg == "";
}
