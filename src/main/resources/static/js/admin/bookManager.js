var msgObj;
var bkCatalogObj;
var bkIDObj;
var bkCodeObj;
var bkNameObj;
var bkAuthorObj;
var bkPressObj;

window.onload = function() {
  msgObj = document.getElementById("msg");
  bkCatalogObj = document.getElementById("bkCatalog");
  bkIDObj = document.getElementById("bkID");
  bkCodeObj = document.getElementById("bkCode")
  bkNameObj = document.getElementById("bkName")
  bkAuthorObj = document.getElementById("bkAuthor")
  bkPressObj = document.getElementById("bkPress")

  var bookManager = document.getElementById("bookManager");
  bookManager.style.backgroundColor = "#8695a7";
  bookManager.style.color = "#fff";

  bkIDObj.focus();
}

function checkForm() {
  var msg = "";
  var bkCatalog =bkCatalogObj.value.trim();
  var bkID = bkIDObj.value.trim();
  var bkCode = bkCodeObj.value.trim();
  var bkName = bkNameObj.value.trim();
  var bkAuthor = bkAuthorObj.value.trim();
  var bkPress = bkPressObj.value.trim();
  if(!(bkID || bkCode || bkName || bkAuthor || bkPress)) {
    msg = "查询条件不能全为空！";
  }
  if(!bkCatalog) {
    msg = "请选择图书分类号！";
  }
  msgObj.style.color = "#ff5777";
  msgObj.innerHTML = msg;
  return msg == "";
}