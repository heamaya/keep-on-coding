	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s"%>
	
	<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
	
	<div id="contactUs">
		<s:form id="contactUsForm" method="post" enctype="application/x-www-form-urlencoded;charset=utf-8" >
			<s:textfield key="lastName" id="lastName" readonly="%{readOnly}" required="true" maxlength="256" cssClass="message" size="50" />
			<s:textfield key="firstName" id="firstName" name="firstName" readonly="%{readOnly}" required="true" maxlength="256" cssClass="message" size="50" />
			<s:textfield key="email" id="email" required="true" name="email" size="50" maxlength="512" cssClass="message" />
			<s:select key="province" id="province" list="provinces" listKey="id" listValue="name" name="province" emptyOption="true" />
			<s:select key="city" id="city" list="cities" listKey="id" listValue="name" name="city" emptyOption="true" />
			<s:textarea key="message" id="message" required="true" name="message" rows="3" cols="50" cssClass="message" />
		</s:form>
	</div>

	<script type="text/javascript">
		$("#contactUsForm").hide();
	
		$(	
			function() {
				var language = '<s:property value="%{#language}"/>';
				
				if(language == "es") {
					contactUsDialogTitle="Contáctenos";
					sendButtonCaption="Enviar";
					cleanButtonCaption="Limpiar";
					successfulMessage="Mensaje enviado exitosamente. ¡Muchas Gracias!";
	  			} else if(language=="en"){
	  				contactUsDialogTitle="Contact Us";
	  				sendButtonCaption="Send";
	  				cleanButtonCaption="Clean";
	  				successfulMessage="Message successfully sent. Thank you very much!";
	  			} else if(language=="pt"){
					contactUsDialogTitle="Fale Conosco";
					sendButtonCaption="Enviar";
					cleanButtonCaption="Limpar";
					successfulMessage="Mensagem enviada com sucesso. Muito Obrigado!";	  				
	  			}
				
				$("#province").selectmenu({style:'dropdown', width:480});
				$("#city").selectmenu({style:'dropdown', width:480});
				
				$("#contactUs").dialog({
					autoOpen:true, 
		  			width:650,
		  		  	position:"center",
		  		    closeOnEscape:false,
		  			title:contactUsDialogTitle,
		  			resizable:true,
		  			draggable:true,
		  			modal:false,
		  			stack:false,
		  			zIndex:1,
					create: function(event, ui) {
						$('.ui-dialog-titlebar-close').remove();
						$("#contactUsForm").show();
					},
					buttons: [
					{ 
						text:sendButtonCaption,
						click: function() {
							$(this).parent().find('.ui-dialog-buttonpane button:first-child').button("disable").hide();
				           	$(this).parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("disable").hide();
				    	    $(this).parent().find('.ui-dialog-buttonpane').append('<div id="ajaxLoader" class="loader"><img src="' + contextPath + '/icons/ajax-loader.gif" /></div>');
							$("#contactUsForm").submit();
						}
					},
					{
						text: cleanButtonCaption,
						click: function() {
		  					$(".error").empty();
					       	$("#contactUs").parent().find("#messageSent").replaceWith("");
		  					$(".message").val("");
		  						
		  					$("#province option:selected").removeAttr("selected");
		  					$("#province option[value='']").attr("selected", "selected");
		  						
		  					var cityOptions = '<option value="" selected="selected"></option>';
		  					$("#city").html(cityOptions);
		  					$("#province").selectmenu({style:'dropdown', width:480});
		  					$("#city").selectmenu({style:'dropdown', width:480});
						}
					}
					]
				});

          	  	$("#province").change(
    				function(event) {
    					$("#city").html('<option value=""></option>');

   	    	    	  	$.getJSON("/Util/CityJSONAction",
   				    	   	{provinceId: $(this).val()}, 
   				    	   	function(cities) { 
   				    			var options = '<option value=""></option>';
    			    		  
   				    	    	for (var i = 0; i < cities.length; i++) {
   				    		   		options += '<option value="' + cities[i].id + '">' + cities[i].name + '</option>';
   				    			}
    			    		      
   					    		$("#city").html(options);
   					    		$("#city").selectmenu({style:'dropdown', width:480});
   							});	 
   		            	}
  		        	);
          	    
				$("#contactUsForm").validate({
					rules: {
						lastName:{
							required: true,
							minlength: 1,
							maxlength:128							
						},
						firstName:{
							required: true,
							minlength: 1,
							maxlength:128
						},
						email: {
							required: true,
							email: true,
							maxlength:128
						},
						message: {
							required: true,
							minlength: 1,
							maxlength:1024
						}
					},
					submitHandler: function(form) {
						$(form).ajaxSubmit({
							url: "./ContactUsPresentationAction_create",
							type: "POST",
							clearForm:true,
							success: function() {
								
							$("#contactUs").parent().find('.ui-dialog-buttonpane').prepend(
		  						'<div id="messageSent" class="ui-widget">' +
	  							 	'<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">' + 
	  									'<p class="paragraph"><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>' +
											'<strong>' +  
												successfulMessage + 
											'</strong></p>' +
	  								'</div>' +
	  							'</div>'
	  						);
  								
							$("#contactUs").parent().find('.ui-dialog-buttonpane button:first-child').button("enable").show();
						    $("#contactUs").parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("enable").show();
						    $("#contactUs").parent().find("#ajaxLoader").replaceWith("");
						    
							$("#province option:selected").removeAttr("selected");
		  					$("#province option[value='']").attr("selected", "selected");
		  						
		  					var cityOptions = '<option value="" selected="selected"></option>';
		  					$("#city").html(cityOptions);
		  					$("#province").selectmenu({style:'dropdown', width:480});
		  					$("#city").selectmenu({style:'dropdown', width:480});
						}
					});
				}, invalidHandler: function(form, validator) {
					$("#contactUs").parent().find('.ui-dialog-buttonpane button:first-child').button("enable").show();
			     	$("#contactUs").parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("enable").show();
			       	$("#contactUs").parent().find("#ajaxLoader").replaceWith("");
			       	$("#contactUs").parent().find("#messageSent").replaceWith("");
				}
			});
		}	
	);	
	</script>
