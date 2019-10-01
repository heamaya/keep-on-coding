	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickNearestProvinceAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickNearestProvinceActions">
				<s:div id="quickNearestProvinceAdd" cssClass="quickNearestProvinceButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Provincia" />
					</s:div>					
				</s:div>
				<s:div id="quickNearestProvinceShow" cssClass="quickNearestProvinceButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Provincia" />
					</s:div>					
				</s:div>
				<s:div id="quickNearestProvinceEdit" cssClass="quickNearestProvinceButton button" >
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Editar Provincia" />
					</s:div>					
				</s:div>
			</s:div>
		</td>
	</tr>

	<script type="text/javascript">
		$(
			function() {
				$(".quickNearestProvinceButton").button({});
				
				$("#quickNearestProvinceAdd").click(
					function(event) {
						$("#quickNearestProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickNearestProvinceButton").button("disable");
						$.get("./QuickNearestProvinceAction_add",	
							function(response) {
								$("#quickNearestProvinceFormDiv").html(response);
								$("#quickNearestProvinceFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:550,
									title:"Provincia",
									open: function(event, ui) {
						    	          	  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickNearestProvinceFormDialog").dialog('destroy');
												      
											    	  if($("#nearestProvince").val() == "") {
											    	      $("#quickNearestProvinceAdd").button("enable");
											    	  } else {
											    		  $(".quickNearestProvinceButton").button("enable");
											    	  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickNearestProvinceAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickNearestProvinceForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
									    		if($("#nearestProvince").val() == "") {
									    			$("#quickNearestProvinceAdd").button("enable");
									    		} else {
									    			$(".quickNearestProvinceButton").button("enable");
									    		}
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickNearestProvinceEdit").click(
					function(event) {
						$("#quickNearestProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickNearestProvinceButton").button("disable");						
						$.get("./QuickNearestProvinceAction_edit",
							{requestId: $("#nearestProvince").val()},
							function(response) {
								$("#quickNearestProvinceFormDiv").html(response);
								
								$("#quickNearestProvinceFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:500,
									title:"Provincia",
									open: function(event, ui) {
							    	          $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickNearestProvinceFormDialog").dialog('destroy');
												      $(".quickNearestProvinceButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickNearestProvinceAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() {
												$("#quickNearestProvinceForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickNearestProvinceButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickNearestProvinceShow").click(
						function(event) {
							$("#quickNearestProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickNearestProvinceButton").button("disable");
							$.get("./QuickNearestProvinceAction_show",
								{requestId: $("#nearestProvince").val()},
								function(response) {
									$("#quickNearestProvinceFormDiv").html(response);
									$("#quickNearestProvinceFormDialog").dialog({autoOpen:true, 
										stack:true, 
										zIndex:1, 
										position:"center", 
										draggable:true, 
										resizable:true, 
										modal:true,
										width:500,
										title:"Provincia",
										open: function(event, ui) {
							    	              $('.ui-dialog-titlebar-close').bind('click', 
										    	      function(event) {
													      $("#quickNearestProvinceFormDialog").dialog('destroy');
													      $(".quickNearestProvinceButton").button("enable");
										    		  }
										    	  );
												  $(".quickButton").button("enable");
									    	      $("#quickNearestProvinceAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickNearestProvinceButton").button("enable");
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