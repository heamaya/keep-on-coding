
	alert("tosty!");
	var containingForm = document.getElementById("${parameters.formName?html}");
		StrutsUtils.addEventListener(containingForm, "submit", 
			function(evt) {
				selectAllOptionsExceptSome(document.getElementById('${parameters.id?html}'), 'key', '${parameters.headerKey}');
			}, true);

		StrutsUtils.addEventListener(containingForm, "submit", 
			function(evt) {
				selectAllOptionsExceptSome(document.getElementById('${parameters.doubleId?html}'), 'key', '${parameters.doubleHeaderKey}');
			}, true);


