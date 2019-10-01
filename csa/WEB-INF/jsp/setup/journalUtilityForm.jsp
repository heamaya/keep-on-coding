	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:form id="currentForm" action="%{mappedRequest}" cssClass="ui-dialog-content">
		<jsp:include page="createReadUpdateDeleteHidden.jsp" />	
		<s:file id="journal" name="journal" label="Entradas" required="true"/>
		<s:textarea id="description" label="Descripción" name="description" readonly="%{readOnly}" required="true" rows="5" cols="40" maxlength="4096" />
		<jsp:include page="formSubmit.jsp" />
	</s:form>
		
	<script type="text/javascript">
		$(function() {
	          	
  			$("#journal").fileinput({
  				buttonText: "Buscar",
  				inputText: ""
  			});
			
			$("#currentForm").validate({
				submitHandler: function(form) {
					$(form).ajaxSubmit({
						url: $("#currentForm").attr("action"),
						type:"POST",
						target:$("#result"),
						success: function() {
							$("#currentDialog").dialog("close");
						}		
					});
				}, invalidHandler: function(form, validator) {
					$("#currentDialog").parent().find('.ui-dialog-buttonpane button:first-child').button("enable").show();
	        	    $("#currentDialog").parent().find('.ui-dialog-buttonpane button:nth-child(2)').button("enable").show();
	        	    $("#currentDialog").parent().find("#ajaxLoader").replaceWith("");
				}
			});
					
  			$("#journal").rules("add", {required:true, accept:"csv"});
		});
	</script>