	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />
	
	<d:table name="${journalEntries}"  class="list" uid="row" pagesize="10" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="Fecha" sortable="true" >
 		  	<s:url id="url" action="%{actionClass}_show" namespace="/setup" escapeAmp="false">
				<s:param name="requestId" value="%{#attr.row.id}" />
			</s:url>
			
			<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" onCompleteTopics="openCurrentDialog"  >
				<s:date name="%{#attr.row.entryDate}" format="dd/MM/yyyy"/> <br />
    		</sj:a> 
 		</d:column>
 		
 		 <d:column title="Usuario" sortable="true">
 			<s:property value='%{#attr.row.user.summary}' />
 		</d:column>
 		
 		<d:column title="Tipo de Entrada" sortable="true">
 			<s:if test="actionClass == 'JournalIncomeEntryAction'">
 				<s:property value='%{#attr.row.incomeType.value}' />
 			</s:if>
 			<s:elseif test="actionClass == 'JournalOutcomeEntryAction'">
 				<s:property value='%{#attr.row.outcomeType.value}' />
 			</s:elseif>
 		</d:column>
 		
 		<d:column title="Descripción" sortable="true">
 			<s:property value='%{#attr.row.description}' />
 		</d:column>
 		
 		<d:column title="Monto" sortable="true">
 			<s:property value='%{getText("format.number", {#attr.row.amount})}' /> 			
 		</d:column>
 		
  		<d:column title="">
  			<s:if test="(#attr.row.isClosed) == false">
  		
    			<s:url id="url" action="%{actionClass}_edit" namespace="/setup" escapeAmp="false">
	    			<s:param name="requestId" value="%{#attr.row.id}" />
    			</s:url>
    			<sj:a href="%{url}" cssClass="listButton" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:div cssClass="button">
						<img src='<s:property value="%{#editIconUrl}" />' title='<s:property value="%{#editTitle}" />' />
					</s:div>
    			</sj:a>
    		
				<s:url id="url" action="%{actionClass}_remove" namespace="/setup" escapeAmp="false">
	   				<s:param name="requestId" value="%{#attr.row.id}" />
				</s:url>
				<sj:a cssClass="listButton" href="%{url}" targets="currentFormDiv" button="true" onCompleteTopics="openCurrentDialog" requestType="GET">
					<s:div cssClass="button">
						<img src='<s:property value="%{#removeIconUrl}" />' title='<s:property value="%{#removeTitle}" />' />
					</s:div>
				</sj:a>
			</s:if>
    	</d:column>		
	</d:table>
	
	<jsp:include page="pagination.jsp"></jsp:include>