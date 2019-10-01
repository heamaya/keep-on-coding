	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %> 
	
	<s:form id="quickLandForm" action="%{mappedRequest}" cssClass="ui-dialog-content" enctype="multipart/form-data; charset=UTF-8" method="post">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden key="previousBoundariesFileName" value="%{previousBoundariesFileName}" />
		<s:hidden key="previousLandPath" value="%{previousLandPath}" />
		<s:hidden key="companyId" value="%{companyId}"/>
		
		<s:if test='actionMethod == "create" || actionMethod == "update"'>
		
			<s:textfield id="name" label="Nombre" name="name" required="true" size="40" />
			
			<s:textarea id="description" label="Descripción" name="description" required="false" rows="5" cols="50" />
			
			<s:if test="surfaceArea != null" >
				<s:textfield label="Superficie (has)" name="surfaceArea" readonly="%{readOnly}" value='%{getText("format.number", {surfaceArea})}' size="40" />
			</s:if>
			<s:else>
				<s:textfield label="Superficie (has)" name="surfaceArea" readonly="%{readOnly}" size="40" />
			</s:else>
			
			<s:select id="nearestProvince" list="provinces" label="Provincia" listKey="id" listValue="name" value="nearestProvince.id" name="nearestProvince" emptyOption="true" required="false" />
		
			<jsp:include page="quickNearestProvince.jsp" />
	
			<s:select id="nearestCity" list="cities" label="Ciudad más Cercana" listKey="id" listValue="name" value="nearestCity.id" name="nearestCity" emptyOption="true"/>
		          
			<jsp:include page="quickNearestCity.jsp" />
			
			<s:if test="nearestCityDistance != null" >
				<s:textfield label="Distancia a la ciudad más Cercana (km)" name="nearestCityDistance" readonly="%{readOnly}" value='%{getText("format.number", {nearestCityDistance})}' size="40" />
			</s:if>
			<s:else>
				<s:textfield label="Distancia a la ciudad más Cercana (km)" name="nearestCityDistance" readonly="%{readOnly}" size="40" />
			</s:else>	
 
			<s:if test="boundariesFileName == null || boundariesFileName == ''">
				<s:file id="boundaries" name="boundaries" label="Límites del Campo (KMZ o KML)"/>
			</s:if>
			<s:else>
				<s:checkbox id="keepBoundaries" value="true" name="keepBoundaries" label="Mantener Archivo de Límites del Campo" />
				<s:file id="boundaries" name="boundaries" label="Límites del Campo (KMZ o KML)" cssClass="replace" />
			</s:else>
		</s:if>
		<s:else>
			<s:textfield label="Nombre" value="%{name}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="Empresa Agropecuaria" value="%{company.name}" required="false" readonly="%{readOnly}" size="40" />
			<s:textarea label="Descripción" value="%{description}" required="false" rows="5" cols="50" readonly="%{readOnly}" />
			
			<s:if test="surfaceArea != null" >
				<s:textfield label="Superficie (has)" readonly="%{readOnly}" value='%{getText("format.number", {surfaceArea})}' size="40" />
			</s:if>
			<s:else>
				<s:textfield label="Superficie (has)" value="%{surfaceArea}" readonly="%{readOnly}" size="40" />
			</s:else>
			
			<s:textfield label="Provincia" value="%{nearestProvince.name}" readonly="%{readOnly}" maxlength="100" size="40" />
			<s:textfield label="Ciudad más Cercana" value="%{nearestCity.name}" readonly="%{readOnly}" maxlength="100" size="40" />
			
			<s:if test="nearestCityDistance != null" >
				<s:textfield label="Distancia a la ciudad más Cercana (km)" readonly="%{readOnly}" value='%{getText("format.number", {nearestCityDistance})}' size="40" />
			</s:if>
			<s:else>
				<s:textfield label="Distancia a la ciudad más Cercana (km)" value="%{nearestCityDistance}" readonly="%{readOnly}" size="40" />
			</s:else>			
		</s:else>
	</s:form>
			
	<script type="text/javascript">
				$(function() {
					
					$("#boundaries").fileinput({
		  				buttonText: "Buscar",
		  				inputText: ""
		  			});
					
					$("select").selectmenu({style:"dropdown", width:400});
				          	  
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
						    	  $("select").selectmenu({style:"dropdown", width:400});
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
				});
			</script>
	
	<s:if test='actionMethod == "update"'>
		<script type="text/javascript">
					$(function() {
						$(".replace").attr("disabled", "disabled");
						$("#keepBoundaries").click(
							function(event) {
								if($(this).is(':checked')) {
									$(".replace").attr("disabled", "disabled");	
								} else {
									$(".replace").attr("disabled", "");	
								}
							}
						);	
	
						$("#quickLandForm").validate({
							rules: {
								name: {
									required: true,
									minlength: 1,
									maxlength:512
								},
								boundaries: {
									accept: "kmz|kml"
								},
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
									url: $("#quickLandForm").attr("action"),
									type:"POST",
									success: function(lands) {
										$("#quickLandFormDialog").dialog("close");
										var selectedId = $("#land").val();
						    		    var options = '<option value=""></option>';
							    		  
						    	      	for (var i = 0; i < lands.length; i++) {
						    	      		
						    	      		if(lands[i].id == selectedId) {
						    	      			options += '<option value="' + lands[i].id + '" selected="selected">' + lands[i].name + '</option>';
						    	      		} else {
						    		    		options += '<option value="' + lands[i].id + '">' + lands[i].name + '</option>';
						    	      		}
						    	      		
						    		  	}
					    		      
							    	  	$("#land").html(options);
										$(".quickLandButton").button("enable");
										$("#quickLandFormDialog").dialog("destroy");
										$("#land").selectmenu({style:"dropdown", width:480});
									},
									beforeSerialize: function(form, options) { 
										$(form).attr("requestId", $("#land").val()).attr("companyId", $("#company").val());
									},
									dataType:"json"
								});															
							}
						});
					});
				</script>
			</s:if>
			<s:else>
				<script type="text/javascript">
				
					$(function() {
	
						$("#quickLandForm").validate({
							rules: {
								name: {
									required: true,
									minlength: 1,
									maxlength:512
								},
								boundaries: {
									accept: "kmz|kml"
								},
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
									url: $("#quickLandForm").attr("action"),
									type:"POST",
									success: function(lands) {
						    			var options = '<option value="" selected="selected"></option>';
							    		  
						    	      	for (var i = 0; i < lands.length; i++) {
						    		    	options += '<option value="' + lands[i].id + '">' + lands[i].name + '</option>';
						    		  	}
					    		      
							    	  	$("#land").html(options);	
							    	  	$("#quickLandAdd").button("enable");
							    	  	$("#quickLandFormDialog").dialog("destroy");
							    	  	$("#land").selectmenu({style:"dropdown", width:480});
									},
									dataType:"json"
								});
							}
						});
					});
				</script>			
			</s:else>