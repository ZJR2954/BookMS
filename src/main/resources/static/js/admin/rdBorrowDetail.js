var IdDateOutObjs;
var IdDateRetPlanObjs;

window.onload = function() {
  IdDateOutObjs = document.getElementsByName("IdDateOut");
  IdDateRetPlanObjs = document.getElementsByName("IdDateRetPlan");

  for(var i = 0; i < IdDateOutObjs.length; i++) {
    IdDateOutObjs[i].innerHTML = new Date(IdDateOutObjs[i].innerHTML).toLocaleDateString();

  }
  for(var i = 0; i < IdDateRetPlanObjs.length; i++) {
    IdDateRetPlanObjs[i].innerHTML = new Date(IdDateRetPlanObjs[i].innerHTML).toLocaleDateString();
  }

}