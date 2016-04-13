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
function keyPressed() {
	document.getElementById("search-bar-container").classList.remove("error");
}
$(function() {
	$.ajax({
		method : 'get',
		url : window.location.protocol + '//' + window.location.host + "/neo4art-services/api/services/search/domains.json",
		dataType : 'json',
		success : function(data) {
			$.each(data, function() {
				this.label = this.text;
				this.value = this.text;
			});
			$("#search-bar").autocomplete({
				source : data
			});
		}
	});
});