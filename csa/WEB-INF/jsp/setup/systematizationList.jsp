	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="beforePhotoIconUrl" value="/icons/beforePhoto-16x16.png" />
	<s:url id="afterPhotoIconUrl" value="/icons/afterPhoto-16x16.png" />
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />
	<s:url id="googleEarthIconUrl" value="/icons/GoogleEarth-16x16.jpg" />

	<d:table name="${systematizations}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="Empresa Agropecuaria" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.company.name}" />
    		</sj:a> 
 		</d:column>

 		 <d:column title="Campo" sortable="true" >
   			<s:property value="%{#attr.row.land.name}" />
 		</d:column>
 		
 		 <d:column title="Es Trabajo Destacado (Se Muestra a los Visitantes)" sortable="false" >
   			<s:if test="#attr.row.starred == true">
   				Sí
   			</s:if>
   			<s:else>
   				No
   			</s:else>
 		</d:column>
 			
  		<d:column title="">
    		<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
    			<s:param name="requestId" value="%{#attr.row.id}" />
    		</s:url>
    		<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#editIconUrl}" />' title='Editar Sistematización <s:property value="%{#attr.row.land.name}" />' />
				</s:div>
    		</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
   				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Sistematización <s:property value="%{#attr.row.land.name}" />' />
				</s:div>
			</sj:a>
			<s:url id="url" action="SystematizationBeforePhotoAction" namespace="/setup" escapeAmp="false">
   				<s:param name="systematizationId" value="%{#attr.row.id}" />
			</s:url>
			<s:a cssClass="listButton systematizationPhotoButton" href="%{url}"> 
				<s:div cssClass="button">
					<img src='<s:property value="%{#beforePhotoIconUrl}" />' title='Editar Fotos Anteriores a la Sistematización de <s:property value="%{#attr.row.land.name}" />' />
				</s:div>
			</s:a> 
			<s:url id="url" action="SystematizationAfterPhotoAction" namespace="/setup" escapeAmp="false">
   				<s:param name="systematizationId" value="%{#attr.row.id}" />
			</s:url>
			<s:a cssClass="listButton systematizationPhotoButton" href="%{url}">
				<s:div cssClass="button">
					<img src='<s:property value="%{#afterPhotoIconUrl}" />' title='Editar Fotos Posteriores a la Sistematización de <s:property value="%{#attr.row.land.name}" />' />
				</s:div>
			</s:a>
			
    		<s:url id="geUrl" action="SystematizationDownloadFileAction_getFile" namespace="/setup" escapeAmp="false">
    			<s:param name="fileName" value="%{#attr.row.systematizationFileName}" />
    			<s:param name="contentType" value="%{#attr.row.systematizationContentType}" />
    			<s:param name="systematizationId" value="%{#attr.row.id}" />
    		</s:url>
    		
			<s:div cssClass="showInGoogleEarth" id="%{geUrl}">
				<div class="button">
					<img src='<s:property value="%{#googleEarthIconUrl}" />' title='Mostrar en Google Earth' />
				</div>					
   			</s:div>		
    	</d:column>		
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>
		
	<script type="text/javascript">
			
		$(
				
			function() {
				$(".systematizationPhotoButton").button({});
				$(".showInGoogleEarth").button({}).click(
					function(event) {
						showSystematization($(this).attr("id"));	
					}
				);

			}
		);	
	</script>