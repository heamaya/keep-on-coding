	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickProvinceAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickProvinceActions">
				<s:div id="quickProvinceAdd" cssClass="quickProvinceButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Provincia" />
					</s:div>					
				</s:div>
				<s:div id="quickProvinceShow" cssClass="quickProvinceButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Provincia" />
					</s:div>					
				</s:div>
				<s:div id="quickProvinceEdit" cssClass="quickProvinceButton button" >
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Editar Provincia" />
					</s:div>					
				</s:div>
				<!--  
				<s:div id="quickProvinceRemove" cssClass="quickButton">
					<s:url id="iconUrl" value="/icons/remove.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title='<s:text name="quickProvinceRemove"/>' />
					</s:div>					
				</s:div>
				-->
			</s:div>
		</td>
	</tr>

	<script type="text/javascript">
		$(
			function() {
				$(".quickProvinceButton").button({});
				
				$("#quickProvinceAdd").click(
					function(event) {
						$("#quickProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickProvinceButton").button("disable");
						$.get("./QuickProvinceAction_add",	
							function(response) {
								$("#quickProvinceFormDiv").html(response);
								$("#quickProvinceFormDialog").dialog({autoOpen:true, 
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
												      $("#quickProvinceFormDialog").dialog('destroy');
												      
											    	  if($("#province").val() == "") {
											    	      $("#quickProvinceAdd").button("enable");
											    	  } else {
											    		  $(".quickProvinceButton").button("enable");
											    	  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickProvinceAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickProvinceForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
									    		if($("#province").val() == "") {
									    			$("#quickProvinceAdd").button("enable");
									    		} else {
									    			$(".quickProvinceButton").button("enable");
									    		}
											}
										}
									}
								);
								
								
								
							},"html"
						);
	
					}
				);
				
				$("#quickProvinceEdit").click(
					function(event) {
						$("#quickProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickProvinceButton").button("disable");						
						$.get("./QuickProvinceAction_edit",
							{requestId: $("#province").val()},
							function(response) {
								$("#quickProvinceFormDiv").html(response);
								
								$("#quickProvinceFormDialog").dialog({autoOpen:true, 
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
												      $("#quickProvinceFormDialog").dialog('destroy');
												      $(".quickProvinceButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickProvinceAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() {
												$("#quickProvinceForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickProvinceButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickProvinceShow").click(
						function(event) {
							$("#quickProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickProvinceButton").button("disable");
							$.get("./QuickProvinceAction_show",
								{requestId: $("#province").val()},
								function(response) {
									$("#quickProvinceFormDiv").html(response);
									$("#quickProvinceFormDialog").dialog({autoOpen:true, 
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
													      $("#quickProvinceFormDialog").dialog('destroy');
													      $(".quickProvinceButton").button("enable");
										    		  }
										    	  );
												  $(".quickButton").button("enable");
									    	      $("#quickProvinceAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickProvinceButton").button("enable");
												}
										}
									});		
									
								}
							);
						}
					);
				/*
				$("#quickProvinceRemove").click(
					function(event) {
						$("#quickProvinceAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickButton").button("disable");
						$.get("./QuickProvinceAction_remove",
							{requestId: $("#province").val()},
							function(response) {
								$("#quickProvinceFormDiv").html(response);
								$("#quickProvinceFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:500,
									title:"Province",
									open: function(event, ui) {
					    	                  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickProvinceFormDialog").dialog('destroy');
									    		  }
									    	  );
											  $(".quickButton").button("enable");
								    	      $("#quickProvinceAjaxLoader").empty();
										  }, 
									buttons: {
										"Delete": 
											function() { 
												$.post("./QuickProvinceAction_delete",
														$("#quickProvinceForm").attr("requestId", $("#province").val()).serialize(),
													function(provinces) {
										    			var options = '<option value=""></option>';
										    		  
										    	      	for (var i = 0; i < provinces.length; i++) {
										    		    	options += '<option value="' + provinces[i].id + '">' + provinces[i].name + '</option>';
										    		  	}
									    		      
											    	  	$("#province").html(options);		
													},
													"json"
												);
												
												$(this).dialog("destroy");
												$(".quickButton").button("enable");; 
											},
										"Cancel":
											function() {
												$(this).dialog("destroy");
												$(".quickButton").button("enable");
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