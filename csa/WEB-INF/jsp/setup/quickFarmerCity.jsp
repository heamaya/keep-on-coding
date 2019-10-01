	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickFarmerCityAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickFarmerCityActions">
				<s:div id="quickFarmerCityAdd" cssClass="quickFarmerCityButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Ciudad" />
					</s:div>					
				</s:div>
				<s:div id="quickFarmerCityShow" cssClass="quickFarmerCityButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Ciudad" />
					</s:div>					
				</s:div>
				<s:div id="quickFarmerCityEdit" cssClass="quickFarmerCityButton button" >
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
				$(".quickFarmerCityButton").button({});
				
				$("#quickFarmerCityAdd").click(
					function(event) {
						$("#quickFarmerCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickFarmerCityButton").button("disable");
						$.get("./QuickFarmerCityAction_add",
							{provinceId: $("#farmerProvince").val()},	
							function(response) {
								$("#quickFarmerCityFormDiv").html(response);
								
								$("#quickFarmerCityFormDialog").dialog({autoOpen:true, 
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
												      $("#quickFarmerCityFormDialog").dialog('destroy');
												      
													  if($("#farmerCity").val() == "") {
													      $("#quickFarmerCityAdd").button("enable");
													  } else {
													      $("#quickFarmerCityAdd, #quickFarmerCityEdit, #quickFarmerCityShow").button("enable");
													  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickFarmerCityAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickFarmerCityForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
											    if($("#farmerCity").val() == "") {
											    	$("#quickFarmerCityAdd").button("enable");
											    } else {
											    	$("#quickFarmerCityAdd, #quickFarmerCityEdit, #quickFarmerCityShow").button("enable");
											    }
												
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickFarmerCityEdit").click(
					function(event) {
						$("#quickFarmerCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickFarmerCityButton").button("disable");
						$.get("./QuickFarmerCityAction_edit",
							{requestId: $("#farmerCity").val(), provinceId: $("#farmerProvince").val()},
							function(response) {
								$("#quickFarmerCityFormDiv").html(response);
								$("#quickFarmerCityFormDialog").dialog({autoOpen:true, 
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
												      $("#quickFarmerCityFormDialog").dialog('destroy');
												      $(".quickFarmerCityButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickFarmerCityAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() { 
												$("#quickFarmerCityForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickFarmerCityButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickFarmerCityShow").click(
						function(event) {
							$("#quickFarmerCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickFarmerCityButton").button("disable");
							$.get("./QuickFarmerCityAction_show",
								{requestId: $("#farmerCity").val(), provinceId: $("#farmerProvince").val()},
								function(response) {
									$("#quickFarmerCityFormDiv").html(response);
									$("#quickFarmerCityFormDialog").dialog({autoOpen:true, 
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
													      $("#quickFarmerCityFormDialog").dialog('destroy');
													      $(".quickFarmerCityButton").button("enable");
										    		  }
										    	  );
									    	      $("#quickFarmerCityAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickFarmerCityButton").button("enable");
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