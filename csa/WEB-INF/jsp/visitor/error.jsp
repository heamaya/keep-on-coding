	<%@ taglib uri="/struts-tags" prefix="s" %>
	
	
	<table class="error">
		<tr>
			<td>
				<s:div class="ui-widget">
				<s:div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"> 
						<p>
						<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> 
						<strong><s:text name="presentationError" /></strong><s:text name="presentationErrorDescription" /></p>
					</s:div>
				</s:div>
			</td>
		</tr>
	</table>
	
	
