<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form id="quickProvinceForm" action="%{mappedRequest}" cssClass="ui-dialog-content">	
	<jsp:include page="createReadUpdateDeleteHidden.jsp" />
	
	<s:if test='actionMethod == "update"'>	
		<s:textfield id="provinceName" label="Nombre" name="provinceName" readonly="%{readOnly}" required="true" maxlength="50" />
		
		<script type="text/javascript">
			$("#quickProvinceForm").validate({
				rules: {
					provinceName: {
						required:true,
						maxlength:512
					}
	
				}, 
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						url: $(form).attr("action"),
						type:"POST",
						success: function(provinces) {
							var selectedId = $("#province").val();
			    		    var options = '<option value=""></option>';
				    		  
			    	      	for (var i = 0; i < provinces.length; i++) {
			    	      		
			    	      		if(provinces[i].id == selectedId) {
			    	      			options += '<option value="' + provinces[i].id + '" selected="selected">' + provinces[i].name + '</option>';
			    	      		} else {
			    		    		options += '<option value="' + provinces[i].id + '">' + provinces[i].name + '</option>';
			    	      		}
			    	      		
			    		  	}
		    		      
				    	  	$("#province").html(options);
							$(".quickProvinceButton").button("enable");
							$("#quickProvinceFormDialog").dialog("destroy");
							$("#province").selectmenu({style:"dropdown", width:480});
						},
						beforeSerialize: function(form, options) { 
							$(form).attr("requestId", $("#province").val());
						},
						dataType:"json"
					});
				}
			});
		</script>
	</s:if>
	<s:elseif test='actionMethod == "create"'>
		<s:textfield label="Nombre" id="provinceName" name="provinceName" readonly="%{readOnly}" required="true" maxlength="50" />
		<script type="text/javascript">
			$("#quickProvinceForm").validate({
				rules: {
					provinceName: {
						required:true,
						maxlength:512
					}
	
				}, 
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						url: $(form).attr("action"),
						type:"POST",
						success: function(provinces) {
			    			var options = '<option value="" selected="selected"></option>';
				    		  
			    	      	for (var i = 0; i < provinces.length; i++) {
			    		    	options += '<option value="' + provinces[i].id + '">' + provinces[i].name + '</option>';
			    		  	}
		    		      
				    	  	$("#province").html(options);	
				    	  	$("#quickProvinceAdd").button("enable");
				    	  	$("#quickProvinceFormDialog").dialog("destroy");
				    	  	$("#province").selectmenu({style:"dropdown", width:480});
						},
						dataType:"json"
					});
				}
			});
		</script>
	</s:elseif>
	<s:else>
		<s:textfield id="provinceName" label="Nombre" name="provinceName" readonly="%{readOnly}" required="false" maxlength="50" />
	</s:else>
</s:form>