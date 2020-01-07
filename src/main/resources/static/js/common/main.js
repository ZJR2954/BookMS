function leftBarItemClicked(e, path) {
  e.style.backgroundColor = "#8695a7";
  e.style.color = "#fff";
  var pathName = window.location.pathname.substring(1);
  var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
  location.href = window.location.protocol + '//' + window.location.host + '/'+ webName + path;
}