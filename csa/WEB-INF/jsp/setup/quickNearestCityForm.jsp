<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="quickNearestCityForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
	<jsp:include page="createReadUpdateDeleteHidden.jsp" />
	<s:hidden key="provinceId" value="%{provinceId}"/>
	
	<s:if test='actionMethod == "update"'>	
		<s:textfield label="Nombre" id="cityName" name="cityName" readonly="%{readOnly}" required="true" maxlength="50" />
		
		<script type="text/javascript">
			$("#quickNearestCityForm").validate({
				rules: {
					cityName: {
						required:true,
						maxlength:512
					}
	
				}, 
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						url: $(form).attr("action"),
						type:"POST",
						success: function(cities) {
							var selectedId = $("#nearestCity").val();
			    		    var options = '<option value=""></option>';
				    		  
			    	      	for (var i = 0; i < cities.length; i++) {
			    	      		
			    	      		if(cities[i].id == selectedId) {
			    	      			options += '<option value="' + cities[i].id + '" selected="selected">' + cities[i].name + '</option>';
			    	      		} else {
			    		    		options += '<option value="' + cities[i].id + '">' + cities[i].name + '</option>';
			    	      		}
			    	      		
			    		  	}
		    		      
				    	  	$("#nearestCity").html(options);
							$(".quickNearestCityButton").button("enable");
							$("#quickNearestCityFormDialog").dialog("destroy");
							$("#nearestCity").selectmenu({style:"dropdown", width:400});
						},
						beforeSerialize: function(form, options) { 
							$(form).attr("requestId", $("#nearestCity").val()).attr("provinceId", $("#province").val());
						},
						dataType:"json"
					});
					
					
				}
			});
		</script>
	</s:if>
	<s:elseif test='actionMethod == "create"'>
		<s:textfield label="Nombre" id="cityName" name="cityName" readonly="%{readOnly}" required="true" maxlength="50" />
		<script type="text/javascript">
			$("#quickNearestCityForm").validate({
				rules: {
					cityName: {
						required:true,
						maxlength:512
					}
	
				}, 
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						url: $(form).attr("action"),
						type:"POST",
						success: function(cities) {
			    			var options = '<option value="" selected="selected"></option>';
				    		  
			    	      	for (var i = 0; i < cities.length; i++) {
			    		    	options += '<option value="' + cities[i].id + '">' + cities[i].name + '</option>';
			    		  	}
		    		      
				    	  	$("#nearestCity").html(options);	
				    	  	$("#quickNearestCityAdd").button("enable");
				    		$("#quickNearestCityFormDialog").dialog("destroy");
				    		$("#nearestCity").selectmenu({style:"dropdown", width:400});
						},
						dataType:"json"
					});
				}
			});
		</script>
	</s:elseif>
	<s:else>
		<s:textfield label="Nombre" id="cityName" name="cityName" readonly="%{readOnly}" required="false" maxlength="50" />
	</s:else>	
</s:form>