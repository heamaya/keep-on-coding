	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %>
	
	<%@ taglib uri="/struts-tags" prefix="s"%> 
	
	<h1 class="title">
		<s:text name="news" />
	</h1>
	
	<div id="result">
		<jsp:include page="newsPresentationList.jsp"></jsp:include>
	</div>