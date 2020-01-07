var msgObj;
var bkNameObj;
var bkAuthorObj;
var bkPressObj;
var reMsgObj;
var reBkCatalogObj;
var reBkNameObj;
var reBkAuthorObj;
var reBkPressObj;
var bkDatePressObj;
var bkDateInObj;

window.onload = function() {
  msgObj = document.getElementById("msg");
  bkNameObj = document.getElementById("bkName");
  bkAuthorObj = document.getElementById("bkAuthor");
  bkPressObj = document.getElementById("bkPress");
  reMsgObj = document.getElementById("reMsg");
  reBkCatalogObj = document.getElementById("reBkCatalog");
  reBkNameObj = document.getElementById("reBkName");
  reBkAuthorObj = document.getElementById("reBkAuthor");
  reBkPressObj = document.getElementById("reBkPress");
  bkDatePressObj = document.getElementById("bkDatePress")
  bkDateInObj = document.getElementById("bkDateIn");

  bkDateInObj.valueAsDate = new Date();

  bkIDObj.focus();
}

function checkForm() {
  var msg = "";
  var bkName = bkNameObj.value.trim();
  var bkAuthor = bkAuthorObj.value.trim();
  var bkPress = bkPressObj.value.trim();
  if(!bkPress) {
    msg = "出版社不能为空！";
  }
  if(!bkAuthor) {
    msg = "作者不能为空！";
  }
  if(!bkName) {
    msg = "书名不能为空！";
  }
  msgObj.style.color = "#ff5777";
  msgObj.innerHTML = msg;
  return msg == "";
}

function checkReForm() {
  var reMsg = "";
  var reBkCatalog = reBkCatalogObj.value.trim();
  var reBkName = reBkNameObj.value.trim();
  var reBkAuthor = reBkAuthorObj.value.trim();
  var reBkPress = reBkPressObj.value.trim();
  var bkDatePress = bkDatePressObj.value.trim();
  var bkDateIn = bkDateInObj.value.trim();

  if(!(bkDatePress&&bkDateIn)) {
    reMsg = "出版日期和入馆日期不能为空！";
  }
  if(!(reBkCatalog&&reBkName&&reBkAuthor&&reBkPress)) {
    reMsg = "请先查询基本信息是否重复！";
  }
  reMsgObj.style.color = "#ff5777";
  reMsgObj.innerHTML = reMsg;
  return reMsg == "";
}