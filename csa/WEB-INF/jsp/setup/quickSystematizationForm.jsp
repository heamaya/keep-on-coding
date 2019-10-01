	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="quickSystematizationForm" action="%{mappedRequest}" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8" method="post" >
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden key="previousSystematizationFileName" value="%{previousSystematizationFileName}" />
		<s:hidden key="previousSystematizationPath" value="%{previousSystematizationPath}" />
		 	
 		<s:if test='actionMethod == "create"'>
			<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			
			<s:select id="company" list="companies" label="Empresa Agropecuaria" listKey="id" listValue="summary" value="company.id" name="company" emptyOption="true" required="true" /> 
			<s:radio id="companyTypes" list="companyTypes" label="Tipo de Empresa" listKey="id" listValue="value" name="companyTypeToAdd" />

			<jsp:include page="quickCompany.jsp" />
			
			<s:select id="land" list="lands" label="Campo" listKey="id" listValue="name" value="land.id" name="land" emptyOption="true" required="true"/>
			
			<jsp:include page="quickLand.jsp" />
			<s:file id="systematizationFile" name="systematization" label="Sistematización del Campo (KMZ o KML)"/>
			<s:checkbox label="Mostrar como Trabajo Destacado" name="starred" fieldValue="true" required="true" />
		</s:if>
		<s:elseif test='actionMethod == "update"'>
			<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="5" cols="50" maxlength="4096" />
			
			<s:select id="company" list="companies" label="Empresa Agropecuaria" listKey="id" listValue="name" value="company.id" name="company" emptyOption="true" required="true" />
			<s:select id="land" list="lands" label="Campo" listKey="id" listValue="name" value="land.id" name="land" emptyOption="true" required="true"/>

			<s:if test="systematizationFileName == null || systematizationFileName == ''">
				<s:file id="systematizationFile" name="systematization" label="Sistematización del Campo (KMZ o KML)"/>
			</s:if>
			<s:else>
				<s:checkbox id="keepSystematization" value="true" name="keepSystematization" label="Mantener Archivo de Sistematización del Campo"/>
				<s:file id="systematizationFile" name="systematization" label="Sistemtizacón del Campo (KMZ o KML)" />
			</s:else>	
			
			<s:checkbox label="Mostrar como Trabajo Destacado" name="starred" fieldValue="true" required="true" />	
		</s:elseif>
		<s:elseif test='actionMethod == "find"'>	
		</s:elseif>
		<s:else>
			<s:textfield label="Empresa Agropecuaria" value="%{company.name}" required="false" readonly="%{readOnly}" />
			<s:textfield label="Campo" value="%{land.name}" required="false" readonly="%{readOnly}" />
			<s:textarea id="descriptionSpanish" label="Descripción en Español" name="descriptionSpanish" readonly="%{readOnly}" required="false" rows="12" cols="28" maxlength="4096" />
			<s:textarea id="descriptionPortuguese" label="Descripción en Portugués" name="descriptionPortuguese" readonly="%{readOnly}" required="false" rows="12" cols="28" maxlength="4096" />
			<s:textarea id="descriptionEnglish" label="Descripción en Inglés" name="descriptionEnglish" readonly="%{readOnly}" required="false" rows="12" cols="28" maxlength="4096" />
			
			<s:if test="starred == true">
   				<s:set name="isStarred">Sí</s:set>
   			</s:if>
   			<s:else>
   				<s:set name="isStarred">No</s:set>
   			</s:else>
   			
   			<s:textfield label="Es un trabajo destacado" value="%{#isStarred}" required="false" readonly="%{readOnly}" />
		</s:else>
	</s:form>
	
	<jsp:include page="formSubmit.jsp" />
		
	<script type="text/javascript">
			
		$(function() {
		    	
			var actionMethod = $("#actionMethod").val();
	          	
	  		if(actionMethod == "update") {
				$("#company, #land").attr("disabled", "disabled");
	  		}
		    	
	    	if($("#company").val() == "") {
	    		$("#quickLandAdd, #quickLandShow, #quickLandEdit, #quickCompanyEdit, #quickCompanyShow").button("disable");
	    		$("#quickCompanyAdd").button("enable");
	    	} else {
	    		$("#quickCompanyAdd, #quickCompanyEdit, #quickCompanyShow, #quickLandAdd").button("enable");
    		  			    			
		 		if($("#land").val() == "") {
					$("#quickLandEdit, #quickLandShow").button("disable");
					$("#quickLandAdd").button("enable");
				} else {
					$("#quickLandAdd, #quickLandEdit, #quickLandShow").button("enable");
				} 
			}
 	          	  
	  		if(actionMethod == "create" || actionMethod == "update") {
	  				
				$("#systematizationFile").fileinput({
					buttonText: "Buscar",
					inputText: ""
				});
				
				$("select").selectmenu({style:"dropdown", width:400});
		  			
				$("label[for^='companyTypes']").css(
					{
						fontFamily: "segoe ui, Arial, sans-serif",
						fontSize: "xx-small"
					}
				);
				
				$("#companyTypes1").attr('checked', 'checked');
		  			
		        $("#company").change(
		    		function(event) {
		    			$("#land").html('<option value=""></option>');
		    	          		   
		    			if($("#company").val() != "") {
		    				
		    				$("#quickCompanyEdit, #quickCompanyShow, #quickLandAdd").button("enable");
		    				$("#quickCompanyAdd").button("disable");
		    				
		    				$("input[type='radio'], label[for^='companyTypes']").hide();
		    				var companySummary = new Array(); 
		    				companySummary = $("#company option:selected").text().split(",");
		    				$("input[id^='companyTypes']").removeAttr("checked");
		    				
		    				if(companySummary[0] == "Persona Legal") {
		    					$("#companyTypes1, label[for^='companyTypes1']").show();
		    					$("#companyTypes1").attr('checked', 'checked');
		    				} else if(companySummary[0] == "Sociedad de Hecho") {
		    					$("#companyTypes2, label[for^='companyTypes2']").show();
		    					$("#companyTypes2").attr('checked', 'checked');
		    				} else if(companySummary[0] == "Sociedad Anónima") {
		    					$("#companyTypes3, label[for^='companyTypes3']").show();
		    					$("#companyTypes3").attr('checked', 'checked');
		    				} else if(companySummary[0] == "Sociedad de Responsabilidad Limitada") {
		    					$("#companyTypes4, label[for^='companyTypes4']").show();
		    					$("#companyTypes4").attr('checked', 'checked');
		    				}
		    						    				
							$.getJSON(
								"./LandJSONAction",
		    					{companyId: $(this).val()}, 
		    			    		function(lands) { 
										var options = '<option value=""></option>';
		    			    		  
		    				    		for (var i = 0; i < lands.length; i++) {
		    				    			options += '<option value="' + lands[i].id + '">' + lands[i].name + '</option>';
		    				    		}
		    			    		      
		    					    	$("#land").html(options);
		    					    	$("select").selectmenu({style:"dropdown", width:400});
		    					}
		    				); 
		    			} else {
		    				$("#quickCompanyEdit, #quickCompanyShow, #quickLandAdd, #quickLandShow, #quickLandEdit").button("disable");
		    				$("#quickCompanyAdd").button("enable");
		    				$("input[type='radio'], label[for^='companyTypes']").show();
		    				$("input[id^='companyTypes']").removeAttr("checked");
		    				$("#companyTypes1").attr('checked', 'checked');
		    			}
					}
				);
		        
				$("#land").change(
						function(event) {
			 	          		    
			 				if($("#land").val() == "") {
			 			    	$("#quickLandEdit, #quickLandShow").button("disable");
			 			    } else {
			 			    	$("#quickLandEdit, #quickLandShow").button("enable");
			 			    } 
						}
					);

				$("#quickSystematizationForm").validate({
					rules: {
						systematizationFile: {
							accept:"kmz|kml"
						},
						descriptionSpanish: {
							maxlength:4096
						},
						descriptionPortuguese: {
							maxlength:4096
						},
						descriptionEnglish: {
							maxlength:4096
						},
						company:"required",
						land:"required"
					},
					messages:{
						systematizationFile: {
							accept:"Por favor, elige un valor aceptado de archivo google earth: kml, kmz."
						}
					},
					submitHandler: function(form) {
						$(form).ajaxSubmit({
							type:"POST",
							success: function(systematizations) {
								
								var options = '<option value=""></option>';
								
								if(actionMethod == "update") {
									var selectedId = $("#systematization").val();
					    		    
					    	      	for (var i = 0; i < systematizations.length; i++) {
					    	      		
					    	      		if(systematizations[i].id == selectedId) {
					    	      			options += '<option value="' + systematizations[i].id + '" selected="selected">' + systematizations[i].summary + '</option>';
					    	      		} else {
					    		    		options += '<option value="' + systematizations[i].id + '">' + systematizations[i].summary + '</option>';
					    	      		}
					    	      		
					    		  	}
									
								} else {
									
									for (var i = 0; i < systematizations.length; i++) {
										options += '<option value="' + systematizations[i].id + '">' + systematizations[i].summary + '</option>';		
									}
									
								}
			    		      
					    	  	$("#systematization").html(options);
								$(".quickSystematizationButton").button("enable");
								$("#quickSystematizationFormDialog").dialog("destroy");
								$("#systematization").selectmenu({style:"dropdown", width:480});
							},
							beforeSerialize: function(form, options) {
								if(actionMethod == "update") {
									$(form).attr("requestId", $("#systematization").val());
								}
							},
							dataType:"json"
						});
					}, invalidHandler: function(form, validator) {
						$("#currentDialog").parent().find('.ui-dialog-buttonpane button:first-child').button("enable").show();
			       	    $("#currentDialog").parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("enable").show();
			       	    $("#currentDialog").parent().find("#quickSystematizationAjaxLoader").replaceWith("");
					}
				});

	  		} 
		});
	</script>