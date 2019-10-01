<%@ taglib uri="/struts-tags" prefix="s" %>

	<table class="error">
		<tr>
			<td>
				<div class="ui-widget">
				<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;"> 
						<p>
							<span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span> 
							<strong>Error:</strong><br />
							<s:property value="%{exception.message}" />
						</p>
					</div>
				</div>
			</td>
		</tr>
	</table>