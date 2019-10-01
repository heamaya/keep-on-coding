	<%@ page language="java" contentType="text/plain;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickLandAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickLandActions">
				<s:div id="quickLandAdd" cssClass="quickLandButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Campo" />
					</s:div>					
				</s:div>
				<s:div id="quickLandShow" cssClass="quickLandButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Campo" />
					</s:div>					
				</s:div>
				<s:div id="quickLandEdit" cssClass="quickLandButton button" >
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Editar Campo" />
					</s:div>					
				</s:div>
			</s:div>
		</td>
	</tr>

	<script type="text/javascript">
		$(
			function() {
				$(".quickLandButton").button({});
				
				$("#quickLandAdd").click(
					function(event) {
						$("#quickLandAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickLandButton").button("disable");
						$.get("./QuickLandAction_add",	
							{companyId: $("#company").val()},
							function(response) {
								$("#quickLandFormDiv").html(response);
								$("#quickLandFormDialog").dialog({
									autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:650,
									title:"Campo",
									open: function(event, ui) {
						    	          	  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickLandFormDialog").dialog('destroy');
												      
											    	  if($("#land").val() == "") {
											    	      $("#quickLandAdd").button("enable");
											    	  } else {
											    		  $(".quickLandButton").button("enable");
											    	  }
									    		  }
									    	  );
						    	          	  
								    	      $("#quickLandAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickLandForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												
									    		if($("#land").val() == "") {
									    			$("#quickLandAdd").button("enable");
									    		} else {
									    			$(".quickLandButton").button("enable");
									    		}
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickLandEdit").click(
					function(event) {
						$("#quickLandAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickLandButton").button("disable");						
						$.get("./QuickLandAction_edit",
							{requestId: $("#land").val(), companyId: $("#company").val()},
							function(response) {
								$("#quickLandFormDiv").html(response);
								
								$("#quickLandFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:650,
									title:"Campo",
									open: function(event, ui) {
							    	          $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickLandFormDialog").dialog('destroy');
												      $(".quickLandButton").button("enable");
									    		  }
									    	  );
											  
								    	      $("#quickLandAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() {
												$("#quickLandForm").submit();
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$(".quickLandButton").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickLandShow").click(
						function(event) {
							$("#quickLandAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickLandButton").button("disable");
							$.get("./QuickLandAction_show",
								{requestId: $("#land").val(), companyId: $("#company").val()},
								function(response) {
									$("#quickLandFormDiv").html(response);
									$("#quickLandFormDialog").dialog({autoOpen:true, 
										stack:true, 
										zIndex:1, 
										position:"center", 
										draggable:true, 
										resizable:true, 
										modal:true,
										width:650,
										title:"Campo",
										open: function(event, ui) {
							    	              $('.ui-dialog-titlebar-close').bind('click', 
										    	      function(event) {
													      $("#quickLandFormDialog").dialog('destroy');
													      $(".quickLandButton").button("enable");
										    		  }
										    	  );
												  $(".quickButton").button("enable");
									    	      $("#quickLandAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$(".quickLandButton").button("enable");
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