	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickCityAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickCityActions">
				<s:div id="quickCityAdd" cssClass="quickCityButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Ciudad" />
					</s:div>					
				</s:div>
				<s:div id="quickCityShow" cssClass="quickCityButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Ciudad" />
					</s:div>					
				</s:div>
				<s:div id="quickCityEdit" cssClass="quickCityButton button" >
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Editar Ciudad" />
					</s:div>					
				</s:div>
				<!--  
				<s:div id="quickCityRemove" cssClass="quickCityButton">
					<s:url id="iconUrl" value="/icons/remove.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title='<s:text name="quickCityRemove"/>' />
					</s:div>					
				</s:div>
				-->
			</s:div>
		</td>
	</tr>

	<script type="text/javascript">
		$(
			function() {
				$(".quickCityButton").button({});
				
				$("#quickCityAdd").click(
					function(event) {
						$("#quickCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickCityButton").button("disable");
						$.get("./QuickCityAction_add",
							{provinceId: $("#province").val()},	
							function(response) {
								$("#quickCityFormDiv").html(response);
								
								$("#quickCityFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:550,
									title:"Ciudad",
									open: function(event, ui) {
						    	          	  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickCityFormDialog").dialog('destroy');
												      
													  if($("#city").val() == "") {
													      $("#quickCityAdd").button("enable");
													  } else {
													      $("#quickCityAdd, #quickCityEdit, #quickCityShow").button("enable");
													  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickCityAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickCityForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
											    if($("#city").val() == "") {
											    	$("#quickCityAdd").button("enable");
											    } else {
											    	$("#quickCityAdd, #quickCityEdit, #quickCityShow").button("enable");
											    }
												
											}
										}
									}
								);
								
								
								
							},"html"
						);
	
					}
				);
				
				$("#quickCityEdit").click(
					function(event) {
						$("#quickCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickCityButton").button("disable");
						$.get("./QuickCityAction_edit",
							{requestId: $("#city").val(), provinceId: $("#province").val()},
							function(response) {
								$("#quickCityFormDiv").html(response);
								$("#quickCityFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:500,
									title:"Ciudad",
									open: function(event, ui) {
							    	          $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickCityFormDialog").dialog('destroy');
												      $(".quickCityButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickCityAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() { 
												$("#quickCityForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickCityButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickCityShow").click(
						function(event) {
							$("#quickCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickCityButton").button("disable");
							$.get("./QuickCityAction_show",
								{requestId: $("#city").val(), provinceId: $("#province").val()},
								function(response) {
									$("#quickCityFormDiv").html(response);
									$("#quickCityFormDialog").dialog({autoOpen:true, 
										stack:true, 
										zIndex:1, 
										position:"center", 
										draggable:true, 
										resizable:true, 
										modal:true,
										width:500,
										title:"Ciudad",
										open: function(event, ui) {
							    	              $('.ui-dialog-titlebar-close').bind('click', 
										    	      function(event) {
													      $("#quickCityFormDialog").dialog('destroy');
													      $(".quickCityButton").button("enable");
										    		  }
										    	  );
									    	      $("#quickCityAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickCityButton").button("enable");
												}
										}
									});		
									
								}
							);
						}
					);
				/*
				$("#quickCityRemove").click(
					function(event) {
						$("#quickCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickCityButton").button("disable");
						$.get("./QuickCityAction_remove",
							{requestId: $("#city").val(), provinceId: $("#province").val()},
							function(response) {
								$("#quickCityFormDiv").html(response);
								$("#quickCityFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:500,
									title:"City",
									open: function(event, ui) {
					    	                  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickCityFormDialog").dialog('destroy');
									    		  }
									    	  );
								    	      $("#quickCityAjaxLoader").empty();
										  }, 
									buttons: {
										"Delete": 
											function() { 
												$.post("./QuickCityAction_delete",
														$("#quickCityForm").attr("requestId", $("#city").val())
									                    .attr("countryId", $("#countriesSelect").val())
									                    .serialize(),
													function(cities) {
										    			var options = '<option value=""></option>';
										    		  
										    	      	for (var i = 0; i < cities.length; i++) {
										    		    	options += '<option value="' + cities[i].id + '">' + cities[i].name + '</option>';
										    		  	}
									    		      
											    	  	$("#city").html(options);		
													},
													"json"
												);
												
												$(this).dialog("destroy"); 
												$("#quickCityAdd").button("enable");
											},
										"Cancel":
											function() {
												$(this).dialog("destroy");
												$("#quickCityAdd, #quickCityEdit, #quickCityShow").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);*/
			}
		);
	</script>