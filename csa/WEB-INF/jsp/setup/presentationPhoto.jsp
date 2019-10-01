	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>

	<s:if test="actionClass == 'DikePresentationPhotoAction'" >
		<s:set name="presentationPhotoTitle">Foto de Presentación de Dique</s:set>
		<s:set name="presentationPhotosTitle">Fotos de Presentación de Diques</s:set>
		<s:set name="downloadFileAction">DikePresentationPhotoDownloadFileAction</s:set>
	</s:if>
	<s:elseif test="actionClass == 'TerracePresentationPhotoAction'">
		<s:set name="presentationPhotoTitle">Foto de Presentación de Terraza</s:set>
		<s:set name="presentationPhotosTitle">Fotos de Presentación de Terrazas	</s:set>
		<s:set name="downloadFileAction">TerracePresentationPhotoDownloadFileAction</s:set>
	</s:elseif>
	<s:elseif test="actionClass == 'ChannelPresentationPhotoAction'">
		<s:set name="presentationPhotoTitle">Foto de Presentación de Canal</s:set>
		<s:set name="presentationPhotosTitle">Fotos de Presentación de Canales</s:set>
		<s:set name="downloadFileAction">ChannelPresentationPhotoDownloadFileAction</s:set>
	</s:elseif>
	<s:elseif test="actionClass == 'GullyRecoveryPresentationPhotoAction'">
		<s:set name="presentationPhotoTitle">Foto de Presentación de Recuperación de Cárcava</s:set>
		<s:set name="presentationPhotosTitle">Fotos de Presentación de Recuperaciones de Cárcavas</s:set>
		<s:set name="downloadFileAction">GullyRecoveryPresentationPhotoDownloadFileAction</s:set>	
	</s:elseif>
	<s:elseif test="actionClass == 'HomePresentationPhotoAction'">
		<s:set name="presentationPhotoTitle">Foto de Presentación Principal</s:set>
		<s:set name="presentationPhotosTitle">Fotos de Presentación Principal</s:set>
		<s:set name="downloadFileAction">HomePresentationPhotoDownloadFileAction</s:set>	
	</s:elseif>
	<s:elseif test="actionClass == 'ErosionPresentationPhotoAction'">
		<s:set name="presentationPhotoTitle">Foto de Presentación de Qué Hacemos - Erosión</s:set>
		<s:set name="presentationPhotosTitle">Fotos de Presentación de Qué Hacemos - Erosión</s:set>
		<s:set name="downloadFileAction">ErosionPresentationPhotoDownloadFileAction</s:set>	
	</s:elseif>

	<s:div cssClass="title">
		<s:property value="presentationPhotosTitle" />
	</s:div>

	<table class="ui-tabs crudmenu">
		<tr>
			<td>
				<s:url id="url"  action="%{actionClass}_add" namespace="/setup">
					<s:param name="downloadFileAction" value="%{downloadFileAction}" />
				</s:url>
				
				<sj:a cssClass="listButton" href="%{#url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:url id="iconUrl" value="/icons/add.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title="Agregar Foto"/>
					</s:div>					
				</sj:a>
			</td>
			<td>
				<s:url id="url" action="%{actionClass}_list" namespace="/setup" >
					<s:param name="downloadFileAction" value="%{downloadFileAction}" />
				</s:url>
				
    			<sj:a cssClass="listButton" href="%{url}" targets="result" button="true" requestType="GET" onCompleteTopics="completeRefresh">
					<s:url id="iconUrl" value="/icons/refresh.png" />
					<s:div cssClass="button">
						<img src='<s:property value="%{#iconUrl}" />' title="Refrescar Listado de Fotos" />
					</s:div>					
    			</sj:a>
			</td>
			<td>
				<s:div id="ajaxLoader">
				</s:div>
			</td>
		</tr>
	</table>

	<sj:dialog id="currentDialog" width="750" modal="true" draggable="true" position="center" resizable="true" title="%{#presentationPhotoTitle}" autoOpen="false" onBeforeTopics="beforeOpenCurrentDialog">
		<s:div id="currentFormDiv">
		</s:div>
	</sj:dialog>

	<s:div id="result">
		<jsp:include page="presentationPhotoList.jsp" />
	</s:div>