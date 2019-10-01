<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>

	<s:form id="quickFarmerForm" action="%{mappedRequest}" cssClass="ui-dialog-content" >
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
	
		<s:if test='actionMethod == "create" || actionMethod == "update"'>				
			<s:textfield label="Apellido" name="lastName" readonly="%{readOnly}" required="true" maxlength="50" size="40" />
			<s:textfield label="Nombre" name="firstName" readonly="%{readOnly}" required="true" maxlength="50" size="40" />
			
			<s:select id="farmerProvince" list="provinces" label="Provincia" listKey="id" listValue="name" value="province.id" name="province" emptyOption="true" required="true" />
		
			<jsp:include page="quickFarmerProvince.jsp" />
	
			<s:select id="farmerCity" list="cities" label="Ciudad" listKey="id" listValue="name" value="city.id" name="city" emptyOption="true"/>
		          
			<jsp:include page="quickFarmerCity.jsp" />
		</s:if>
		<s:else>
			<s:textfield label="Apellido" name="lastName" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
			<s:textfield label="Nombre" name="firstName" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
			<s:textfield label="Provincia" value="%{province.name}" readonly="%{readOnly}" maxlength="100" size="40" />
			<s:textfield label="Ciudad" value="%{city.name}" readonly="%{readOnly}" maxlength="100" size="40" />
		</s:else>
	
		<s:textfield label="Teléfono Primario" name="primaryTelephoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Secundario" name="secondaryTelephoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Celular Primario" name="primaryCellPhoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Teléfono Celular Secundario" name="secondaryCellPhoneNumber" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Correo Electrónico Primario" name="email" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textfield label="Correo Electrónico Secundario" name="secondaryEmail" readonly="%{readOnly}" required="false" maxlength="50" size="40" />
		<s:textarea label="Comentario" name="comment" readonly="%{readOnly}" required="false" rows="5" cols="40" />
	
	
		<script type="text/javascript">
			
			$(function() {
				var actionMethod = $("#actionMethod").val();
				
				if(actionMethod == "create" || actionMethod == "update") {
				
	    			if($("#farmerProvince").val() == "") {
	    				$("#quickFarmerCityAdd, #quickFarmerCityShow, #quickFarmerCityEdit, #quickFarmerProvinceEdit, #quickFarmerProvinceShow").button("disable");
	    				$("#quickFarmerProvinceAdd").button("enable");
	    			} else {
		    			$("#quickFarmerProvinceAdd, #quickFarmerProvinceEdit, #quickFarmerProvinceShow, #quickFarmerCityAdd").button("enable");
    			  			    			
			 			if($("#farmerCity").val() == "") {
							$("#quickFarmerCityEdit, #quickFarmerCityShow").button("disable");
							$("#quickFarmerCityAdd").button("enable");
						} else {
							$("#quickFarmerCityAdd, #quickFarmerCityEdit, #quickFarmerCityShow").button("enable");
						} 
					}
	    			
	    			$("select").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});
	    	
          	    	$("#farmerProvince").change(
						function(event) {
							$("#farmerCity").html('<option value=""></option>');
	          		    
							if($("#farmerProvince").val() == "") {
				    			$("#quickFarmerProvinceEdit, #quickFarmerProvinceShow, #quickFarmerCityAdd, #quickFarmerCityShow, #quickFarmerCityEdit").button("disable");
			    			} else {
			    				$("#quickFarmerProvinceEdit, #quickFarmerProvinceShow, #quickFarmerCityAdd").button("enable");
				    		}
	          		  
		    	    	  	$.getJSON(
					           "./CityJSONAction",
					    	   {provinceId: $(this).val()}, 
					    	   function(cities) { 
				    			  var options = '<option value=""></option>';
			    		  
					    	  	  for (var i = 0; i < cities.length; i++) {
				    		          options += '<option value="' + cities[i].id + '">' + cities[i].name + '</option>';
				    		  	  }
			    		      
					    	  	  $("#farmerCity").html(options);				    	    	  
					    	  	  $("#farmerCity").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});
							});	 
		             	}
		          	);
	          	  
	          	  	$("#farmerCity").change(
	 	           	     function(event) {
	 	          		    
	 			    		if($("#farmerCity").val() == "") {
	 			    			$("#quickFarmerCityEdit, #quickFarmerCityShow").button("disable");
	 			    		} else {
	 			    			$("#quickFarmerCityEdit, #quickFarmerCityShow").button("enable");
	 			    		} 
	 		             }
	 		      	);
				}

				if(actionMethod == "create") {
				
					$("#quickFarmerForm").validate({
						rules: {
							lastName: {
								required: true,
								minlength: 1,
								maxlength:50
							},
							firstName: {
								required: true,
								minlength: 1,
								maxlength:50
							},
							province: {
								required: true							
							},
							city: {
								required: true							
							},
							primaryTelephoneNumber: {
								digits:true,
								maxlength:50							
							},
							secondaryTelephoneNumber: {
								digits:true,
								maxlength:50							
							},
							primaryCellPhoneNumber: {
								digits:true,
								maxlength:50							
							},
							secondaryCellPhoneNumber: {
								digits:true,
								maxlength:50							
							},
							email: {
								email: true,
								minlength: 1,
								maxlength:50
							},
							secondaryEmail: {
								email: true,
								minlength: 1,
								maxlength:50
							},
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
							},
							comment: {
								maxlength:512							
							}
						},
						submitHandler: function(form) {
							$(form).ajaxSubmit({
								url: $(form).attr("action"),
								type:"POST",
								success: function(agricultorFarmers) {

					    			var options = '<option value="" selected="selected"></option>';
					    			
					    			if($("#ownersSelect").length != 0) {
					    				
										$("#ownersSelect option").each(
									    	function(index, element) {
									    			
									    		for(var i = 0; i < agricultorFarmers.length; i++) {
						    							
									    			if(agricultorFarmers[i] != null && element.value == agricultorFarmers[i].id) {
						    							agricultorFarmers[i] = null;
						    						}
									    				
									    		}
									    	}
									    );
	
					    	      		for (var k = 0; k < agricultorFarmers.length; k++) {
					    	      			
					    	      			if(agricultorFarmers[k] != null) {
					    		    			options += '<option value="' + agricultorFarmers[k].id + '">' + agricultorFarmers[k].summary + '</option>';
					    	      			}
					    	      			
					    		  		}
					    				
					    			} else {
						    		  
					    	      		for (var i = 0; i < agricultorFarmers.length; i++) {
					    		    		options += '<option value="' + agricultorFarmers[i].id + '">' + agricultorFarmers[i].summary + '</option>';
					    		  		}
					    			}
				    		      
						    	  	$("#agricultorFarmer").html(options);	
						    	  	$("#quickFarmerAdd").button("enable");
						    	  	$("#quickFarmerFormDialog").dialog("destroy");
						    	  	$("#agricultorFarmer").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});
								},
								dataType:"json"
							});
						}
					});
				} else if(actionMethod == "update") {
					$("#quickFarmerForm").validate({
						rules: {
							lastName: {
								required: true,
								minlength: 1,
								maxlength:50
							},
							firstName: {
								required: true,
								minlength: 1,
								maxlength:50
							},
							province: {
								required: true							
							},
							city: {
								required: true							
							},
							primaryTelephoneNumber: {
								digits:true,
								maxlength:50							
							},
							secondaryTelephoneNumber: {
								digits:true,
								maxlength:50							
							},
							primaryCellPhoneNumber: {
								digits:true,
								maxlength:50							
							},
							secondaryCellPhoneNumber: {
								digits:true,
								maxlength:50							
							},
							email: {
								email: true,
								minlength: 1,
								maxlength:50
							},
							secondaryEmail: {
								email: true,
								minlength: 1,
								maxlength:50
							},
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
							},
							comment: {
								maxlength:512							
							}
						},
						submitHandler: function(form) {
							$(form).ajaxSubmit({
								type:"POST",
								success: function(agricultorFarmers) {
									$("#quickFarmerFormDialog").dialog("close");
									var selectedId = $("#agricultorFarmer").val();
					    		    var options = '<option value=""></option>';
					    		    
					    			if($("#ownersSelect").length != 0) {
					    				
										$("#ownersSelect option").each(
									    	function(index, element) {
									    			
									    		for(var i = 0; i < agricultorFarmers.length; i++) {
						    							
									    			if(agricultorFarmers[i] != null && element.value == agricultorFarmers[i].id) {
						    							agricultorFarmers[i] = null;
						    						}
									    				
									    		}
									    	}
									    );
	
						    	      	for (var i = 0; i < agricultorFarmers.length; i++) {

							    	      	if(agricultorFarmers[i] != null) {
						    	      		
							    	      		if(agricultorFarmers[i].id == selectedId) {
							    	      			options += '<option value="' + agricultorFarmers[i].id + '" selected="selected">' + agricultorFarmers[i].summary + '</option>';
							    	      		} else {
						    		    			options += '<option value="' + agricultorFarmers[i].id + '">' + agricultorFarmers[i].summary + '</option>';
						    	      			}
						    	      		
						    		  		}
						    	    	}
					    				
					    			} else {
						    		  
						    	      	for (var i = 0; i < agricultorFarmers.length; i++) {
						    	      		
						    	      		if(agricultorFarmers[i].id == selectedId) {
						    	      			options += '<option value="' + agricultorFarmers[i].id + '" selected="selected">' + agricultorFarmers[i].summary + '</option>';
						    	      		} else {
						    		    		options += '<option value="' + agricultorFarmers[i].id + '">' + agricultorFarmers[i].summary + '</option>';
						    	      		}
						    	      		
						    		  	}
					    			}

						    	  	$("#agricultorFarmer").html(options);
									$(".quickFarmerButton").button("enable");
									$("#quickFarmerFormDialog").dialog("destroy");
									$("#agricultorFarmer").not("[multiple='multiple']").selectmenu({style:"dropdown", width:400});
								},
								beforeSerialize: function(form, options) { 
									$(form).attr("requestId", $("#agricultorFarmer").val());
								},
								dataType:"json"
							});
						}
					});
				}
			});
		</script>
	</s:form>