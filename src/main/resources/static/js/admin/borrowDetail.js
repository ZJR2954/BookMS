var IdDateOutObj;
var IdDateRetPlanObj;
var IdDateRetActObj;

window.onload = function() {
  IdDateOutObj = document.getElementById("IdDateOut");
  IdDateRetPlanObj = document.getElementById("IdDateRetPlan");
  IdDateRetActObj = document.getElementById("IdDateRetAct");

  IdDateOutObj.innerHTML = new Date(IdDateOutObj.innerHTML).toLocaleDateString();
  IdDateRetPlanObj.innerHTML = new Date(IdDateRetPlanObj.innerHTML).toLocaleDateString();
  IdDateRetActObj.innerHTML = new Date(IdDateRetActObj.innerHTML).toLocaleDateString();
}