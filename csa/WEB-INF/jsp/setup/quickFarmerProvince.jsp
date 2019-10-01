	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickFarmerProvinceAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickFarmerProvinceActions">
				<s:div id="quickFarmerProvinceAdd" cssClass="quickFarmerProvinceButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Provincia" />
					</s:div>					
				</s:div>
				<s:div id="quickFarmerProvinceShow" cssClass="quickFarmerProvinceButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Provincia" />
					</s:div>					
				</s:div>
				<s:div id="quickFarmerProvinceEdit" cssClass="quickFarmerProvinceButton button" >
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
				$(".quickFarmerProvinceButton").button({});
				
				$("#quickFarmerProvinceAdd").click(
					function(event) {
						$("#quickFarmerProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickFarmerProvinceButton").button("disable");
						$.get("./QuickFarmerProvinceAction_add",	
							function(response) {
								$("#quickFarmerProvinceFormDiv").html(response);
								$("#quickFarmerProvinceFormDialog").dialog({autoOpen:true, 
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
												      $("#quickFarmerProvinceFormDialog").dialog('destroy');
												      
											    	  if($("#farmerProvince").val() == "") {
											    	      $("#quickFarmerProvinceAdd").button("enable");
											    	  } else {
											    		  $(".quickFarmerProvinceButton").button("enable");
											    	  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickFarmerProvinceAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickFarmerProvinceForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
									    		if($("#farmerProvince").val() == "") {
									    			$("#quickFarmerProvinceAdd").button("enable");
									    		} else {
									    			$(".quickFarmerProvinceButton").button("enable");
									    		}
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickFarmerProvinceEdit").click(
					function(event) {
						$("#quickFarmerProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickFarmerProvinceButton").button("disable");						
						$.get("./QuickFarmerProvinceAction_edit",
							{requestId: $("#farmerProvince").val()},
							function(response) {
								$("#quickFarmerProvinceFormDiv").html(response);
								
								$("#quickFarmerProvinceFormDialog").dialog({autoOpen:true, 
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
												      $("#quickFarmerProvinceFormDialog").dialog('destroy');
												      $(".quickFarmerProvinceButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickFarmerProvinceAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() {
												$("#quickFarmerProvinceForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickFarmerProvinceButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickFarmerProvinceShow").click(
						function(event) {
							$("#quickFarmerProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickFarmerProvinceButton").button("disable");
							$.get("./QuickFarmerProvinceAction_show",
								{requestId: $("#farmerProvince").val()},
								function(response) {
									$("#quickFarmerProvinceFormDiv").html(response);
									$("#quickFarmerProvinceFormDialog").dialog({autoOpen:true, 
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
													      $("#quickFarmerProvinceFormDialog").dialog('destroy');
													      $(".quickFarmerProvinceButton").button("enable");
										    		  }
										    	  );
												  $(".quickButton").button("enable");
									    	      $("#quickFarmerProvinceAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickFarmerProvinceButton").button("enable");
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