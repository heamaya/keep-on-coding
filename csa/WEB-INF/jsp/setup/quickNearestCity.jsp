	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickNearestCityAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickNearestCityActions">
				<s:div id="quickNearestCityAdd" cssClass="quickNearestCityButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Ciudad" />
					</s:div>					
				</s:div>
				<s:div id="quickNearestCityShow" cssClass="quickNearestCityButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Ciudad" />
					</s:div>					
				</s:div>
				<s:div id="quickNearestCityEdit" cssClass="quickNearestCityButton button" >
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
				$(".quickNearestCityButton").button({});
				
				$("#quickNearestCityAdd").click(
					function(event) {
						$("#quickNearestCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickNearestCityButton").button("disable");
						$.get("./QuickNearestCityAction_add",
							{provinceId: $("#nearestProvince").val()},	
							function(response) {
								$("#quickNearestCityFormDiv").html(response);
								
								$("#quickNearestCityFormDialog").dialog({autoOpen:true, 
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
												      $("#quickNearestCityFormDialog").dialog('destroy');
												      
													  if($("#nearestCity").val() == "") {
													      $("#quickNearestCityAdd").button("enable");
													  } else {
													      $("#quickNearestCityAdd, #quickNearestCityEdit, #quickNearestCityShow").button("enable");
													  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickNearestCityAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickNearestCityForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
											    if($("#nearestCity").val() == "") {
											    	$("#quickNearestCityAdd").button("enable");
											    } else {
											    	$("#quickNearestCityAdd, #quickNearestCityEdit, #quickNearestCityShow").button("enable");
											    }
												
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickNearestCityEdit").click(
					function(event) {
						$("#quickNearestCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickNearestCityButton").button("disable");
						$.get("./QuickNearestCityAction_edit",
							{requestId: $("#nearestCity").val(), provinceId: $("#nearestProvince").val()},
							function(response) {
								$("#quickNearestCityFormDiv").html(response);
								$("#quickNearestCityFormDialog").dialog({autoOpen:true, 
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
												      $("#quickNearestCityFormDialog").dialog('destroy');
												      $(".quickNearestCityButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickNearestCityAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() { 
												$("#quickNearestCityForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickNearestCityButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickNearestCityShow").click(
						function(event) {
							$("#quickNearestCityAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickNearestCityButton").button("disable");
							$.get("./QuickNearestCityAction_show",
								{requestId: $("#nearestCity").val(), provinceId: $("#nearestProvince").val()},
								function(response) {
									$("#quickNearestCityFormDiv").html(response);
									$("#quickNearestCityFormDialog").dialog({autoOpen:true, 
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
													      $("#quickNearestCityFormDialog").dialog('destroy');
													      $(".quickNearestCityButton").button("enable");
										    		  }
										    	  );
									    	      $("#quickNearestCityAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickNearestCityButton").button("enable");
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