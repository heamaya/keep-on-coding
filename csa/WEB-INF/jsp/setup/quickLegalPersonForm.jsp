	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="quickCompanyForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden name="previousPath" value="%{previousPath}" />
		
		<s:if test='actionMethod == "update"'>
			<s:textfield id="name" label="Nombre" name="name" required="true" size="40" />
			<s:textfield id="tributeKey" label="CUIT" name="tributeKey" required="true" size="40" />
			<s:select id="taxCondition" label="Condición Frente a la AFIP" list="taxConditions" name="taxCondition" listKey="id" listValue="value" emptyOption="true" required="true" value="taxCondition.id" required="true" />
			<s:select id="agricultorFarmer" label="Productor Agropecuario" list="agricultorFarmers" name="farmer" listKey="id" listValue="summary" emptyOption="true" required="true" value="farmer.id"/>
			<jsp:include page="quickFarmer.jsp" />
			<jsp:include page="addressFormFields.jsp" />
			
			<script type="text/javascript">
			
				$(function() {
					
					if($("#agricultorFarmer").val() == "") {
		    			$("#quickFarmerEdit, #quickFarmerShow").button("disable");
		    			$("#quickFarmerAdd").button("enable");
		    		} else {
		    			$("#quickFarmerAdd, #quickFarmerEdit, #quickFarmerShow").button("enable");
					}
					
					$("select").selectmenu({style:"dropdown", width:400});
					
					$("#agricultorFarmer").change(
					    function() {
					    			
						    if($("#agricultorFarmer").val() == "") {
						   		$("#quickFarmerEdit, #quickFarmerShow").button("disable");
						   		$("#quickFarmerAdd").button("enable");
						   	} else {
						   		$("#quickFarmerAdd, #quickFarmerEdit, #quickFarmerShow").button("enable");
							}
					    			
						}		
					);

					$("#quickCompanyForm").validate({
						rules: {
							name: {
								required: true,
								minlength: 1,
								maxlength:512
							},
							tributeKey: {
								required: true,
								digits:true,
								minlength:11,
								maxlength:11
							},
							taxCondition:"required",
							province:"required",
							street: {
								maxlength:512							
							},
							streetNumber: {
								digits:true,
								maxlength:10							
							},
							floor: {
								digits:true,
								maxlength:3							
							},
							department: {
								maxlength:255							
							}
						},
						submitHandler: function(form) {
							$(form).ajaxSubmit({
								url: $("#quickCompanyForm").attr("action"),
								type:"POST",
								success: function(companies) {
									$("#quickCompanyFormDialog").dialog("close");
									var selectedId = $("#company").val();
					    		    var options = '<option value=""></option>';
						    		  
					    	      	for (var i = 0; i < companies.length; i++) {
					    	      		
					    	      		if(companies[i].id == selectedId) {
					    	      			options += '<option value="' + companies[i].id + '" selected="selected">' + companies[i].summary + '</option>';
					    	      		} else {
					    		    		options += '<option value="' + companies[i].id + '">' + companies[i].summary + '</option>';
					    	      		}
					    	      		
					    		  	}
				    		      
						    	  	$("#company").html(options);
									$("#quickCompanyFormDialog").dialog("destroy");
									$("#company").selectmenu({style:"dropdown", width:480});
								},
								beforeSerialize: function(form, options) { 
									$(form).attr("requestId", $("#company").val());
								},
								dataType:"json"
							});
														
						}
					});
				});
			</script>
		</s:if>
		<s:elseif test='actionMethod == "create"'>
			<s:textfield id="name" label="Nombre" name="name" required="true" size="40" />
			<s:textfield id="tributeKey" label="CUIT" name="tributeKey" required="true" size="40" />
			<s:select id="taxCondition" label="Condición Frente a la AFIP" list="taxConditions" name="taxCondition" listKey="id" listValue="value" emptyOption="true" required="true" value="taxCondition.id" required="true" />
			<s:select id="agricultorFarmer" label="Productor Agropecuario" list="agricultorFarmers" name="farmer" listKey="id" listValue="summary" emptyOption="true" required="true" value="farmer.id"/>
			<jsp:include page="quickFarmer.jsp" />
			<jsp:include page="addressFormFields.jsp" />
			
			<script type="text/javascript">
			
				$(function() {
					
					if($("#agricultorFarmer").val() == "") {
		    			$("#quickFarmerEdit, #quickFarmerShow").button("disable");
		    			$("#quickFarmerAdd").button("enable");
		    		} else {
		    			$("#quickFarmerAdd, #quickFarmerEdit, #quickFarmerShow").button("enable");
					}
					
					$("select").selectmenu({style:"dropdown", width:400});
					
					$("#agricultorFarmer").change(
					    function() {
					    			
						    if($("#agricultorFarmer").val() == "") {
						   		$("#quickFarmerEdit, #quickFarmerShow").button("disable");
						   		$("#quickFarmerAdd").button("enable");
						   	} else {
						   		$("#quickFarmerAdd, #quickFarmerEdit, #quickFarmerShow").button("enable");
							}
					    			
						}		
					);

					$("#quickCompanyForm").validate({
						rules: {
							companyType:"required",
							name: {
								required: true,
								minlength: 1,
								maxlength:512
							},
							tributeKey: {
								required: true,
								digits:true,
								minlength:11,
								maxlength:11
							},
							taxCondition:"required",
							province:"required",
							street: {
								maxlength:512							
							},
							streetNumber: {
								digits:true,
								maxlength:10							
							},
							floor: {
								digits:true,
								maxlength:3							
							},
							department: {
								maxlength:255							
							}
						},
						submitHandler: function(form) {
							$(form).ajaxSubmit({
								url: $("#quickCompanyForm").attr("action"),
								type:"POST",
								success: function(companies) {
					    			var options = '<option value="" selected="selected"></option>';
						    		  
					    	      	for (var i = 0; i < companies.length; i++) {
					    		    	options += '<option value="' + companies[i].id + '">' + companies[i].summary + '</option>';
					    		  	}
				    		      
						    	  	$("#company").html(options);	
						    	  	$("#quickCompanyFormDialog").dialog("destroy");
						    	  	$("#company").selectmenu({style:"dropdown", width:480});
								},
								dataType:"json"
							});
						}
					});
				});
			</script>		
		</s:elseif>
		<s:else>
			<s:textfield label="Nombre" value="%{name}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="CUIT" value="%{tributeKey}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="Condición Frente a la AFIP" value="%{taxCondition.value}"  required="false" readonly="%{readOnly}" size="40" />
			<s:textfield id="agricultorFarmer" label="Productor Agropecuario" value="%{farmer.summary}"  required="false" readonly="%{readOnly}" size="40" />
			<jsp:include page="addressFormFields.jsp" />		
		</s:else>
	</s:form>