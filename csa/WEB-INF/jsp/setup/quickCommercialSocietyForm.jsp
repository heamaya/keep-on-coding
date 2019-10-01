	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:form id="quickCompanyForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />
		<s:hidden name="previousPath" value="%{previousPath}" />
		
		<s:if test='actionMethod == "update"'>
			<s:textfield id="name" label="Nombre" name="name" required="true" size="40" />
			<s:textfield id="tributeKey" label="CUIT" name="tributeKey" required="true" size="40" />
			<s:select id="taxCondition" label="Condición Frente a la AFIP" list="taxConditions" name="taxCondition" listKey="id" listValue="value" emptyOption="true" required="true" value="taxCondition.id" required="true" />
			
			<s:if test="agricultorFarmers != null || owners != null" >
					
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
			</s:if>
			
			<jsp:include page="addressFormFields.jsp" />
			
			<script type="text/javascript">
			
				$(function() {

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
						
					$("select").not("[multiple='multiple']").selectmenu({style:'dropdown', width:400});

					$("#quickCompanyForm").validate({
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
								url: $("#quickCompanyForm").attr("action"),
								type:"POST",
								success: function(companies) {
									$("#quickCompanyFormDialog").dialog("close");
									var selectedId = $("#company").val();
					    		    var options = '<option value=""></option>';
						    		  
					    	      	for (var i = 0; i < companies.length; i++) {
					    	      		
					    	      		if(companies[i].id == selectedId) {
					    	      			options += '<option value="' + companies[i].id + '" selected="selected">' + companies[i].summary + '</option>';
					    	      		} else {
					    		    		options += '<option value="' + companies[i].id + '">' + companies[i].summary + '</option>';
					    	      		}
					    	      		
					    		  	}
				    		      
						    	  	$("#company").html(options);
									$(".quickCompanyButton").button("enable");
									$("#quickCompanyFormDialog").dialog("destroy");
									$("#company").selectmenu({style:"dropdown", width:400});
								},
								beforeSerialize: function(form, options) { 
									$(form).attr("requestId", $("#company").val());
									
									$('#agricultorFarmer option').each(
						  				function(index, element) {
						  					$('<input />').attr('type', 'hidden')
						  			        	.attr('name', 'agricultorFarmersIds')
						  			        	.attr('value', element.value)
						  			        	.appendTo('#quickCompanyForm');
						  					}
						  				);
											
					  					$('#ownersSelect option').each(
						  					function(index, element) {
						  						$('<input />').attr('type', 'hidden')
						  			            .attr('name', 'ownersIds')
						  			            .attr('value', element.value)
						  			            .appendTo('#quickCompanyForm');
						  					}
						  				);
									},
								dataType:"json"
							});
														
						}
					});
				});
			</script>
		</s:if>
		<s:elseif test='actionMethod == "create"'>
			<s:textfield id="name" label="Nombre" name="name" required="true" size="40" />
			<s:textfield id="tributeKey" label="CUIT" name="tributeKey" required="true" size="40" />
			<s:select id="taxCondition" label="Condición Frente a la AFIP" list="taxConditions" name="taxCondition" listKey="id" listValue="value" emptyOption="true" required="true" value="taxCondition.id" required="true" />
				
			<s:if test="agricultorFarmers != null || owners != null" >
					
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
			</s:if>
			
			<jsp:include page="addressFormFields.jsp" />
			
			
			<script type="text/javascript">
			
				$(function() {		
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
					
					$("select").not("[multiple='multiple']").selectmenu({style:'dropdown', width:400});

					
					$("#quickCompanyForm").validate({
						rules: {
							companyType:"required",
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
						}, submitHandler: function(form) {
							$(form).ajaxSubmit({
								url: $("#quickCompanyForm").attr("action"),
								type:"POST",
								success: function(companies) {
					    			var options = '<option value="" selected="selected"></option>';
						    		  
					    	      	for (var i = 0; i < companies.length; i++) {
					    		    	options += '<option value="' + companies[i].id + '">' + companies[i].summary + '</option>';
					    		  	}
				    		      
						    	  	$("#company").html(options);	
						    	  	$("#quickCompanyAdd").button("enable");
						    	  	$("#quickCompanyFormDialog").dialog("destroy");
						    	  	$("#company").selectmenu({style:"dropdown", width:400});
								},
								beforeSerialize: function(form, options) { 
									$(form).attr("requestId", $("#company").val());
									
									$('#agricultorFarmer option').each(
						  				function(index, element) {
						  					$('<input />').attr('type', 'hidden')
						  			        	.attr('name', 'agricultorFarmersIds')
						  			        	.attr('value', element.value)
						  			        	.appendTo('#quickCompanyForm');
						  					}
						  			);
											
					  				$('#ownersSelect option').each(
						  				function(index, element) {
						  					$('<input />').attr('type', 'hidden')
						  		            .attr('name', 'ownersIds')
						  		            .attr('value', element.value)
						  		            .appendTo('#quickCompanyForm');
						  				}
						  			);
								},
								dataType:"json"
							});
						}
					});
				});
			</script>		
		</s:elseif>
		<s:else>
			<s:textfield label="Nombre" value="%{name}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="CUIT" value="%{tributeKey}" required="false" readonly="%{readOnly}" size="40" />
			<s:textfield label="Condición Frente a la AFIP" value="%{taxCondition.value}"  required="false" readonly="%{readOnly}" size="40" />
			
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
			
			<jsp:include page="addressFormFields.jsp" />		
		</s:else>
	</s:form>