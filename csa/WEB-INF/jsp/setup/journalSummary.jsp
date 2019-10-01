	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:div cssClass="title">
		Libro Diario
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:form>
					<s:select label="Libro Diario" list="journals" id="journals" listKey="id" listValue="summary" emptyOption="false" headerKey="0" headerValue="Libro Diario Actual - No Cerrado"/>
				</s:form>
			</td>
		</tr>
	</table>

	<s:div id="result">
		<jsp:include page="journalSummaryList.jsp" />
	</s:div>
	
	<script type="text/javascript">
		$("#journals").selectmenu({style:"dropdown", width:500});
	
		$(
			function() {
			
				$("#journals").change(
					function() {
						$.get("./JournalSummaryAction_goTo",
							{journalId: $("#journals").val()},
							function(response) {
								$("#result").html(response);
							}
						);
					}
				);	
			}	
		);
	</script>