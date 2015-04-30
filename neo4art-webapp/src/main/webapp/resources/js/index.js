function focused() {
	document.getElementById("search-bar-container").classList.add("focused");
}
function unfocused() {
	document.getElementById("search-bar-container").classList.remove("focused");
}
function clicked() {
	if (document.searchForm.query.value != "") {
		document.forms["searchForm"].submit();
	} else {
		document.getElementById("search-bar-container").classList.add("error");
	}
}
function keyPressed(){
	document.getElementById("search-bar-container").classList.remove("error");
}
