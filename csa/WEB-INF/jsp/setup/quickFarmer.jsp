	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickFarmerAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickFarmerActions">
				<s:div id="quickFarmerAdd" cssClass="quickFarmerButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Productor Agropecuario" />
					</s:div>					
				</s:div>
				<s:div id="quickFarmerShow" cssClass="quickFarmerButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Productor Agropecuario" />
					</s:div>					
				</s:div>
				<s:div id="quickFarmerEdit" cssClass="quickFarmerButton button" >
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Editar Productor Agropecuario" />
					</s:div>					
				</s:div>
			</s:div>
		</td>
	</tr>

	<script type="text/javascript">
		$(
			function() {
				$(".quickFarmerButton").button({});
				
				$("#quickFarmerAdd").click(
					function(event) {
						$("#quickFarmerAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickFarmerButton").button("disable");
						$.get("./QuickFarmerAction_add",	
							function(response) {
								$("#quickFarmerFormDiv").html(response);
								$("#quickFarmerFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:750,
									title:"Productor Agropecuario",
									open: function(event, ui) {
						    	          	  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickFarmerFormDialog").dialog('destroy');
												      
											    	  if($("#agricultorFarmer").val() == "") {
											    	      $("#quickFarmerAdd").button("enable");
											    	  } else {
											    		  $(".quickFarmerButton").button("enable");
											    	  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickFarmerAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickFarmerForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
									    		if($("#agricultorFarmer").val() == "") {
									    			$("#quickFarmerAdd").button("enable");
									    		} else {
									    			$(".quickFarmerButton").button("enable");
									    		}
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickFarmerEdit").click(
					function(event) {
						$("#quickFarmerAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickFarmerButton").button("disable");						
						$.get("./QuickFarmerAction_edit",
							{requestId: $("#agricultorFarmer").val()},
							function(response) {
								$("#quickFarmerFormDiv").html(response);
								
								$("#quickFarmerFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:750,
									title:"Productor Agropecuario",
									open: function(event, ui) {
							    	          $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickFarmerFormDialog").dialog('destroy');
												      $(".quickFarmerButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickFarmerAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() {
												$("#quickFarmerForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickFarmerButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickFarmerShow").click(
						function(event) {
							$("#quickFarmerAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickFarmerButton").button("disable");
							$.get("./QuickFarmerAction_show",
								{requestId: $("#agricultorFarmer").val()},
								function(response) {
									$("#quickFarmerFormDiv").html(response);
									$("#quickFarmerFormDialog").dialog({autoOpen:true, 
										stack:true, 
										zIndex:1, 
										position:"center", 
										draggable:true, 
										resizable:true, 
										modal:true,
										width:750,
										title:"Productor Agropecuario",
										open: function(event, ui) {
							    	              $('.ui-dialog-titlebar-close').bind('click', 
										    	      function(event) {
													      $("#quickFarmerFormDialog").dialog('destroy');
													      $(".quickFarmerButton").button("enable");
										    		  }
										    	  );
												  $(".quickButton").button("enable");
									    	      $("#quickFarmerAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickFarmerButton").button("enable");
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