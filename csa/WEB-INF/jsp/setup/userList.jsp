	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="beforePhotoIconUrl" value="/icons/beforePhoto-16x16.png" />
	<s:url id="afterPhotoIconUrl" value="/icons/afterPhoto-16x16.png" />
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${users}"  class="list" uid="row" pagesize="20" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="Nombre de Usuario" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup/user" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.username}" />
    		</sj:a> 
 		</d:column>

 		 <d:column title="Apellido" sortable="true" >
   			<s:property value="%{#attr.row.person.lastName}" />
 		</d:column>
 		
 		 <d:column title="Nombre" sortable="true" >
   			<s:property value="%{#attr.row.person.firstName}" />
 		</d:column>
 		
 		 <d:column title="Tipo de Usuario" sortable="true" >
   			<s:property value="%{#attr.row.role.value}" />
 		</d:column>
 			
	  	<d:column title="">
	  		<s:if test='#attr.row.username != authenticatedUser.username'>
	    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup/user" escapeAmp="false">
	    			<s:param name="requestId" value="%{#attr.row.id}" />
	    		</s:url>
	    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:div cssClass="button">
						<img src='<s:property value="%{#editIconUrl}" />' title='Editar Usuario <s:property value="%{#attr.row.land.name}" />' />
					</s:div>
	    		</sj:a>
				<s:url id="url" action="%{actionClass}_remove" namespace="/setup/user" escapeAmp="false">
	   				<s:param name="requestId" value="%{#attr.row.id}" />
				</s:url>
				<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:div cssClass="button">
						<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Usuario <s:property value="%{#attr.row.land.name}" />' />
					</s:div>
				</sj:a>
			</s:if>	
	    </d:column>
	</d:table>
	
	<script type="text/javascript">
		var namespace = "/setup/user";
		var actionClass = '<s:property value="%{actionClass}"/>';
		var downloadFileAction = '<s:property value="%{downloadFileAction}"/>';
		
		$(".pageButton").unbind("click");
		
		if(downloadFileAction != null && downloadFileAction != '') {
			downloadFileAction = "&downloadFileAction=" + downloadFileAction;
		} else {
			downloadFileAction = "";
		}

		$("#first").button({icons:{primary:"ui-icon-seek-first"}})
			.click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#first").attr("data") + downloadFileAction, 	
					function(response) {				
						$("#result").empty().html(response);			
					},
					"html"
				);					
			}
		);
			
		$("#next").button({
			icons:{primary:"ui-icon-seek-next"}}).
			click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#next").attr("data")  + downloadFileAction, 	
					function(response) {
						$("#result").empty().html(response);				
					},
					"html"
				);					
			}
		);

		$("#previous").button({icons:{primary:"ui-icon-seek-prev"}})
			.click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#previous").attr("data") + downloadFileAction, 	
					function(response) {
						$("#result").html(response);				
					},
					"html"
				);					
			}
		);

		$("#last").button({
			icons:{primary:"ui-icon-seek-end"}}).
			click(function(event) {
				$.get(
					namespace + "/" + actionClass + "_list" + $("#last").attr("data") + downloadFileAction, 	
					function(response) {
						$("#result").empty().html(response);				
					},
					"html"
				);					
			
		});
		
		
		$(".pageLinks").css({width:"100%"});
		$("[id^='goTo']").each(
			function(index, element) {
				$(this).button({label:$(this).attr("page")});	
			}
		);
		$(".pageButton").css({fontSize:"xx-small"});
		$("[data='currentPage']").button("disable");
		$("[id^='goTo']").not("[data='currentPage']").bind("click", 
			function(event) {
						
					$.get(
						namespace + "/" + actionClass + "_list" + $(this).attr("data")  + downloadFileAction, 	
						function(response) {
							$("#result").empty().html(response);				
						},
						"html"
					);				
			}
		);
	</script>