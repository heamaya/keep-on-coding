	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${farmers}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*" > 		
 		 <d:column title="Apellido y Nombre" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
    			<s:property value="%{#attr.row.lastName}" /> <s:property value="%{#attr.row.firstName}" />
    		</sj:a> 
 		</d:column>
 		
 		 <d:column title="Provincia" sortable="true" >
   			<s:property value="%{#attr.row.address.province.name}" />
 		</d:column>
 		
 		 <d:column title="Ciudad" sortable="true" >
   			<s:property value="%{#attr.row.address.city.name}" />
 		</d:column>
 			
	  	<d:column title="">
	    	<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
	    		<s:param name="requestId" value="%{#attr.row.id}" />
	    	</s:url>
	    	<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#editIconUrl}" />' title='Editar Productor Agropecuario <s:property value="%{#attr.row.lastName}" /> <s:property value="%{#attr.row.firstName}" />' />
				</s:div>
	    	</sj:a>
			<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
	   			<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
				<s:div cssClass="button">
					<img src='<s:property value="%{#removeIconUrl}" />' title='Eliminar Productor Agropecuario <s:property value="%{#attr.row.lastName}" /> <s:property value="%{#attr.row.firstName}" />' />
				</s:div>
			</sj:a>	
	    </d:column>
	</d:table>
	<jsp:include page="pagination.jsp"></jsp:include>