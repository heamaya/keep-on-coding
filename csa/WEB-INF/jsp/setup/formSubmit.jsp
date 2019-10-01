	<script type="text/javascript">
		$(
			function() {
				var submitLabel = "";
		    	
				if($("#actionMethod").val() == "create") {
					submitLabel = "Guardar";
				} else if($("#actionMethod").val() == "update") {
					submitLabel = "Actualizar";
				} else if($("#actionMethod").val() == "delete") {
					submitLabel = "Eliminar";
				} else if($("#actionMethod").val() == "") {
					submitLabel = "Ok";
				}  else if($("#actionMethod").val() == "find") {
					submitLabel = "Filtrar";
				}
				
				$("#currentDialog").dialog(
					{
						buttons:[ 
							{ 
								text: submitLabel,
								click: function(event) {
									
									if(submitLabel == "Ok") {
										$("#currentDialog").dialog("close");
									} else {
										$(this).parent().find('.ui-dialog-buttonpane button:first-child').button("disable").hide();
						        	    $(this).parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("disable").hide();
						        	    $(this).parent().find('.ui-dialog-buttonpane').append('<div id="ajaxLoader" class="loader"><img src="' + contextPath + '/icons/ajax-loader.gif" /></div>');
										$("#currentForm").submit();
									}
								}
							},
							{
								text: "Cancelar",
								click: function(event) {
									$("#currentDialog").dialog("close");
								}
							}
						]
					}		
				);
			}
		);
	</script>