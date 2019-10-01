	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8" method="post">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden key="previousBoundariesFileName" value="%{previousBoundariesFileName}" />
		<s:hidden key="previousLandPath" value="%{previousLandPath}" />
		 	
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>
			<s:textfield id="name" label="Nombre" name="name" required="true" size="40" />
			<s:select id="company" label="Empresa Agropecuaria" list="companies" name="company" listKey="id" listValue="summary" emptyOption="true" required="true" value="company.id" required="true" />
			<s:radio id="companyTypes" list="companyTypes" label="Tipo de Empresa" listKey="id" listValue="value" name="companyTypeToAdd" />
			
			<jsp:include page="quickCompany.jsp" />
			
			<s:textarea id="description" label="Descripción" name="description" required="false" rows="8" cols="40" />
			
			<s:if test="surfaceArea != null" >
				<s:textfield label="Superficie (has)" name="surfaceArea" readonly="%{readOnly}" value='%{getText("format.number", {surfaceArea})}' size="40"  />
			</s:if>
			<s:else>
				<s:textfield label="Superficie (has)" name="surfaceArea" readonly="%{readOnly}" size="40"  />
			</s:else>
			
			<s:select id="nearestProvince" list="provinces" label="Provincia" listKey="id" listValue="name" value="nearestProvince.id" name="nearestProvince" emptyOption="true" required="false" />
		
			<jsp:include page="quickNearestProvince.jsp" />
	
			<s:select id="nearestCity" list="cities" label="Ciudad más Cercana" listKey="id" listValue="name" value="nearestCity.id" name="nearestCity" emptyOption="true"/>
		          
			<jsp:include page="quickNearestCity.jsp" />
			
			<s:if test="nearestCityDistance != null" >
				<s:textfield label="Distancia a la ciudad más Cercana (km)" name="nearestCityDistance" readonly="%{readOnly}" value='%{getText("format.number", {nearestCityDistance})}' size="40"  />
			</s:if>
			<s:else>
				<s:textfield label="Distancia a la ciudad más Cercana (km)" name="nearestCityDistance" readonly="%{readOnly}" size="40"  />
			</s:else>	
 
			<s:if test="boundariesFileName == null || boundariesFileName == ''">
				<s:file id="boundaries" name="boundaries" label="Límites del Campo (KMZ o KML)"/>
			</s:if>
			<s:else>
				<s:checkbox id="keepBoundaries" value="true" name="keepBoundaries" label="Mantener Archivo de Límites del Campo"/>
				<s:file id="boundaries" name="boundaries" label="Límites del Campo (KMZ o KML)" />
			</s:else>
		</s:if>
		<s:elseif test='actionMethod == "find"'>
			<s:select label="Modo de Filtro de Texto" list="matchModes" name="matchMode" emptyOption="false" listKey="id" listValue="value" />
			<s:textfield id="name" label="Nombre" name="name" required="false" size="40" />
			<s:select id="company" label="Empresa Agropecuaria" list="companies" name="company" listKey="id" listValue="summary" emptyOption="true" required="false" />
			<s:textarea id="description" label="Descripción" name="description" required="false" rows="8" cols="40" />
			<s:textfield label="Superficie (has)" name="surfaceArea" readonly="%{readOnly}" size="40"  />
			<s:select id="nearestProvince" list="provinces" label="Provincia" listKey="id" listValue="name" name="nearestProvince" emptyOption="true" />
			<s:select id="nearestCity" list="cities" label="Ciudad más Cercana" listKey="id" listValue="name" name="nearestCity" emptyOption="true"/>
		    <s:textfield label="Distancia a la ciudad más Cercana (km)" name="nearestCityDistance" readonly="%{readOnly}" size="40"  />
		</s:elseif>
		<s:else>		
			<s:textfield label="Nombre" value="%{name}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="Empresa Agropecuaria" value="%{company.name}" required="false" readonly="%{readOnly}" size="40"  />
			<s:textarea label="Descripción" value="%{description}" required="false" rows="8" cols="40" readonly="%{readOnly}" />
			
			<s:if test="surfaceArea != null" >
				<s:textfield label="Superficie (has)" readonly="%{readOnly}" value='%{getText("format.number", {surfaceArea})}' size="40"  />
			</s:if>
			<s:else>
				<s:textfield label="Superficie (has)" value="%{surfaceArea}" readonly="%{readOnly}" size="40"  />
			</s:else>
			
			<s:textfield label="Provincia" value="%{nearestProvince.name}" readonly="%{readOnly}" maxlength="100" size="40"  />
			<s:textfield label="Ciudad más Cercana" value="%{nearestCity.name}" readonly="%{readOnly}" maxlength="100" size="40"  />
			
			<s:if test="nearestCityDistance != null" >
				<s:textfield label="Distancia a la ciudad más Cercana (km)" readonly="%{readOnly}" value='%{getText("format.number", {nearestCityDistance})}' size="40"  />
			</s:if>
			<s:else>
				<s:textfield label="Distancia a la ciudad más Cercana (km)" value="%{nearestCityDistance}" readonly="%{readOnly}" size="40" />
			</s:else>			
		</s:else>
	</s:form>

	<jsp:include page="formSubmit.jsp" />

	<script type="text/javascript">
			
		    $(function() {
		    	
		    	if($("#company").val() == "") {
	    			$("#quickCompanyEdit, #quickCompanyShow").button("disable");
	    			$("#quickCompanyAdd").button("enable");
	    		} else {
	    			$("#quickCompanyAdd, #quickCompanyEdit, #quickCompanyShow").button("enable");
				}
	    	
	    		if($("#nearestProvince").val() == "") {
	    			$("#quickNearestCityAdd, #quickNearestCityShow, #quickNearestCityEdit, #quickNearestProvinceEdit, #quickNearestProvinceShow").button("disable");
	    			$("#quickNearestProvinceAdd").button("enable");
	    		} else {
	    			$("#quickNearestProvinceAdd, #quickNearestProvinceEdit, #quickNearestProvinceShow, #quickNearestCityAdd").button("enable");
    			  			    			
		 			if($("#nearestCity").val() == "") {
						$("#quickNearestCityEdit, #quickNearestCityShow").button("disable");
						$("#quickNearestCityAdd").button("enable");
					} else {
						$("#quickNearestCityAdd, #quickNearestCityEdit, #quickNearestCityShow").button("enable");
					} 
				}
	    	
          	    $("#nearestProvince").change(
					function(event) {
						$("#nearestCity").html('<option value=""></option>');
	          		    
						if($("#nearestProvince").val() == "") {
			    			$("#quickNearestProvinceEdit, #quickNearestProvinceShow, #quickNearestCityAdd, #quickNearestCityShow, #quickNearestCityEdit").button("disable");
			    		} else {
			    			$("#quickNearestProvinceEdit, #quickNearestProvinceShow, #quickNearestCityAdd").button("enable");
			    		}
	          		  
	    	    	  	$.getJSON(
				           "./CityJSONAction",
				    	   {provinceId: $(this).val()}, 
				    	   function(cities) { 
				    		  var options = '<option value=""></option>';
			    		  
				    	      for (var i = 0; i < cities.length; i++) {
				    		     options += '<option value="' + cities[i].id + '">' + cities[i].name + '</option>';
				    		  }
			    		      
					    	  $("#nearestCity").html(options);
					    	  $("#nearestCity").selectmenu({style:"dropdown", width:400});
						});	 
		             }
		          );
	          	  
	          	  $("#nearestCity").change(
	 	           	     function(event) {
	 	          		    
	 			    		if($("#nearestCity").val() == "") {
	 			    			$("#quickNearestCityEdit, #quickNearestCityShow").button("disable");
	 			    		} else {
	 			    			$("#quickNearestCityEdit, #quickNearestCityShow").button("enable");
	 			    		} 
	 		             }
	 		      );
	          	  
			    	var actionMethod = $("#actionMethod").val();
			    	
			    	if(actionMethod == "create" || actionMethod == "update") {
			    		
			  			$("#boundaries").fileinput({
			  				buttonText: "Buscar",
			  				inputText: ""
			  			});
			  			
						$("label[for^='companyTypes']").css(
							{
								fontFamily: "segoe ui, Arial, sans-serif",
								fontSize: "xx-small"
							}
						);
						
						$("#companyTypes1").attr('checked', 'checked');
			  			
			  			$("select").selectmenu({style:"dropdown", width:400});
			  			
			  			if(actionMethod == "update") {
				  			
							$("#boundaries").attr("disabled", "disabled");
							$("#keepBoundaries").click(
								function(event) {
							
									if($(this).is(':checked')) {
										$("#boundaries").attr("disabled", "disabled");
									} else {
										$("#boundaries").removeAttr("disabled");
									}	
								}
							);	
			  			}
			  			
			  			$("#company").change(
					    	function(event) {
					    		$("#land").html('<option value=""></option>');
					    	          		   
					    			if($("#company").val() != "") {
					    				
					    				$("#quickCompanyEdit, #quickCompanyShow, #quickLandAdd").button("enable");
					    				$("#quickCompanyAdd").button("disable");
					    				
					    				
					    				$("input[type='radio'], label[for^='companyTypes']").not("label[for='companyTypes']").hide();
					    				var companySummary = new Array(); 
					    				companySummary = $("#company option:selected").text().split(",");
					    				$("input[id^='companyTypes']").removeAttr("checked");
					    				
					    				if(companySummary[0] == "Persona Legal") {
					    					$("#companyTypes1, label[for='companyTypes1']").show();
					    					$("#companyTypes1").attr('checked', 'checked');
					    				} else if(companySummary[0] == "Sociedad de Hecho") {
					    					$("#companyTypes2, label[for='companyTypes2']").show();
					    					$("#companyTypes2").attr('checked', 'checked');
					    				} else if(companySummary[0] == "Sociedad Anónima") {
					    					$("#companyTypes3, label[for='companyTypes3']").show();
					    					$("#companyTypes3").attr('checked', 'checked');
					    				} else if(companySummary[0] == "Sociedad de Responsabilidad Limitada") {
					    					$("#companyTypes4, label[for='companyTypes4']").show();
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

						$("#currentForm").validate({
							rules: {
								name: {
									required: true,
									minlength: 1,
									maxlength:512
								},
								boundaries: {
									accept: "kmz|kml"
								},
								company:"required",
								description: {
									minlength:0,
									maxlength:1024
								},
								surfaceArea: {
									number: true,
									range: [1, 50000]
								},
								nearestCityDistance: {
									number: true,
									range: [0.01, 500]
								}
							},
							messages: {
								boundaries: {
									accept: "Por favor, elige un valor aceptado de archivo google earth: kml, kmz."
								}
							},
							submitHandler: function(form) {
								$(form).ajaxSubmit({
									url: $("#currentForm").attr("action"),
									type:"POST",
									target:$("#result"),
									success: function() {
										$("#currentDialog").dialog("close");
									}
								});
							}, invalidHandler: function(form, validator) {
								$("#currentDialog").parent().find('.ui-dialog-buttonpane button:first-child').button("enable").show();
				        	    $("#currentDialog").parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("enable").show();
				        	    $("#currentDialog").parent().find("#ajaxLoader").replaceWith("");
							}
						});
			    	} else if(actionMethod == "delete" || actionMethod == "find") {
			    		
			    		if(actionMethod == "find") {
			    			$("select").selectmenu({style:"dropdown", width:400});
			    		}
			    		
			    		$("#currentForm").ajaxForm({
							url: $("#currentForm").attr("action"),
							type:"POST",
							target:$("#result"),
							success: function() {
								$("#currentDialog").dialog("close");
							} 
						});
			    	}

		    });
	</script>