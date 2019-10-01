	<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="ISO-8859-1" %> 
	
	<%@ taglib uri="/struts-tags" prefix="s"%>
	<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
	<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
	
	<html>
		<head>
			<title><t:insertAttribute name="title" /></title>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" > 
			<meta name="description" content='<t:getAsString name="description" />' >
			
			<sj:head jqueryui="true" jquerytheme="south-street" loadAtOnce="true" locale="es" />
			<link rel="stylesheet" type="text/css" href='<s:url value="/css/csa.css" />' />
			
			<script type="text/javascript">
				var contextPath = "<%=request.getContextPath()%>";
			    
			    $(
			    	function() {
			    		$.validator.setDefaults({ 
			    		    lang: "es" 
			    		});
			    	}
			    	
			    );
        	</script>
		</head>
		<body>
			<s:set var="language" value="%{locale.language}" scope="action"/>
			<t:insertAttribute name="actions" />
			<table class="layout">
				<tr>
					<td class="border ui-corner-all">
						&nbsp;
					</td>
					<td>
						<table class="center">
							<tr>
								<td class="header ui-corner-all" >
									<t:insertAttribute name="header" />		
								</td>
							</tr>
							<tr>
								<td class="content">
									<t:insertAttribute name="content" />	
								</td>
							</tr>
							<tr>
								<td class="footer ui-corner-all">
									<t:insertAttribute name="footer" />			
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</body>
	</html>