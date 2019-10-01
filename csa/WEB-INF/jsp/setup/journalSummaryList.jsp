	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	<%@ taglib uri="/struts-tags" prefix="s" %>
	<%@ taglib uri="/struts-jquery-tags" prefix="sj" %>
	<%@ taglib uri="http://displaytag.sf.net" prefix="d" %>
	
	<s:url id="removeIconUrl" value="/icons/remove.png" />
	<s:url id="editIconUrl" value="/icons/edit.png" />

	<d:table name="${entries}"  class="list" uid="row" pagesize="15" sort="list" requestURI="" export="false" excludedParams="*">
 		
 		 <d:column title="Fecha" sortable="true" >
			<s:date name="%{#attr.row.entryDate}" format="dd/MM/yyyy" />
 		</d:column>
 		
 		<d:column title="Tipo de Entrada" sortable="true" >
 			<s:if test="(#attr.row) instanceof com.tipuana.csa.model.JournalIncomeEntry">
 				<s:property value="#attr.row.incomeType.value" />
 			</s:if>
 			<s:elseif test="(#attr.row) instanceof com.tipuana.csa.model.JournalOutcomeEntry">
 				<s:property value="#attr.row.outcomeType.value" />
 			</s:elseif>
 		</d:column>
 		
 		<d:column title="Descripción" sortable="true" >
			<s:property value="%{#attr.row.description}" />
 		</d:column>
 		
 		<d:column title="Usuario" sortable="true" >
			<s:property value="%{#attr.row.user.person.firstName}" /> <s:property value="%{#attr.row.user.person.lastName}" /> 
 		</d:column>
 		
 		<d:column title='Ingreso'>
 			<s:if test="(#attr.row) instanceof com.tipuana.csa.model.JournalIncomeEntry">
 				<s:property value='%{getText("format.number", {#attr.row.amount})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else>
 		</d:column>
 		<d:column title='Egreso'>
 			<s:if test="(#attr.row) instanceof com.tipuana.csa.model.JournalOutcomeEntry">
 				<s:property value='%{getText("format.number", {#attr.row.amount})}' />
 			</s:if>
 			<s:else>
 				&nbsp;
 			</s:else>
 		</d:column>	
	</d:table>
	
	<jsp:include page="pagination.jsp"></jsp:include>
	
	<table class="list">
  		<tr>
    		<th>Total de Ingresos</th>
	    	<th>Total de Egresos</th>
	    	<th>Saldo Total</th>
  		</tr>
  		<tr>
    		<td><s:property value='%{getText("format.number", {journal.incomesAmount})}' /></td>
    		<td><s:property value='%{getText("format.number", {journal.outcomesAmount})}' /></td>
    		
    		<s:if test="%{(journal.revenue < 0)}">
    			<td class="negativeNumber"><s:property value='%{getText("format.number", {journal.revenue})}' /></td>
    		</s:if>
    		<s:else>
    			<td class="positiveNumber"><s:property value='%{getText("format.number", {journal.revenue})}' /></td>
    		</s:else>
  		</tr>
	</table>
	
	<table class="list">
  		<tr>
  			<th>Usuario</th>
    		<th>Total de Ingresos </th>
	    	<th>Total de Egresos</th>
	    	<th>Saldo Total</th>
	    	<th>Diferencia</th>
  		</tr>
  		<s:iterator value="%{journal.usersJournal}" >	
  			<tr>
  				<td>
  					<s:property value='%{user.person.firstName}'/> <s:property value='%{user.person.lastName}'/>
  				</td>
    			<td>
    				<s:property value='%{getText("format.number", {incomesAmount})}' />
    			</td>
    			<td>
    				<s:property value='%{getText("format.number", {outcomesAmount})}' />
    			</td>
    			
    			<s:if test="%{(revenue < 0)}">
    				<td class="negativeNumber"><s:property value='%{getText("format.number", {revenue})}' /></td>
    			</s:if>
    			<s:else>
    				<td class="positiveNumber"><s:property value='%{getText("format.number", {revenue})}' /></td>
    			</s:else>
    		
    		    <s:if test="%{(realRevenue < 0)}">
    				<td class="negativeNumber"><s:property value='%{getText("format.number", {realRevenue})}' /></td>
    			</s:if>
    			<s:else>
    				<td class="positiveNumber"><s:property value='%{getText("format.number", {realRevenue})}' /></td>
    			</s:else>
	  		</tr>
  		</s:iterator>
	</table>