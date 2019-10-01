	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickSystematizationAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickSystematizationActions">
				<s:div id="quickSystematizationAdd" cssClass="quickSystematizationButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Sistematización" />
					</s:div>					
				</s:div>
				<s:div id="quickSystematizationShow" cssClass="quickSystematizationButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Sistematización" />
					</s:div>					
				</s:div>
				<s:div id="quickSystematizationEdit" cssClass="quickSystematizationButton button" >
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Editar Sistematización" />
					</s:div>					
				</s:div>
			</s:div>
		</td>
	</tr>

	<script type="text/javascript">
		$(
			function() {
				$(".quickSystematizationButton").button({});
				
				$("#quickSystematizationAdd").click(
					function(event) {
						$("#quickSystematizationAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickSystematizationButton").button("disable");
						$.get("./QuickSystematizationAction_add",	
							function(response) {
								$("#quickSystematizationFormDiv").html(response);
								$("#quickSystematizationFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:800,
									title:"Sistematización",
									open: function(event, ui) {
						    	          	  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickSystematizationFormDialog").dialog('destroy');
												      
											    	  if($("#systematization").val() == "") {
											    	      $("#quickSystematizationAdd").button("enable");
											    	  } else {
											    		  $(".quickSystematizationButton").button("enable");
											    	  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickSystematizationAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickSystematizationForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
									    		if($("#systematization").val() == "") {
									    			$("#quickSystematizationAdd").button("enable");
									    		} else {
									    			$(".quickSystematizationButton").button("enable");
									    		}
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickSystematizationEdit").click(
					function(event) {
						$("#quickSystematizationAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickSystematizationButton").button("disable");						
						$.get("./QuickSystematizationAction_edit",
							{requestId: $("#systematization").val()},
							function(response) {
								$("#quickSystematizationFormDiv").html(response);
								
								$("#quickSystematizationFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:800,
									title:"Sistematización",
									open: function(event, ui) {
							    	          $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickSystematizationFormDialog").dialog('destroy');
												      $(".quickSystematizationButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickSystematizationAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() {
												$("#quickSystematizationForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickSystematizationButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickSystematizationShow").click(
						function(event) {
							$("#quickSystematizationAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickSystematizationButton").button("disable");
							$.get("./QuickSystematizationAction_show",
								{requestId: $("#systematization").val()},
								function(response) {
									$("#quickSystematizationFormDiv").html(response);
									$("#quickSystematizationFormDialog").dialog({autoOpen:true, 
										stack:true, 
										zIndex:1, 
										position:"center", 
										draggable:true, 
										resizable:true, 
										modal:true,
										width:800,
										title:"Sistematización",
										open: function(event, ui) {
							    	              $('.ui-dialog-titlebar-close').bind('click', 
										    	      function(event) {
													      $("#quickSystematizationFormDialog").dialog('destroy');
													      $(".quickSystematizationButton").button("enable");
										    		  }
										    	  );
												  $(".quickButton").button("enable");
									    	      $("#quickSystematizationAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickSystematizationButton").button("enable");
												}
										}
									});		
									
								}
							);
						}
					);
			}
		);
	</script>