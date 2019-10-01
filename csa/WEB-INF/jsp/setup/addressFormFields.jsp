	<%@ taglib uri="/struts-tags" prefix="s" %>
	
	<s:if test='actionMethod == "create" || actionMethod == "update"'>
		<s:select id="province" list="provinces" label="Provincia" listKey="id" listValue="name" value="province.id" name="province" emptyOption="true" required="true" />
		
		<jsp:include page="quickProvince.jsp" />
	
		<s:select id="city" list="cities" label="Ciudad" listKey="id" listValue="name" value="city.id" name="city" emptyOption="true" />
		          
		<jsp:include page="quickCity.jsp" />          
	</s:if>
	<s:elseif test='actionMethod == "find"'>		          
		
		<s:select id="province" list="provinces" label="Provincia"
				  listKey="id" listValue="name" name="province" emptyOption="true" /> 
	
		<s:select id="city" list="cities" label="Ciudad"
				  listKey="id" listValue="name" name="city" emptyOption="true" />
		
	</s:elseif>
	<s:else>
		<s:textfield label="Provincia" value="%{province.name}" readonly="%{readOnly}" maxlength="50" size="40"/>
		<s:textfield label="Ciudad" value="%{city.name}" readonly="%{readOnly}" maxlength="50" size="40" />
	</s:else>
	
	<s:textfield label="Calle" name="street" readonly="%{readOnly}" maxlength="512" size="40" />
	<s:textfield label="Número" name="streetNumber" readonly="%{readOnly}" maxlength="10" size="40" />
	<s:textfield label="Piso" name="floor" readonly="%{readOnly}" maxlength="3" size="40" />
	<s:textfield label="Departamento" name="department" readonly="%{readOnly}" maxlength="255" size="40" />

	<script type="text/javascript">
			
		    $(function() {
		    	
	    		if($("#province").val() == "") {
	    			$("#quickCityAdd, #quickCityShow, #quickCityEdit, #quickProvinceEdit, #quickProvinceShow").button("disable");
	    			$("#quickProvinceAdd").button("enable");
	    		} else {
	    			$("#quickProvinceAdd, #quickProvinceEdit, #quickProvinceShow, #quickCityAdd").button("enable");
    			  			    			
		 			if($("#city").val() == "") {
						$("#quickCityEdit, #quickCityShow").button("disable");
						$("#quickCityAdd").button("enable");
					} else {
						$("#quickCityAdd, #quickCityEdit, #quickCityShow").button("enable");
					} 
				}
	    	
          	    $("#province").change(
					function(event) {
						$("#city").html('<option value=""></option>');
	          		    
						if($("#province").val() == "") {
			    			$("#quickProvinceEdit, #quickProvinceShow, #quickCityAdd, #quickCityShow, #quickCityEdit").button("disable");
			    		} else {
			    			$("#quickProvinceEdit, #quickProvinceShow, #quickCityAdd").button("enable");
			    		}
	          		  
	    	    	  	$.getJSON(
				           "./CityJSONAction",
				    	   {provinceId: $(this).val()}, 
				    	   function(cities) { 
				    		  var options = '<option value=""></option>';
			    		  
				    	      for (var i = 0; i < cities.length; i++) {
				    		     options += '<option value="' + cities[i].id + '">' + cities[i].name + '</option>';
				    		  }
			    		      
					    	  $("#city").html(options);		
					    	  $("#city").selectmenu({style:'dropdown', width:400});
						});	 
		             }
		          );
	          	  
	          	  $("#city").change(
	 	           	     function(event) {
	 	          		    
	 			    		if($("#city").val() == "") {
	 			    			$("#quickCityEdit, #quickCityShow").button("disable");
	 			    		} else {
	 			    			$("#quickCityEdit, #quickCityShow").button("enable");
	 			    		} 
	 		             }
	 		      );
		    });
	</script>
 