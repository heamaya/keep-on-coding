	<%@ taglib uri="/struts-tags" prefix="s" %>

	<tr>
		<td class="tdLabel">
			<s:div id="quickCompanyAjaxLoader">
			</s:div>
		</td>
		<td> 
			<s:div id="quickCompanyActions">
				<s:div id="quickCompanyAdd" cssClass="quickCompanyButton button">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Empresa Agropecuaria" />
					</s:div>					
				</s:div>
				<s:div id="quickCompanyShow" cssClass="quickCompanyButton button">
					<s:url id="iconUrl" value="/icons/preview.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Mostrar Empresa Agropecuaria" />
					</s:div>					
				</s:div>
				<s:div id="quickCompanyEdit" cssClass="quickCompanyButton button" >
					<s:url id="iconUrl" value="/icons/edit.png" />
					<s:div>
						<img src='<s:property value="%{#iconUrl}" />' title="Editar Empresa Agropecuaria" />
					</s:div>					
				</s:div>
			</s:div>
		</td>
	</tr>

	<script type="text/javascript">
		$(
			function() {
				$(".quickCompanyButton").button({});		
				$("#quickCompanyAdd").click(
					function(event) {
						$("#quickCompanyAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickCompanyButton").button("disable");
						
						var companyTypeId = $("input[type='radio']:checked").val();
						var companyTypeTitle = $("label[for='companyTypes" + companyTypeId + "']").text();
						var companyTypeAction = "";
						var companyDialogWidth = 800;
											
						switch(parseInt(companyTypeId)) {
							case 1:
								companyTypeAction = "QuickLegalPersonAction";
								companyDialogWidth = 750;
							break;
							
							case 2:
								companyTypeAction = "QuickFactCommercialSocietyAction";
							break;
							
							case 3:
								companyTypeAction = "QuickAnonymousCommercialSocietyAction";
							break;
							
							case 4:
								companyTypeAction = "QuickLimitedResponsibilityCommercialSocietyAction";
							break;
						}
						
						
						$.get("./" + companyTypeAction + "_add",	
							function(response) {
								$("#quickCompanyFormDiv").html(response);
								$("#quickCompanyFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:companyDialogWidth,
									title:companyTypeTitle,
									open: function(event, ui) {
						    	          	  $('.ui-dialog-titlebar-close').bind('click', 
									    	      function(event) {
												      $("#quickCompanyFormDialog").dialog('destroy');
												      $("#quickCompanyAdd").button("enable");
									    		  }
									    	  );
						    	          	  
								    	      $("#quickCompanyAjaxLoader").empty();
										  },
									buttons: {
										"Guardar": 
											function() {
												$("#quickCompanyForm").submit();	
												$("#quickCompanyAdd").button("enable");
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$("#quickCompanyAdd").button("enable");
											}
										}
									}
								);
							},"html"
						);
	
					}
				);
				
				$("#quickCompanyEdit").click(
					function(event) {
						$("#quickCompanyAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
						$(".quickCompanyButton").button("disable");						
						
						var companyTypeId = $("input[type='radio']:checked").val();
						var companyTypeTitle = $("label[for='companyTypes" + companyTypeId + "']").text();
						var companyTypeAction = "";
						var companyDialogWidth = 800;
											
						switch(parseInt(companyTypeId)) {
							case 1:
								companyTypeAction = "QuickLegalPersonAction";
								companyDialogWidth = 750;
							break;
							
							case 2:
								companyTypeAction = "QuickFactCommercialSocietyAction";
							break;
							
							case 3:
								companyTypeAction = "QuickAnonymousCommercialSocietyAction";
							break;
							
							case 4:
								companyTypeAction = "QuickLimitedResponsibilityCommercialSocietyAction";
							break;
						}
						
						$.get("./" + companyTypeAction + "_edit",
							{requestId: $("#company").val()},
							function(response) {
								$("#quickCompanyFormDiv").html(response);
								
								$("#quickCompanyFormDialog").dialog({autoOpen:true, 
									stack:true, 
									zIndex:1, 
									position:"center", 
									draggable:true, 
									resizable:true, 
									modal:true,
									width:companyDialogWidth,
									title:companyTypeTitle,
									open: function(event, ui) {
							    	          $('.ui-dialog-titlebar-close').bind('click',
							    	        		  
									    	      function(event) {
												      $("#quickCompanyFormDialog").dialog('destroy');
												      $("#quickCompanyEdit, #quickCompanyShow").button("enable");
									    		  }
							    	          
									    	  );
											  
								    	      $("#quickCompanyAjaxLoader").empty();
										  },
									buttons: {
										"Actualizar": 
											function() {
												$("#quickCompanyForm").submit();
												$("#quickCompanyEdit, #quickCompanyShow").button("enable");
											},
										"Cancelar":
											function() {
												$(this).dialog("destroy");
												$("#quickCompanyEdit, #quickCompanyShow").button("enable");
											}
										}
									}
								);		
								
							}
						);
					}
				);
	
				$("#quickCompanyShow").click(
						function(event) {
							$("#quickCompanyAjaxLoader").append('<img src="' + contextPath + '/icons/ajax-loader.gif" />');
							$(".quickCompanyButton").button("disable");
							
							var companyTypeId = $("input[type='radio']:checked").val();
							var companyTypeTitle = $("label[for='companyTypes" + companyTypeId + "']").text();
							var companyTypeAction = "";
							var companyDialogWidth = 800;
												
							switch(parseInt(companyTypeId)) {
								case 1:
									companyTypeAction = "QuickLegalPersonAction";
									companyDialogWidth = 750;
								break;
								
								case 2:
									companyTypeAction = "QuickFactCommercialSocietyAction";
								break;
								
								case 3:
									companyTypeAction = "QuickAnonymousCommercialSocietyAction";
								break;
								
								case 4:
									companyTypeAction = "QuickLimitedResponsibilityCommercialSocietyAction";
								break;
							}
							
							$.get("./" + companyTypeAction + "_show",
								{requestId: $("#company").val()},
								function(response) {
									$("#quickCompanyFormDiv").html(response);
									$("#quickCompanyFormDialog").dialog({autoOpen:true, 
										stack:true, 
										zIndex:1, 
										position:"center", 
										draggable:true, 
										resizable:true, 
										modal:true,
										width:companyDialogWidth,
										title:companyTypeTitle,
										open: function(event, ui) {
							    	              $('.ui-dialog-titlebar-close').bind('click', 
										    	      function(event) {
													      $("#quickCompanyFormDialog").dialog('destroy');
													      $("#quickCompanyEdit, #quickCompanyShow").button("enable");
										    		  }
										    	  );

									    	      $("#quickCompanyAjaxLoader").empty();
											  },
										buttons: {
											"Ok": 
												function() { 
													$(this).dialog("destroy");
													$("#quickCompanyEdit, #quickCompanyShow").button("enable");
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