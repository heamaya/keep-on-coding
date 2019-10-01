	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content" >
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden name="previousPath" value="%{previousPath}"></s:hidden>
		<s:hidden id="actionClass" key="actionClass" value="%{actionClass}" />
				 	
 		<s:if test='actionMethod == "create" || actionMethod == "update"'>	
			<s:textfield id="name" label="Nombre" name="name" required="true" size="40" />
			<s:textfield id="tributeKey" label="CUIT" name="tributeKey" required="true" size="40" />
			<s:select id="taxCondition" label="Condición Frente a la AFIP" list="taxConditions" name="taxCondition" listKey="id" listValue="value" emptyOption="true" required="true" value="taxCondition.id" />

			<s:if test="actionClass == 'LegalPersonAction'">
				<s:select id="agricultorFarmer" label="Productor Agropecuario" list="agricultorFarmers" name="farmer" listKey="id" listValue="summary" emptyOption="true" required="true" value="farmer.id"/>
				<jsp:include page="quickFarmer.jsp" />
			</s:if>
			<s:elseif test="agricultorFarmers != null || owners != null" >
					
					<s:optiontransferselect 
						id="agricultorFarmer" 
						doubleId="ownersSelect" 
						buttonCssClass="otsButtons"
						leftTitle='Listado de Productores Agropecuarios' 
						rightTitle='Productores de la Sociedad Comercial'
		                addToLeftLabel='Remover' 
		                addToRightLabel='Agregar'
		                allowUpDownOnLeft="false" 
		                allowUpDownOnRight="false"
		                allowAddAllToLeft="false"
		                allowAddAllToRight="false"
		                allowSelectAll="false"
		                listKey="id" 
		                listValue="summary"
		                doubleListKey="id" 
		                doubleListValue="summary"
		                multiple="true" 
		                doubleMultiple="true"
		                size="5" doubleSize="5"
						list="agricultorFarmers" 
						doubleList="owners" 
						name="agricultorFarmersIds"
						doubleName="ownersIds"
						emptyOption="false" 
						doubleEmptyOption="false"
					>
					</s:optiontransferselect>
					
					<jsp:include page="quickFarmer.jsp" />
			</s:elseif>
			
			<jsp:include page="addressFormFields.jsp" />
		</s:if>
		<s:elseif test='actionMethod == "find"'>
			<s:select label="Modo de Filtro de Texto" list="matchModes" name="matchMode" emptyOption="false" listKey="id" listValue="value" />
			<s:textfield id="name" label="Nombre" name="name" required="false" size="40" />
			<s:textfield id="tributeKey" label="CUIT" name="tributeKey" required="false" size="40" />
			<s:select id="taxCondition" label="Condición Frente a la AFIP" list="taxConditions" name="taxCondition" listKey="id" listValue="value" emptyOption="true" required="false" />
			
			<jsp:include page="addressFormFields.jsp" />
		</s:elseif>
		<s:else>
			<s:textfield label="Nombre" value="%{name}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="CUIT" value="%{tributeKey}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="Condición Frente a la AFIP" value="%{taxCondition.value}"  required="false" readonly="%{readOnly}" size="40" />
			
			<s:if test="actionClass == 'LegalPersonAction'">
				<s:textfield label="Productor Agropecuario" value="%{farmer.summary}" required="false" readonly="%{readOnly}" size="40" />
			</s:if>
   			<s:else>	
   				<tr>
					<td class="tdLabel">
						<label for="farmersTable" class="label">
							Productores Agropecuarios:
						</label>
					</td>
					<td>
						<d:table id="farmersTable" name="${farmers}" class="list" uid="row" requestURI="" export="false" excludedParams="*" >
							<d:column title="Nombre" sortable="true">
								<s:property value='%{#attr.row.firstName}' /> <s:property value='%{#attr.row.lastName}' />
							</d:column>
							<d:column title="Provincia" sortable="true">
								<s:property value='%{#attr.row.address.province}' />
							</d:column>
							<d:column title="Ciudad" sortable="true">
								<s:property value='%{#attr.row.address.city}' />
							</d:column>
						</d:table>
					</td>
				</tr>
			</s:else>

			<jsp:include page="addressFormFields.jsp" />
		</s:else>
		
		<s:submit id="strutsSubmitButton" />
	</s:form>
	
	<script type="text/javascript">
			
		$(function() {
			$("#strutsSubmitButton").hide();		
			
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
									$("#strutsSubmitButton").trigger("click");
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
			
			$(".otsButtons").css(
				{
			  		fontSize: 'x-small',
			  		textAlign: 'center',
			  		verticalAling: 'middle',
			  		fontFamily: 'segoe ui, Arial, sans-serif',
		  			fontWeight: 'bold'								
		  		}
			).button({});
			
			$("label[for='leftTitle'], label[for='rightTitle']").css(
					{
				  		fontSize: 'x-small',
				  		textAlign: 'center',
				  		verticalAling: 'middle',
				  		fontFamily: 'segoe ui, Arial, sans-serif',
			  			fontWeight: 'bold'								
			  		}
			);
			
			var actionClass = $("#actionClass").val();
			
			if(actionClass == "LegalPersonAction") {
		    	
				if($("#agricultorFarmer").val() == "") {
	    			$("#quickFarmerEdit, #quickFarmerShow").button("disable");
	    			$("#quickFarmerAdd").button("enable");
	    		} else {
	    			$("#quickFarmerAdd, #quickFarmerEdit, #quickFarmerShow").button("enable");
				}
			} else {
				$("#quickFarmerEdit, #quickFarmerShow").button("disable");
    			$("#quickFarmerAdd").button("enable");
			}
		    	
		    $("#agricultorFarmer").change(
		    	function() {
		    			
			    	if($("#agricultorFarmer").val() == "") {
			   			$("#quickFarmerEdit, #quickFarmerShow").button("disable");
			   			$("#quickFarmerAdd").button("enable");
			   		} else {
			   			$("#quickFarmerAdd, #quickFarmerEdit, #quickFarmerShow").button("enable");
					}
		    			
		    	}		
		    );
		   	    					
			var actionMethod = $("#actionMethod").val();
			    	
			if(actionMethod == "create" || actionMethod == "update") {
				
				$("select").not("[multiple='multiple']").selectmenu({style:'dropdown', width:400});

				$("#currentForm").validate({
					rules: {
						name: {
							required: true,
							minlength: 1,
							maxlength:512
						},
						tributeKey: {
							required: true,
							digits:true,
							minlength:11,
							maxlength:11
						},
						taxCondition:"required",
						province:"required",
						street: {
							maxlength:512							
						},
						streetNumber: {
							digits:true,
							maxlength:10							
						},
						floor: {
							digits:true,
							maxlength:3							
						},
						department: {
							maxlength:255							
						}
					},
					submitHandler: function(form) {
						$(form).ajaxSubmit({
							url: $("#currentForm").attr("action"),
							type:"POST",
							target:$("#result"),

							success: function() {
								$("#currentDialog").dialog("close");
							}
						});
					}, 
					invalidHandler: function(form, validator) {
						$("#currentDialog").parent().find('.ui-dialog-buttonpane button:first-child').button("enable").show();
		        	    $("#currentDialog").parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("enable").show();
		        	    $("#currentDialog").parent().find("#ajaxLoader").replaceWith("");
					}
				});
			} else if(actionMethod == "delete" || actionMethod == "find") {
				
				if(actionMethod == "find") {
					$("select").not("[multiple='multiple']").selectmenu({style:'dropdown', width:400});	
				}
				
				$("#currentForm").ajaxForm({
					url: $("#currentForm").attr("action"),
					type:"POST",
					target:$("#result"),
					success: function() {
						$("#currentDialog").dialog("close");
					} 
				});
			}
		});
	</script>